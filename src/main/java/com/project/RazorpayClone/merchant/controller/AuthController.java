package com.project.RazorpayClone.merchant.controller;

import com.project.RazorpayClone.merchant.Service.AuthService;
import com.project.RazorpayClone.merchant.dto.request.MerchantSignupRequest;
import com.project.RazorpayClone.merchant.dto.response.MerchantResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<MerchantResponse> signup(@RequestBody @Valid MerchantSignupRequest request){
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.signup(request));
    }
}
