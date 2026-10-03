package com.project.RazorpayClone.payment.processor;

import com.project.RazorpayClone.payment.gateway.DTO.PaymentRequest;
import com.project.RazorpayClone.payment.processor.dto.PaymentProcessorRequest;
import com.project.RazorpayClone.payment.processor.dto.PaymentProcessorResponse;

public interface PaymentProcessor {

    public PaymentProcessorResponse charge (PaymentProcessorRequest request);
}
