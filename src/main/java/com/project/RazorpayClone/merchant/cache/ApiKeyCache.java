package com.project.RazorpayClone.merchant.cache;

import com.project.RazorpayClone.merchant.Entity.ApiKey;

import java.util.Optional;
import java.util.UUID;

public interface ApiKeyCache {

    Optional<ApiKeyCacheEntry> get(String keyId);

    void put(String keyId, ApiKeyCacheEntry entry);

    void  evict(String keyId);
}
