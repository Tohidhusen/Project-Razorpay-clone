package com.project.RazorpayClone.payment.DTO.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.RazorpayClone.common.enums.Money;
import com.project.RazorpayClone.common.enums.PaymentMethod;
import com.project.RazorpayClone.common.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
@JsonInclude(JsonInclude.Include.NON_NULL)
public record PaymentResponse(
        UUID id,
        UUID orderId,
        UUID merchantId,
        Money amount,
        PaymentMethod method,
        PaymentStatus status,
        Map<Object,String> methodDetail,
        String errorCode,
        String errorDescription,
        LocalDateTime createdAt,
        LocalDateTime capturedAt

) {
}
