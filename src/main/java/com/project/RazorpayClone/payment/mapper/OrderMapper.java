package com.project.RazorpayClone.payment.mapper;

import com.project.RazorpayClone.payment.DTO.response.OrderResponse;
import com.project.RazorpayClone.payment.Entity.OrderRecord;
import jakarta.persistence.criteria.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    OrderResponse toResponse(OrderRecord orderrecord);
}
