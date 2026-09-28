package com.project.RazorpayClone.payment.DTO.request;

import com.project.RazorpayClone.common.enums.Money;

import java.time.LocalDateTime;
import java.util.Map;

public record OrderCreateRequest(
        Money amount,
        String receipt,
        Map<String,Object> notes,
        LocalDateTime expireAt
) {

}
