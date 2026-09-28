package com.project.RazorpayClone.payment.service.impl;

import com.project.RazorpayClone.payment.DTO.request.OrderCreateRequest;
import com.project.RazorpayClone.payment.DTO.response.OrderResponse;
import com.project.RazorpayClone.payment.DTO.response.PaymentResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderResponse create(UUID merchantId, OrderCreateRequest request);

    OrderResponse getOrderById(UUID merchantId, UUID orderId);

    OrderResponse cancel(UUID merchantId, UUID orderId);

    List<PaymentResponse> listPayments(UUID merchantId, UUID orderId);
}
