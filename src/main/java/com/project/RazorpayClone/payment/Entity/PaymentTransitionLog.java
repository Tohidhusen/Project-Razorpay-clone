package com.project.RazorpayClone.payment.Entity;

import com.project.RazorpayClone.common.BaseEntity;
import com.project.RazorpayClone.common.enums.PaymentEvent;
import com.project.RazorpayClone.common.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "payment_transition_log",indexes = {
        @Index(name = "idx_payment_transition_log_payment_id", columnList = "payment_id")
})
public class PaymentTransitionLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Enumerated(EnumType.STRING)
    @Column(name = "from_status",length = 30)
    private PaymentStatus fromstatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "event",length = 30,nullable = false)
    private PaymentEvent event;

    @Enumerated(EnumType.STRING)
    @Column(name = "to_status",length = 30)
    private PaymentStatus toStatus;

    @Column(name = "actor",length = 100,nullable = false)
    private String actor;

    @Column(name = "occurred_at",nullable = false)
    private LocalDateTime occurredAt;
}
