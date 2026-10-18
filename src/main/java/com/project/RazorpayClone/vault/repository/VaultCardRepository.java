package com.project.RazorpayClone.vault.repository;

import com.project.RazorpayClone.vault.VaultCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VaultCardRepository extends JpaRepository<VaultCard, UUID> {
}
