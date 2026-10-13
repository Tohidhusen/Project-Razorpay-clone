package com.project.RazorpayClone.payment.config;

import com.project.RazorpayClone.common.enums.PaymentMethod;
import com.project.RazorpayClone.payment.gateway.PaymentAdapter;
import com.project.RazorpayClone.payment.gateway.adapter.CardPaymentadapter;
import com.project.RazorpayClone.payment.gateway.adapter.NetBankingAdapter;
import com.project.RazorpayClone.payment.gateway.adapter.UpiPaymentAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class PaymentAdapterconfig {

private final NetBankingAdapter netBankingAdapter;
private final CardPaymentadapter cardPaymentadapter;
private final UpiPaymentAdapter upiPaymentAdapter;

    public Map<PaymentMethod, PaymentAdapter> getPaymentAdaptersMap() {
        return Map.of(
                PaymentMethod.CARD, cardPaymentadapter,
                PaymentMethod.NETBANKING,netBankingAdapter,
                PaymentMethod.UPI,upiPaymentAdapter
        );
    }
}
