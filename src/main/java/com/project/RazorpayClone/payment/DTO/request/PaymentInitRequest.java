package com.project.RazorpayClone.payment.DTO.request;

import com.project.RazorpayClone.common.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;

import java.util.Map;
import java.util.UUID;

public record PaymentInitRequest(
        @NotNull(message = "orderId is required")
        UUID orderId,
        @NotNull(message = "payment method is required")
        PaymentMethod method,
        Map<String,Object> methodDetails
) {
}
