package com.project.RazorpayClone.merchant.Service;

import com.project.RazorpayClone.merchant.dto.request.MerchantSignupRequest;
import com.project.RazorpayClone.merchant.dto.response.MerchantResponse;


public interface AuthService {
    MerchantResponse signup( MerchantSignupRequest request);
}
