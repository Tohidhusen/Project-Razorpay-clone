package com.project.RazorpayClone.common.exception;

import lombok.Getter;

@Getter
public class DuplicateResourcehandle extends RuntimeException
{
    private final String errorCode;

    public DuplicateResourcehandle(String errorCode, String message) {
        super(message);
        this.errorCode=errorCode;
    }



}
