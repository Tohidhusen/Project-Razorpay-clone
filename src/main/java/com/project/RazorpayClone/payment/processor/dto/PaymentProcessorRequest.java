package com.project.RazorpayClone.payment.processor.dto;

import com.project.RazorpayClone.common.enums.Money;
import com.project.RazorpayClone.common.enums.PaymentMethod;

import java.util.Map;
import java.util.UUID;

public record PaymentProcessorRequest(
        UUID processingId,
        UUID paymentId,
        PaymentMethod method,
        Money amount,
        String pan,
        String expiry,
        Map<String,Object> methodDetails
) {
    public static PaymentProcessorRequest card(UUID paymentId,String pan,String expiry,Money amount,Map<String,Object> details) {
        return new PaymentProcessorRequest( UUID.randomUUID(),paymentId,PaymentMethod.CARD,amount,pan,expiry,details);
    }
    public static PaymentProcessorRequest nonCard(UUID paymentId,Money amount,Map<String,Object> details,PaymentMethod paymentMethod) {
        return new PaymentProcessorRequest( UUID.randomUUID(),paymentId,paymentMethod,amount,null,null,details);
    }
}
