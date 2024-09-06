package com.payments.DSPaymentServices.dto;

import lombok.Data;

@Data
public class PaymentRequest {

    private Long userId;
    private String merchantId;
    private Double amount;
    private String creditCardNumber;
    private String creditCardToken;

    // Constructors, Getters, and Setters
}

