package com.project.RazorpayClone.payment.service.impl;

import com.project.RazorpayClone.common.enums.OrderStatus;
import com.project.RazorpayClone.common.enums.PaymentStatus;
import com.project.RazorpayClone.common.exception.BusinessRuleViolationException;
import com.project.RazorpayClone.common.exception.ResourceNotFoundException;
import com.project.RazorpayClone.payment.DTO.request.PaymentInitRequest;
import com.project.RazorpayClone.payment.DTO.response.PaymentResponse;
import com.project.RazorpayClone.payment.Entity.OrderRecord;
import com.project.RazorpayClone.payment.Entity.Payment;
import com.project.RazorpayClone.payment.gateway.DTO.PaymentRequest;
import com.project.RazorpayClone.payment.gateway.DTO.PaymentResult;
import com.project.RazorpayClone.payment.gateway.PaymentGatewayRouter;
import com.project.RazorpayClone.payment.mapper.PaymentMapper;
import com.project.RazorpayClone.payment.repository.OrderRepository;
import com.project.RazorpayClone.payment.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentGatewayRouter paymentGatewayRouter;
    private final PaymentMapper paymentMapper;
    @Override
    @Transactional
    public PaymentResponse initiate(UUID merchantId,PaymentInitRequest request) {
        OrderRecord order=orderRepository.findByIdAndMerchantId(request.orderId(),merchantId)
                .orElseThrow(()->new ResourceNotFoundException("Order was not found",request.orderId()));

        if(order.getStatus() != OrderStatus.PAID && order.getStatus() != OrderStatus.CANCELLED) {
            throw new BusinessRuleViolationException("ORDER_NOT_PAYABLE","order cannot accept in this state:" +order.getId());
        }
        order.setStatus(OrderStatus.CREATED);
        order.setAttempts(order.getAttempts()+1);

        Payment payment =Payment.builder()
                .order(order)
                .merchantId(merchantId)
                .amount(order.getAmount())
                .status(PaymentStatus.CREATED)
                .method(request.method())
                .methodDetails(request.methodDetails())
                .build();
         payment=paymentRepository.save(payment);
        PaymentRequest paymentRequest =new PaymentRequest(
                payment.getId(),order.getId(),
                merchantId, order.getAmount(),request.method(),request.methodDetails());

         PaymentResult result=paymentGatewayRouter.initiate(paymentRequest);
          switch (result){
              case PaymentResult.Pending pending-> payment.setProcessorReference(pending.paymentRegRefrence());
              case PaymentResult.Failure failure-> {
                  payment.setStatus(PaymentStatus.FAILED);
                  payment.setErrorCode(failure.errorCode());
                  payment.setErrorDescription(failure.errorDescription());
              }
          }

          paymentRepository.save(payment);
          orderRepository.save(order);

          return paymentMapper.toResponse(payment);
    }
}
