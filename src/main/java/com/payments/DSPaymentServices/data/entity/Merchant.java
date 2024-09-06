package com.payments.DSPaymentServices.data.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "merchants")
@Data
public class Merchant {

    @Id
    private String merchantId;
    private String name;
    private Double balance;

    public Merchant(String merchantId, String name, Double balance) {
        this.merchantId = merchantId;
        this.name = name;
        this.balance = balance;
    }

    // Getters and Setters
}
