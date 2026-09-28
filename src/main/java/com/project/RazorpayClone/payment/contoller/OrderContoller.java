package com.project.RazorpayClone.payment.contoller;

import com.project.RazorpayClone.payment.DTO.request.OrderCreateRequest;
import com.project.RazorpayClone.payment.DTO.response.OrderResponse;
import com.project.RazorpayClone.payment.service.impl.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderContoller {
    private final OrderService orderService;

    UUID merchantId = UUID.fromString("11111111-1111-1111-1111-111111111111"); //TODO replace wiht meerchantsecuirity context

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody OrderCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(merchantId,request));
    }
}
