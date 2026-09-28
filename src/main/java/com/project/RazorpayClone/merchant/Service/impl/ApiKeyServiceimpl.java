package com.project.RazorpayClone.merchant.Service.impl;

import com.project.RazorpayClone.common.exception.ResourceNotFoundException;
import com.project.RazorpayClone.common.utility.RandomUtill;
import com.project.RazorpayClone.merchant.Entity.ApiKey;
import com.project.RazorpayClone.merchant.Entity.Merchant;
import com.project.RazorpayClone.merchant.Service.ApiKeyService;
import com.project.RazorpayClone.merchant.dto.request.CreateApiKeyRequest;
import com.project.RazorpayClone.merchant.dto.response.ApiKeyCreateResponse;
import com.project.RazorpayClone.merchant.dto.response.ApikeyResponse;
import com.project.RazorpayClone.merchant.mapper.ApikeyMapper;
import com.project.RazorpayClone.merchant.repository.ApiKeyRepository;
import com.project.RazorpayClone.merchant.repository.MerchantRepository;
import jakarta.annotation.Nullable;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ApiKeyServiceimpl implements ApiKeyService {

    private final MerchantRepository merchantRepository;
    private final ApiKeyRepository apiKeyRepository;
    private final ApikeyMapper apikeyMapper;


    @Override
    @Transactional
    public ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request) {

        Merchant merchant=merchantRepository.findById(merchantId)
                .orElseThrow(()->new ResourceNotFoundException("MERCHANT_NOT_FOUND","Merchant with id "+merchantId+" not found"));

        String keyId="rzp"+request.getEnvironment().name().toLowerCase()+ RandomUtill.randombase64(24);
        String rawsecret=RandomUtill.randombase64(40);

        ApiKey apiKey=ApiKey.builder()
                .merchant(merchant)
                .keyId(keyId)
                .keySecretHash(rawsecret)   // TODO: replace with bcryptencoder
                .environment(request.getEnvironment())
                .build();
        apiKey=apiKeyRepository.save(apiKey);

        return new ApiKeyCreateResponse(apiKey.getId(),apiKey.getKeyId(),rawsecret,apiKey.getEnvironment());
    }
    @Override
    public List<ApikeyResponse> listByMerchant(UUID merchantId){

       return apikeyMapper.toResponseList(apiKeyRepository.findByMerchant_Id(merchantId));
    }

    @Override
    @Transactional
    public void revoke(UUID merchantId, UUID keyId) {

        ApiKey apiKey=apiKeyRepository.findById(keyId)
                .filter(apiKey1 -> apiKey1.getMerchant().getId().equals(merchantId))
                .orElseThrow(()->new ResourceNotFoundException("API_KEY_NOT_FOUND","Api key with id "+keyId+" not found"));

        if(!apiKey.getMerchant().getId().equals(merchantId)){
            throw new ResourceNotFoundException("API_KEY_NOT_FOUND","Api key with id "+keyId+" not found for merchant with id "+merchantId);
        }

        apiKey.setEnabled(false);

    }

    @Override
    public @Nullable ApiKeyCreateResponse rotate(UUID merchantId, UUID keyId) {
        ApiKey apiKey=apiKeyRepository.findById(keyId)
                .filter(apiKey1 -> apiKey1.getMerchant().getId().equals(merchantId))
                .orElseThrow(()->new ResourceNotFoundException("API_KEY_NOT_FOUND","Api key with id "+keyId+" not found"));
                if(!apiKey.getEnabled()) throw new RuntimeException("API_KEY_DISABLED can not be rotated");

        String newrawsecret=RandomUtill.randombase64(40);

        apiKey.setPreviouskeySecretHash(apiKey.getKeySecretHash());
        apiKey.setKeySecretHash(newrawsecret); // TODO: replace with bcryptencoder

        apiKey.setRotatedAt(LocalDateTime.now());
        apiKey.setGracePeriodExpireAt(LocalDateTime.now().plusHours(24));

        apiKey=apiKeyRepository.save(apiKey);

        return new ApiKeyCreateResponse(apiKey.getId(),apiKey.getKeyId(),newrawsecret,apiKey.getEnvironment());


    }

}
