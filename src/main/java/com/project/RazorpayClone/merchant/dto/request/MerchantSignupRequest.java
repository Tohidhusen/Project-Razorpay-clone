package com.project.RazorpayClone.merchant.dto.request;

import com.project.RazorpayClone.common.enums.BusinessType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MerchantSignupRequest(

         @NotNull(message = "name is required")
        String name,

        @Email(message = "Invalid email format")
        @NotNull(message = "email is required")
        String email,

        @NotNull(message = "password is required")
        @Size(min=8, message = "Password must be at least 8 characters long")
        String password,

        String businessName,
        BusinessType businessType
) {
}
