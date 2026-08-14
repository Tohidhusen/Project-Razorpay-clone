package com.project.RazorpayClone.merchant.controller;

import com.project.RazorpayClone.merchant.Service.ApiKeyService;
import com.project.RazorpayClone.merchant.dto.request.CreateApiKeyRequest;
import com.project.RazorpayClone.merchant.dto.response.ApiKeyCreateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
