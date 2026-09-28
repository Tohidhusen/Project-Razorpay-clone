package com.project.RazorpayClone.merchant.mapper;

import com.project.RazorpayClone.merchant.Entity.ApiKey;
import com.project.RazorpayClone.merchant.dto.response.ApiKeyCreateResponse;
import com.project.RazorpayClone.merchant.dto.response.ApikeyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApikeyMapper {

    ApiKeyCreateResponse toResponse(ApiKey apiKey);
    List<ApikeyResponse> toResponseList(List<ApiKey> apiKeyList);
}
