package com.project.RazorpayClone.payment.service.impl;

import com.project.RazorpayClone.payment.DTO.request.PaymentInitRequest;
import com.project.RazorpayClone.payment.DTO.response.PaymentResponse;

import java.util.UUID;

public interface PaymentService {

    PaymentResponse initiate(UUID merchantId,PaymentInitRequest request);
}
