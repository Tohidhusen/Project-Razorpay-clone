package com.project.RazorpayClone.payment.contoller;

import com.project.RazorpayClone.merchant.Entity.Merchant;
import com.project.RazorpayClone.payment.DTO.request.PaymentInitRequest;
import com.project.RazorpayClone.payment.DTO.response.PaymentResponse;
import com.project.RazorpayClone.payment.service.impl.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/payment")
@RequiredArgsConstructor
public class PaymentContoller {


    private final PaymentService paymentService;
    Merchant merchantId="FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF ";

    @PostMapping
    public ResponseEntity<PaymentResponse> initiate(@Valid @RequestBody PaymentInitRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paymentService.initiate(merchantId, request));
    }

    @PostMapping("/{paymentId}/capture")
    public ResponseEntity<PaymentResponse> capture(@PathVariable UUID paymentId) {

        return ResponseEntity.ok(paymentService.capture(merchantId,paymentId));
    }
}
