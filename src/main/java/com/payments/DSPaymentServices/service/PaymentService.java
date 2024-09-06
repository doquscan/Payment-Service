package com.payments.DSPaymentServices.service;

import com.payments.DSPaymentServices.data.entity.Payment;
import com.payments.DSPaymentServices.data.repository.PaymentRepository;
import com.payments.DSPaymentServices.dto.PaymentRequest;
import com.payments.DSPaymentServices.exceptions.PaymentProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private com.example.payment.service.AccountService accountService;

    @Autowired
    private MerchantService merchantService;


    @Transactional
    public boolean processPayment(PaymentRequest request) {
        try {
            // Deduct amount from user account
            accountService.debit(request.getUserId(), request.getAmount());

            // Record the payment transaction
            Payment payment = new Payment(
                    request.getUserId(),
                    request.getMerchantId(),
                    request.getAmount(),
                    LocalDateTime.now(),
                    "SUCCESS"
            );
            paymentRepository.save(payment);

            // Update the merchant's balance
            merchantService.credit(request.getMerchantId(), request.getAmount());

            return true;
        } catch (Exception e) {
            throw new PaymentProcessingException("Payment processing failed", e);
        }
    }

    public boolean isDuplicateRequest(String idempotencyKey) {
        return paymentRepository.findByIdempotencyKey(idempotencyKey).isPresent();
    }

    public void saveIdempotencyKey(String idempotencyKey) {
        Payment payment = new Payment();
        payment.setIdempotencyKey(idempotencyKey);
        paymentRepository.save(payment);
    }

}

