package com.project.RazorpayClone.payment.processor.dto;

import com.project.RazorpayClone.payment.processor.PaymentProcessor;

public sealed interface PaymentProcessorResponse permits
        PaymentProcessorResponse.Pending,
        PaymentProcessorResponse.succes,
        PaymentProcessorResponse.Failure
{
    record Pending(String processorRefrence) implements PaymentProcessorResponse {}

    record succes(String processorRefrence,String bankRefrence)implements PaymentProcessorResponse{}

    record Failure(String errorCode, String errorDescription)implements PaymentProcessorResponse{}
}
