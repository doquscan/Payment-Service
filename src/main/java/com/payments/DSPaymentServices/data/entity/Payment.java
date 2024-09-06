package com.payments.DSPaymentServices.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String merchantId;
    private Double amount;
    private LocalDateTime paymentDate;
    private String status;

    @Column(unique = true)
    private String idempotencyKey;

    public Payment(Long userId, String merchantId, Double amount, LocalDateTime now, String success) {
    }

    public Payment(Long userId, String merchantId, Double amount, LocalDateTime paymentDate, String status, String idempotencyKey) {
        this.userId = userId;
        this.merchantId = merchantId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
        this.idempotencyKey = idempotencyKey;
    }

    public Payment() {

    }


    // Getters and Setters for all fields, including idempotencyKey

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }
}
