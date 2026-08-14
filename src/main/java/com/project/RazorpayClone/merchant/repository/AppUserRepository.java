package com.project.RazorpayClone.merchant.repository;

import com.project.RazorpayClone.merchant.Entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
}
