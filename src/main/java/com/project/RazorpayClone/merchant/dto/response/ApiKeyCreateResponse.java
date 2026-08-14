package com.project.RazorpayClone.merchant.dto.response;



import java.util.UUID;

public record ApiKeyCreateResponse(
        UUID id,
        String KeyId,
        String KeySecret,
        String environment
){


}
