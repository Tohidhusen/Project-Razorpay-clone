package com.project.RazorpayClone.payment.gateway.adapter;

import com.project.RazorpayClone.payment.gateway.DTO.PaymentRequest;
import com.project.RazorpayClone.payment.gateway.DTO.PaymentResult;
import com.project.RazorpayClone.payment.gateway.PaymentAdapter;

import java.util.UUID;

public class CardPaymentadapter implements PaymentAdapter {

    @Override
    public PaymentResult initiate(PaymentRequest request) {
     return null;
    }

    @Override
    public PaymentResult capture(UUID paymentId) {
        return null;
    }
}
