package com.payments.DSPaymentServices.service;

import com.payments.DSPaymentServices.data.entity.Merchant;
import com.payments.DSPaymentServices.data.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Transactional
    public void credit(String merchantId, Double amount) {
        Merchant merchant = merchantRepository.findByMerchantId(merchantId)
                .orElseThrow(() -> new RuntimeException("Merchant not found"));

        merchant.setBalance(merchant.getBalance() + amount);
        merchantRepository.save(merchant);
    }
}
