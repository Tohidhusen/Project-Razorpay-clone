package com.project.RazorpayClone.merchant.dto.response;

import com.project.RazorpayClone.common.enums.BusinessType;
import com.project.RazorpayClone.common.enums.MerchantStatus;

import java.util.UUID;

public record MerchantResponse(
        UUID id,
        String name,
        String email,
        String businessName,
        BusinessType businessType,
        MerchantStatus merchantStatus

) {
}
