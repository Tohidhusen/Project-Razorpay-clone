package com.project.RazorpayClone.merchant.repository;

import com.project.RazorpayClone.merchant.Entity.Merchant;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MerchantRepository extends JpaRepository<Merchant, UUID> {
    boolean existsByEmail(String email);
}
