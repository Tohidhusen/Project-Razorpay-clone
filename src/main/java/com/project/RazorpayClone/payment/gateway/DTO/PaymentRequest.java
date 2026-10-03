package com.project.RazorpayClone.payment.gateway.DTO;

import com.project.RazorpayClone.common.enums.Money;
import com.project.RazorpayClone.common.enums.PaymentMethod;

import java.util.Map;
import java.util.UUID;

public record PaymentRequest(
        UUID paymentId,
        UUID orderId,
        UUID merchantId,
        Money amount,
        PaymentMethod method,
        Map<String,Object> methodDetails
) {
}
