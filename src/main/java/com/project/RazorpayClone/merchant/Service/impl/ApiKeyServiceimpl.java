package com.project.RazorpayClone.merchant.Service.impl;

import com.project.RazorpayClone.common.exception.ResourceNotFoundException;
import com.project.RazorpayClone.merchant.Entity.ApiKey;
import com.project.RazorpayClone.merchant.Entity.Merchant;
import com.project.RazorpayClone.merchant.Service.ApiKeyService;
import com.project.RazorpayClone.merchant.dto.request.CreateApiKeyRequest;
import com.project.RazorpayClone.merchant.dto.response.ApiKeyCreateResponse;
import com.project.RazorpayClone.merchant.repository.ApiKeyRepository;
import com.project.RazorpayClone.merchant.repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiKeyServiceimpl implements ApiKeyService {

    private final MerchantRepository merchantRepository;
    private final ApiKeyRepository apiKeyRepository;


    @Override
    public ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request) {

        Merchant merchant=merchantRepository.findById(merchantId)
                .orElseThrow(()->new ResourceNotFoundException("MERCHANT_NOT_FOUND","Merchant with id "+merchantId+" not found"));

        String keyId="rzp"+request.getEnvironment().name().toLowerCase()+"big_ran_String";
        String rawsecret="big_ran_String";//TODO replace with some cryptographic string

        ApiKey apiKey=ApiKey.builder()
                .merchant(merchant)
                .keyId(keyId)
                .keySecretHash(rawsecret)
                .environment(request.getEnvironment())
                .build();
        apiKey=apiKeyRepository.save(apiKey);

        return new ApiKeyCreateResponse(apiKey.getId(),keyId,rawsecret, request.Environment());
    }
}
