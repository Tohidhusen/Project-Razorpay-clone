package com.project.RazorpayClone.payment.service.impl;

import com.project.RazorpayClone.payment.DTO.request.PaymentInitRequest;
import com.project.RazorpayClone.payment.DTO.response.PaymentResponse;
import com.project.RazorpayClone.payment.Entity.Payment;

import java.util.UUID;

public interface PaymentService {

    PaymentResponse initiate(UUID merchantId,PaymentInitRequest request);

    PaymentResponse capture(UUID merchantId, UUID paymentId);

    void resolveAuthorization(UUID paymentId, Boolean approve, String bankRef, String errorCode, String errordescription);
}
