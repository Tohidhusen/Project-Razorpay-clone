package com.project.RazorpayClone.payment.gateway.DTO;

public sealed interface PaymentResult permits PaymentResult.Pending,
        PaymentResult.Failure{
    record Pending(String paymentRegRefrence) implements PaymentResult {}
    record Failure(String errorCode,String errorDescription) implements PaymentResult {}
}
