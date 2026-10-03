package com.project.RazorpayClone.payment.config;

import com.project.RazorpayClone.common.enums.PaymentMethod;
import com.project.RazorpayClone.payment.gateway.PaymentAdapter;
import com.project.RazorpayClone.payment.gateway.adapter.CardPaymentadapter;
import com.project.RazorpayClone.payment.gateway.adapter.NetBankingAdapter;
import com.project.RazorpayClone.payment.gateway.adapter.UpiPaymentAdapter;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class PaymentAdapterconfig {



    public Map<PaymentMethod, PaymentAdapter> getPaymentAdaptersMap() {
        return Map.of(
                PaymentMethod.CARD,new CardPaymentadapter(),
                PaymentMethod.NETBANKING,new NetBankingAdapter(),
                PaymentMethod.UPI,new UpiPaymentAdapter()
        );
    }
}
