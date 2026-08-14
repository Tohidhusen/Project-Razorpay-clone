package com.project.RazorpayClone.merchant.Service.impl;

import com.project.RazorpayClone.common.enums.MerchantStatus;
import com.project.RazorpayClone.common.enums.UserRole;
import com.project.RazorpayClone.common.exception.DuplicateResourcehandle;
import com.project.RazorpayClone.merchant.Entity.AppUser;
import com.project.RazorpayClone.merchant.Entity.Merchant;
import com.project.RazorpayClone.merchant.Service.AuthService;
import com.project.RazorpayClone.merchant.dto.request.MerchantSignupRequest;
import com.project.RazorpayClone.merchant.dto.response.MerchantResponse;
import com.project.RazorpayClone.merchant.repository.AppUserRepository;
import com.project.RazorpayClone.merchant.repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final MerchantRepository merchantRepository;
    private final AppUserRepository appUserRepository;
    @Override
    public MerchantResponse signup(MerchantSignupRequest request) {
        if(merchantRepository.existsByEmail(request.email())) {
            throw new DuplicateResourcehandle("MERCHANT_ALREADY_EXISTS", "Merchant with email " + request.email() + " already exists");
        }
        Merchant merchant=Merchant.builder()
                .name(request.name())
                .email(request.email())
                .businessName(request.businessName())
                .businessType(request.businessType())
                .status(MerchantStatus.PENDING_KYC)
                .build();
        merchant=merchantRepository.save(merchant);
        AppUser appuser= AppUser.builder()
                .role(UserRole.OWNER)
                .email(merchant.getEmail())
                .merchant(merchant)
                .passwordHash(request.password())   //TODO decrypt password using bcrypt
                .build();
         appUserRepository.save(appuser);

        return new MerchantResponse(
                merchant.getId(),
                merchant.getName(),
                merchant.getEmail(),
                merchant.getBusinessName(),
                merchant.getBusinessType(),
                merchant.getStatus()
        );

    }

}
