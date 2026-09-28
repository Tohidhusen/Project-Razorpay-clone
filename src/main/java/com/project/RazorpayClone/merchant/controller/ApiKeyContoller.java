package com.project.RazorpayClone.merchant.controller;

import com.project.RazorpayClone.merchant.Service.ApiKeyService;
import com.project.RazorpayClone.merchant.dto.request.CreateApiKeyRequest;
import com.project.RazorpayClone.merchant.dto.response.ApiKeyCreateResponse;
import com.project.RazorpayClone.merchant.dto.response.ApikeyResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("/v1/merchants/{merchantId}/api-keys")
@RequiredArgsConstructor
public class ApiKeyContoller {

    private final ApiKeyService apiKeyService;
    @PostMapping
    public ResponseEntity<ApiKeyCreateResponse> create(@PathVariable UUID merchantId,
                                                                 @Valid @RequestBody CreateApiKeyRequest request) {
        return ResponseEntity.status(201).body(apiKeyService.create(merchantId,request));
    }
    @GetMapping
    public ResponseEntity<List<ApikeyResponse>> listByMerchant(@PathVariable UUID merchantId){
        return ResponseEntity.ok(apiKeyService.listByMerchant(merchantId));
    }


    @DeleteMapping
    public ResponseEntity<Void> revoke(@PathVariable  UUID merchantId,@PathVariable UUID keyId){
        apiKeyService.revoke(merchantId,keyId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{keyId}/rotate")
    public ResponseEntity<ApiKeyCreateResponse> rotate(@PathVariable UUID merchantId, @PathVariable UUID keyId){
        return ResponseEntity.ok(apiKeyService.rotate(merchantId,keyId));
    }
}
