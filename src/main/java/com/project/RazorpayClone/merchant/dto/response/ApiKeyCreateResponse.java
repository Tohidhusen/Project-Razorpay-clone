package com.project.RazorpayClone.merchant.dto.response;



import com.project.RazorpayClone.common.enums.Environment;

import java.util.UUID;

public record ApiKeyCreateResponse(
        UUID id,
        String KeyId,
        String KeySecret,
      Environment environment
){


}
