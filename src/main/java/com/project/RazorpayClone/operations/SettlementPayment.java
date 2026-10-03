package com.project.RazorpayClone.operations;

import com.project.RazorpayClone.common.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "settlement_payment")
public class SettlementPayment extends BaseEntity {

    @EmbeddedId
    private SettlementPaymentId id;

    @MapsId("settlementId")//map to the settlement id which is present in the settlement paymentid
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "settlement_id", nullable = false)
    private Settlement settlementId;
}
