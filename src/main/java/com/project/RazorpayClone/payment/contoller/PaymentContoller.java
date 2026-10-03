package com.project.RazorpayClone.payment.contoller;

import com.project.RazorpayClone.payment.DTO.request.PaymentInitRequest;
import com.project.RazorpayClone.payment.DTO.response.PaymentResponse;
import com.project.RazorpayClone.payment.service.impl.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/payment")
@RequiredArgsConstructor
public class PaymentContoller {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> initiate(@Valid  @RequestBody PaymentInitRequest request) {
        
    }
}
