package com.payments.DSPaymentServices.data.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_accounts")
@Data
public class UserAccount {

    @Id
    private Long userId;
    private String userName;
    private Double balance;

    public UserAccount() {
    }

    public UserAccount(Long userId, String userName, Double balance) {
        this.userId = userId;
        this.userName = userName;
        this.balance = balance;
    }

    // Getters and Setters
}
