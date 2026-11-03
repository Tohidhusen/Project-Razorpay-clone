package com.project.RazorpayClone.merchant.controller;

import com.project.RazorpayClone.merchant.Service.ApiKeyService;
import com.project.RazorpayClone.merchant.dto.request.CreateApiKeyRequest;
import com.project.RazorpayClone.merchant.dto.response.ApiKeyCreateResponse;
import com.project.RazorpayClone.merchant.dto.response.ApikeyResponse;
import com.project.RazorpayClone.merchant.security.MerchantContext;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("/v1/merchants/api-keys")
@RequiredArgsConstructor
public class ApiKeyContoller {

    private final ApiKeyService apiKeyService;
    private final MerchantContext merchantContext;
    @PostMapping
    public ResponseEntity<ApiKeyCreateResponse> create(
                                                                 @Valid @RequestBody CreateApiKeyRequest request) {
        return ResponseEntity.status(201).body(apiKeyService.create(merchantContext.getMerchantId(),request));
    }
    @GetMapping
    public ResponseEntity<List<ApikeyResponse>> listByMerchant(UUID merchantId) {
        return ResponseEntity.ok(apiKeyService.listByMerchant(merchantContext.getMerchantId()));
    }


    @DeleteMapping
    public ResponseEntity<Void> revoke(@PathVariable UUID keyId){
        apiKeyService.revoke(merchantContext.getMerchantId(),keyId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{keyId}/rotate")
    public ResponseEntity<ApiKeyCreateResponse> rotate( @PathVariable UUID keyId){
        return ResponseEntity.ok(apiKeyService.rotate(merchantContext.getMerchantId(),keyId));
    }
}
