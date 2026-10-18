package com.project.RazorpayClone.payment.gateway.adapter;

import com.project.RazorpayClone.payment.gateway.DTO.PaymentRequest;
import com.project.RazorpayClone.payment.gateway.DTO.PaymentResult;
import com.project.RazorpayClone.payment.gateway.PaymentAdapter;
import com.project.RazorpayClone.payment.processor.dto.PaymentProcessorResponse;
import com.project.RazorpayClone.vault.service.VaultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
@RequiredArgsConstructor
public class CardPaymentadapter implements PaymentAdapter {

    private final VaultService vaultService;

    @Override
    public PaymentResult initiate(PaymentRequest request) {


            String token = (String) request.methodDetails().get("token");

            PaymentProcessorResponse response = vaultService.charge(
                    request.paymentId(), token, request.amount(), request.methodDetails()
            );

            return switch (response) {
                case PaymentProcessorResponse.Success success -> new PaymentResult.Success(success.bankRefrence());
                case PaymentProcessorResponse.Failure failure -> new PaymentResult.Failure(failure.errorCode(), failure.errorDescription());
                case PaymentProcessorResponse.Pending pending -> new PaymentResult.Pending(pending.processorRefrence());
            };

    }

    @Override
    public PaymentResult capture(UUID paymentId) {
        return new PaymentResult.Success("CARD_REF");
    }
}
