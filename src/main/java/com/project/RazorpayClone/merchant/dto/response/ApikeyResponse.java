package com.project.RazorpayClone.merchant.dto.response;

import com.project.RazorpayClone.common.enums.Environment;

import javax.swing.text.StyledEditorKit;
import java.time.LocalDateTime;
import java.util.UUID;

public record ApikeyResponse(
        UUID id,
        String keyId,
        Environment environment,
        Boolean enabled,
        LocalDateTime lastUsedAt,
        LocalDateTime createdAt
) {
}
