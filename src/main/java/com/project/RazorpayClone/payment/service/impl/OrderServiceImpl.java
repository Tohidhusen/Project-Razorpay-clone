package com.project.RazorpayClone.payment.service.impl;

import com.project.RazorpayClone.common.enums.OrderStatus;
import com.project.RazorpayClone.common.exception.BusinessRuleViolationException;
import com.project.RazorpayClone.common.exception.DuplicateResourcehandle;
import com.project.RazorpayClone.common.exception.ResourceNotFoundException;
import com.project.RazorpayClone.payment.DTO.request.OrderCreateRequest;
import com.project.RazorpayClone.payment.DTO.response.OrderResponse;
import com.project.RazorpayClone.payment.DTO.response.PaymentResponse;
import com.project.RazorpayClone.payment.Entity.OrderRecord;
import com.project.RazorpayClone.payment.Entity.Payment;
import com.project.RazorpayClone.payment.mapper.OrderMapper;
import com.project.RazorpayClone.payment.mapper.PaymentMapper;
import com.project.RazorpayClone.payment.repository.OrderRepository;
import com.project.RazorpayClone.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

import java.time.temporal.ChronoUnit;
import java.util.List;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Value("${order.default-expiry-minutes:30}")
    @DurationUnit(ChronoUnit.MINUTES)
    private Duration defaultOrderExpiry;

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final OrderMapper orderMapper;


    @Override
    public OrderResponse create(UUID merchantId, OrderCreateRequest request) {
       if(request.receipt() != null  && orderRepository.existsByMerchantIdAndReceipt(merchantId,request.receipt())){
         throw new DuplicateResourcehandle("ORDER_ALREADY_EXISTS","Order with receipt "+request.receipt()+" already exists for merchant "+merchantId);
       }
        OrderRecord order =OrderRecord.builder()
                .merchantId(merchantId)
                .amount(request.amount())
                .receipt(request.receipt())
                .status(OrderStatus.CREATED)
                .expireAt(request.expireAt() != null
                        ? request.expireAt()
                        : LocalDateTime.now().plus(defaultOrderExpiry))
                .build();
       order =orderRepository.save(order);
       // TODO make public kafka event about order is created
        
       return orderMapper.toResponse(order);

    }

    @Override
    public OrderResponse getOrderById(UUID merchantId, UUID orderId) {
        OrderRecord order=orderRepository.findByIdAndMerchantId(orderId,merchantId)
                .orElseThrow(()->new ResourceNotFoundException("ORDER_NOT_FOUND","Order with id "+orderId));
                return orderMapper.toResponse(order);
    }

    @Override
    public OrderResponse cancel(UUID merchantId, UUID orderId) {
        OrderRecord order=orderRepository.findByIdAndMerchantId(orderId,merchantId)
                .orElseThrow(()->new ResourceNotFoundException("ORDER_NOT_FOUND","Order with id "+orderId));

        if(order.getStatus()==OrderStatus.CANCELED || order.getStatus()==OrderStatus.PAID){
            throw new BusinessRuleViolationException("ORDER_CANNOT_BE_CANCELED", "Order with status " + order.getStatus() + " cannot be canceled");
        }
        order.setStatus(OrderStatus.CANCELED);
        order=orderRepository.save(order);
        return orderMapper.toResponse(order);
    }

    @Override
    public List<PaymentResponse> listPayments(UUID merchantId, UUID orderId) {

       OrderRecord order =orderRepository.findByIdAndMerchantId(orderId,merchantId)
                .orElseThrow(()->new ResourceNotFoundException("ORDER_NOT_FOUND","Order with id "+orderId));

       List<Payment> paymentList=paymentRepository.findByOrder_Id(order);

        return paymentList.stream()
                .map(paymentMapper::toResponse)
                .collect(Collectors.toList());


    }
}
