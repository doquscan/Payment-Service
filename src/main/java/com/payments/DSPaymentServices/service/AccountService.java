package com.example.payment.service;


import com.payments.DSPaymentServices.data.entity.UserAccount;
import com.payments.DSPaymentServices.data.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Transactional
    public void debit(Long userId, Double amount) {
        UserAccount userAccount = userAccountRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User account not found"));

        if (userAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        userAccount.setBalance(userAccount.getBalance() - amount);
        userAccountRepository.save(userAccount);
    }
}
