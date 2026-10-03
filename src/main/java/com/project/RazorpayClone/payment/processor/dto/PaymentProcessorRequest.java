package com.project.RazorpayClone.payment.processor.dto;

import com.project.RazorpayClone.common.enums.Money;
import com.project.RazorpayClone.common.enums.PaymentMethod;

import java.util.Map;

public record PaymentProcessorRequest(
        PaymentMethod method,
        Money amount,
        Map<String,Object> methodDetails
) {
}
