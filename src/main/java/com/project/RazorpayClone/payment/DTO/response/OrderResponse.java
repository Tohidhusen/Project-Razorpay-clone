package com.project.RazorpayClone.payment.DTO.response;

import com.project.RazorpayClone.common.enums.Money;
import com.project.RazorpayClone.common.enums.OrderStatus;


import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;




public record OrderResponse(
        UUID orderId,
        UUID merchantId,
        Money amount,
        String receipt,
        OrderStatus status,
        Integer attempts,
        Map<String,Object> notes,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {

}
