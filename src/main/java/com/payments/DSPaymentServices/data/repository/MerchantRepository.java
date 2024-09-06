package com.payments.DSPaymentServices.data.repository;

import com.payments.DSPaymentServices.data.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantRepository extends JpaRepository<Merchant, String> {
    Optional<Merchant> findByMerchantId(String merchantId);
}
