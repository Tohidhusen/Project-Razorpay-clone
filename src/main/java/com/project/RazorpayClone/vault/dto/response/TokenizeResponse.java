package com.project.RazorpayClone.vault.dto.response;

import com.project.RazorpayClone.common.enums.CardBrand;

public record TokenizeResponse(
        String token,
        String lastFour,
        CardBrand brand,
        Integer expiryMonth,
        Integer expiryYear
) {
}
