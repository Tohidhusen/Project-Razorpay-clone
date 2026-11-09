package com.project.RazorpayClone.merchant.cache;

import com.project.RazorpayClone.common.enums.Environment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


public record ApiKeyCacheEntry(
        String keyId,
        String keySecretHash,
        String previousKeySecretHash,
        LocalDateTime gracePeriodExpireAt,
        UUID merchantId,
        Environment environment,
        boolean enabled
) {
    public boolean isInGracePeriod() {
        return gracePeriodExpireAt != null && LocalDateTime.now().isBefore(gracePeriodExpireAt);
    }
}
