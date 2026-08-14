package com.project.RazorpayClone.merchant.dto.request;

import com.project.RazorpayClone.common.enums.Environment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateApiKeyRequest {
    private Environment environment;

    public String Environment() {
        return environment.name().toLowerCase();
    }
}
