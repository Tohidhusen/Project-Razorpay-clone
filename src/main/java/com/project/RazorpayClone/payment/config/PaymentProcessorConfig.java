package com.project.RazorpayClone.payment.config;

import com.project.RazorpayClone.common.enums.PaymentMethod;
import com.project.RazorpayClone.payment.processor.PaymentProcessor;
import com.project.RazorpayClone.payment.processor.strategy.CardPaymentProcessor;
import com.project.RazorpayClone.payment.processor.strategy.NetbankingPaymentProcessor;
import com.project.RazorpayClone.payment.processor.strategy.UpiPaymentProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PaymentProcessorConfig {
    @Bean
    public Map<PaymentMethod, PaymentProcessor> paymentProcessorMap() {
        return Map.of(
                PaymentMethod.CARD, new CardPaymentProcessor(),
                PaymentMethod.NETBANKING, new NetbankingPaymentProcessor(),
                PaymentMethod.UPI, new UpiPaymentProcessor()
        );
    }
}
