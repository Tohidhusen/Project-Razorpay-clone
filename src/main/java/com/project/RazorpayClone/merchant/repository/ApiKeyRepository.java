package com.project.RazorpayClone.merchant.repository;

import com.project.RazorpayClone.merchant.Entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApiKeyRepository extends JpaRepository<ApiKey, UUID> {
}
