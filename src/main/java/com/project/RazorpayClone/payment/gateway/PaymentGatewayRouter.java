package com.project.RazorpayClone.payment.gateway;

import com.project.RazorpayClone.common.enums.PaymentMethod;
import com.project.RazorpayClone.payment.gateway.DTO.PaymentRequest;
import com.project.RazorpayClone.payment.gateway.DTO.PaymentResult;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PaymentGatewayRouter {
    public final Map<PaymentMethod,PaymentAdapter> paymentAdapter;

    public PaymentResult initiate(PaymentRequest request) {
        PaymentAdapter adapter= paymentAdapter.get(request.method());
        if(adapter==null){
            throw new IllegalArgumentException("No payment adapter found for method: " + request.method());
        }
        return adapter.initiate(request);

    }

    public PaymentResult capture(PaymentMethod method, UUID paymentId) {
        PaymentAdapter adapter= paymentAdapter.get(method);
        if(adapter==null){
            throw new IllegalArgumentException("No payment adapter found for method: " + method);
        }
        return adapter.capture(paymentId);

    }
}
