package com.project.RazorpayClone.payment.gateway;

import com.project.RazorpayClone.payment.Entity.Payment;
import com.project.RazorpayClone.payment.gateway.DTO.PaymentRequest;
import com.project.RazorpayClone.payment.gateway.DTO.PaymentResult;

public interface PaymentAdapter {
    PaymentResult initiate(PaymentRequest request);
}
