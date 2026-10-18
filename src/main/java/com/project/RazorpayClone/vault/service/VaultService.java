package com.project.RazorpayClone.vault.service;

import com.project.RazorpayClone.common.enums.Money;
import com.project.RazorpayClone.payment.processor.dto.PaymentProcessorResponse;
import com.project.RazorpayClone.vault.dto.request.TokenizeRequest;
import com.project.RazorpayClone.vault.dto.response.TokenizeResponse;

import java.util.Map;
import java.util.UUID;

public interface VaultService {
    TokenizeResponse tokenize(TokenizeRequest request, UUID merchantId);

    PaymentProcessorResponse charge(UUID paymentId, String token, Money amount, Map<String, Object> methodDetails);
}
