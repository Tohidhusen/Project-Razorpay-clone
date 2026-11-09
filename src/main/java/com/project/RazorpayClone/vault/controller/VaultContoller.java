package com.project.RazorpayClone.vault.controller;

import com.project.RazorpayClone.merchant.security.MerchantContext;
import com.project.RazorpayClone.payment.processor.PaymentProcessor;
import com.project.RazorpayClone.payment.processor.dto.PaymentProcessorRequest;
import com.project.RazorpayClone.payment.processor.dto.PaymentProcessorResponse;
import com.project.RazorpayClone.vault.dto.request.TokenizeRequest;
import com.project.RazorpayClone.vault.dto.response.TokenizeResponse;
import com.project.RazorpayClone.vault.service.VaultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/vault")
public class VaultContoller {
    private final VaultService vaultService;
    private final MerchantContext merchantContext;

    @PostMapping("/tokenize")
    public ResponseEntity<TokenizeResponse> tokenize(@Valid @RequestBody TokenizeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vaultService.tokenize(request, merchantContext.getMerchantId()));
    }
}
