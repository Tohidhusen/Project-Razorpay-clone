package com.project.RazorpayClone.payment.gateway;

import com.project.RazorpayClone.common.enums.PaymentMethod;
import com.project.RazorpayClone.payment.gateway.DTO.PaymentRequest;
import com.project.RazorpayClone.payment.gateway.DTO.PaymentResult;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class PaymentGatewayRouter {
    public final Map<PaymentMethod,PaymentAdapter> paymentAdapterMap;

    public PaymentResult initiate(PaymentRequest request){
        PaymentAdapter adapter=paymentAdapterMap.get(request.method());
        if(adapter==null){
            throw new IllegalArgumentException("Invalid request method");
        }
       return adapter.initiate(request);//if its card calling card instance and card method

    }
}
