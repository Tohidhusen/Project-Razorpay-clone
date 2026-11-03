package com.project.RazorpayClone.payment.service.impl;

import com.project.RazorpayClone.common.enums.OrderStatus;

import com.project.RazorpayClone.common.enums.PaymentEvent;
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
import com.project.RazorpayClone.payment.service.PaymentTransitionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentGatewayRouter paymentGatewayRouter;
    private final PaymentMapper paymentMapper;
    private final PaymentTransitionService paymentTransitionService;
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

        
           paymentTransitionService.apply(payment,PaymentEvent.AUTHORIZE_ATTEMPT);

         PaymentResult result=paymentGatewayRouter.initiate(paymentRequest);
          switch (result){
              case PaymentResult.Pending pending-> payment.setProcessorReference(pending.paymentRegRefrence());
              case PaymentResult.Failure failure-> {
//                  payment.setStatus(PaymentStatus.FAILED);
                  paymentTransitionService.apply(payment, PaymentEvent.AUTHORIZE_FAIL);
                  payment.setErrorCode(failure.errorCode());
                  payment.setErrorDescription(failure.errorDescription());
              }
              case PaymentResult.Success success -> {
              }
          }

          paymentRepository.save(payment);
          orderRepository.save(order);

          return paymentMapper.toResponse(payment);
    }

    @Override
    public PaymentResponse capture(UUID merchantId, UUID paymentId) {
        Payment payment= paymentRepository.findByIdAndMerchantId(paymentId,merchantId)
                .orElseThrow(()->new ResourceNotFoundException("Payment not found",paymentId));

//        payment.setStatus(PaymentStatus.CAPTURING);
        paymentTransitionService.apply(payment, PaymentEvent.CAPTURE_REQUEST);


        PaymentResult paymentResult = paymentGatewayRouter.capture(payment.getMethod(),paymentId);

        if(paymentResult instanceof PaymentResult.Success success){
            paymentTransitionService.apply(payment, PaymentEvent.CAPTURE_SUCCESS);
            payment.setCapturedAt(LocalDateTime.now());
            log.info("Payment captured successfully,paymentId{}",paymentId);

        }else if(paymentResult instanceof PaymentResult.Failure failure){
//            payment.setStatus(PaymentStatus.AUTHORIZED);
            paymentTransitionService.apply(payment, PaymentEvent.CAPTURE_FAIL);
            payment.setErrorCode(failure.errorCode());
            payment.setErrorDescription(failure.errorDescription());
            log.warn("Payment capture failed,paymentId{}",payment);
        }
        payment= paymentRepository.save(payment);

        return paymentMapper.toResponse(payment);
    }

    @Override
    public void resolveAuthorization(UUID paymentId, Boolean approve, String bankRef, String errorCode, String errordescription) {

        Payment payment =paymentRepository.findById(paymentId)
                .orElseThrow(()->new ResourceNotFoundException("Payment not found",paymentId));

        if(payment.getStatus() != PaymentStatus.AUTHORIZING){
            throw new BusinessRuleViolationException("PAYMENT_NOT_AUTHORIZING","Payment is not in authorizing state");
        }
        OrderRecord order=payment.getOrder();

        if(approve){
            paymentTransitionService.apply(payment, PaymentEvent.AUTHORIZE_SUCCESS);
            payment.setBankReference(bankRef);
            payment.setCapturedAt(LocalDateTime.now());
            log.info("Payment authorized successfully,paymentId{}",paymentId);

            //Autocapture
            paymentTransitionService.apply(payment, PaymentEvent.CAPTURE_REQUEST);
            PaymentResult captureResult = paymentGatewayRouter.capture(payment.getMethod(),paymentId);
             if(approve){
                 if(captureResult instanceof PaymentResult.Success success){
                     paymentTransitionService.apply(payment, PaymentEvent.CAPTURE_SUCCESS);
                     payment.setCapturedAt(LocalDateTime.now());
                     order.setStatus(OrderStatus.PAID);
                 }else if(captureResult instanceof PaymentResult.Failure failure){
                     paymentTransitionService.apply(payment, PaymentEvent.CAPTURE_FAIL);
                     payment.setErrorCode(failure.errorCode());
                     payment.setErrorDescription(failure.errorDescription());
                 }
             }else{
                 paymentTransitionService.apply(payment, PaymentEvent.CAPTURE_FAIL);
                 payment.setErrorCode(errorCode);
                 payment.setErrorDescription(errordescription);
             }
           paymentRepository.save(payment);
             orderRepository.save(order);

        }

    }

}
