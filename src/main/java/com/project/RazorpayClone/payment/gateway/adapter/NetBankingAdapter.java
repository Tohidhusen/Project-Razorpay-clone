package com.project.RazorpayClone.payment.gateway.adapter;

import com.project.RazorpayClone.common.enums.PaymentMethod;
import com.project.RazorpayClone.payment.gateway.DTO.PaymentRequest;
import com.project.RazorpayClone.payment.gateway.DTO.PaymentResult;
import com.project.RazorpayClone.payment.gateway.PaymentAdapter;
import com.project.RazorpayClone.payment.mapper.PaymentMapper;
import com.project.RazorpayClone.payment.processor.PaymentProcessorRouter;
import com.project.RazorpayClone.payment.processor.dto.PaymentProcessorRequest;
import com.project.RazorpayClone.payment.processor.dto.PaymentProcessorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class NetBankingAdapter implements PaymentAdapter {
     private final PaymentProcessorRouter paymentProcessorRouter;

    @Override
    public PaymentResult initiate(PaymentRequest request) {
       try{
           log.info("initiate payment with netbanking adapter,paymentID:{}",request.paymentId());

           PaymentProcessorRequest paymentProcessorRequest =PaymentProcessorRequest.nonCard(
                   request.paymentId(),request.amount(), request.methodDetails(), PaymentMethod.NETBANKING);

           PaymentProcessorResponse paymentProcessorResponse =
                   paymentProcessorRouter.charge(paymentProcessorRequest);

           return switch (paymentProcessorResponse){
               case PaymentProcessorResponse.Pending pending-> new PaymentResult.Pending(pending.processorRefrence());
               case PaymentProcessorResponse.Failure failure-> new PaymentResult.Failure(failure.errorCode(),failure.errorDescription());
               case PaymentProcessorResponse.Success success->new PaymentResult.Success(success.bankRefrence());
           };
       }catch (Exception e){
           log.warn("Netbanking failed for paymentId{}",request.paymentId());
           return new PaymentResult.Failure("NETBANKING_FAILED",e.getMessage());
       }
    }

    @Override
    public PaymentResult capture(UUID paymentId) {
        return new PaymentResult.Success("NBK_REF");
    }
}
