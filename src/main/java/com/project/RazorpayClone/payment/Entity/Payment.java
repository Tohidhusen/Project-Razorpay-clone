package com.project.RazorpayClone.payment.Entity;

import com.project.RazorpayClone.common.BaseEntity;
import com.project.RazorpayClone.common.enums.Money;
import com.project.RazorpayClone.common.enums.PaymentMethod;
import com.project.RazorpayClone.common.enums.PaymentStatus;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "payments",indexes = {
        @Index(name = "idx_payments_merchant_id", columnList = "merchantId"),
        @Index(name = "idx_payments_order_id", columnList = "order_id"),
})
public class Payment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID merchantId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderRecord order;

    @Embedded
    private Money amount;

    @Column(nullable = false, length = 100)
    private String idempotenceKey;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PaymentStatus Status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PaymentMethod method;


    @JdbcTypeCode(SqlTypes.JSON)
    @Column(length = 255, nullable = false, columnDefinition = "jsonb")
    private Map<String, Object> methodDetails;

    @Column(length = 100)
    private String bankReference;

    @Column(length = 100)
    private String errorCode;

    @Column(length = 200)
    private String errorDescription;

    private LocalDateTime authorizedAt;

    private LocalDateTime capturedAt;

    private LocalDateTime failedAt;

    private LocalDateTime refundedAt;

    private LocalDateTime settledAt;

}


