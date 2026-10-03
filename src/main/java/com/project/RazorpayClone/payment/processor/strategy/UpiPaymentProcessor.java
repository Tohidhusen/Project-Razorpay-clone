package com.project.RazorpayClone.payment.processor.strategy;

import com.project.RazorpayClone.payment.processor.PaymentProcessor;
import com.project.RazorpayClone.payment.processor.dto.PaymentProcessorRequest;
import com.project.RazorpayClone.payment.processor.dto.PaymentProcessorResponse;

public class UpiPaymentProcessor implements PaymentProcessor {
    @Override
    public PaymentProcessorResponse charger(PaymentProcessorRequest request) {
        return null;
    }
}
