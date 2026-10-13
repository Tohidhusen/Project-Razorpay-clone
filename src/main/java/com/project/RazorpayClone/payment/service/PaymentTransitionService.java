package com.project.RazorpayClone.payment.service;

import com.project.RazorpayClone.common.enums.PaymentActer;
import com.project.RazorpayClone.common.enums.PaymentEvent;
import com.project.RazorpayClone.common.enums.PaymentStatus;
import com.project.RazorpayClone.payment.Entity.Payment;
import com.project.RazorpayClone.payment.Entity.PaymentTransitionLog;
import com.project.RazorpayClone.payment.repository.PaymentTransitionLogRepository;
import com.project.RazorpayClone.payment.statemachine.PaymentStateMachine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentTransitionService {

    private final PaymentTransitionLogRepository paymentTransitionLogRepository;
    private final PaymentStateMachine paymentStateMachine;

    public PaymentStatus apply(Payment payment, PaymentEvent event) {
        PaymentStatus next = paymentStateMachine.transition(payment.getStatus(), event);
        PaymentTransitionLog log = PaymentTransitionLog.builder()
                .payment(payment)
                .fromStatus(payment.getStatus())
                .event(event)
                .toStatus(next)
                .actor(PaymentActer.SYSTEM) //TODO: fetch merchant context to identify actor
                .occurredAt(LocalDateTime.now())
                .build();
        payment.setStatus(next);
        paymentTransitionLogRepository.save(log);
        return next;
    }

}
