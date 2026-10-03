package com.project.RazorpayClone.operations;

import com.project.RazorpayClone.common.BaseEntity;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class SettlementPaymentId  {
    private UUID settlementId;
    private UUID paymentId;
}
