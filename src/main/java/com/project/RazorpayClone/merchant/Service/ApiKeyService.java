package com.project.RazorpayClone.merchant.Service;

import com.project.RazorpayClone.merchant.dto.request.CreateApiKeyRequest;
import com.project.RazorpayClone.merchant.dto.response.ApiKeyCreateResponse;

import java.util.UUID;

public interface ApiKeyService {
    ApiKeyCreateResponse create(UUID merchantId,CreateApiKeyRequest request);
}
