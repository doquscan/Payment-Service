package com.payments.DSPaymentServices.controller;

import com.payments.DSPaymentServices.dto.PaymentRequest;
import com.payments.DSPaymentServices.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequest paymentRequest, @RequestHeader("Idempotency-Key") String idempotencyKey) {
        if (paymentService.isDuplicateRequest(idempotencyKey)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate request");
        }

        paymentService.saveIdempotencyKey(idempotencyKey);

        boolean paymentSuccess = paymentService.processPayment(paymentRequest);

        if (paymentSuccess) {
            return ResponseEntity.ok("Payment successful");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment failed");
        }
    }
}


