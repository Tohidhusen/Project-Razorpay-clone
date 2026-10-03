package com.project.RazorpayClone.payment.processor;

import com.project.RazorpayClone.common.enums.PaymentMethod;
import com.project.RazorpayClone.payment.processor.dto.PaymentProcessorRequest;
import com.project.RazorpayClone.payment.processor.dto.PaymentProcessorResponse;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PaymentProcessorRouter {

    private Map<PaymentMethod, PaymentProcessor> paymentProcessorMap;
    public PaymentProcessorResponse charge(PaymentProcessorRequest request) {
        PaymentProcessor processor = paymentProcessorMap.get(request.method());
        if (processor == null) {
            throw new IllegalArgumentException("No payment processor found for method: " + request.method());
        }
        return processor.charge(request);
    }
}
