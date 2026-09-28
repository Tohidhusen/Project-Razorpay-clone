package com.project.RazorpayClone.merchant.mapper;

import com.project.RazorpayClone.merchant.Entity.Merchant;
import com.project.RazorpayClone.merchant.dto.request.MerchantSignupRequest;
import com.project.RazorpayClone.merchant.dto.response.MerchantResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MerchantMapper {

    Merchant toEntityFromRequest(MerchantSignupRequest request);
    MerchantResponse toResponse(Merchant merchant);
}
