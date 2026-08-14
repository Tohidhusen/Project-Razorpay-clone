package com.project.RazorpayClone.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Getter
public class GlobalExceptionHandling {

    @ExceptionHandler(DuplicateResourcehandle.class)
    public ResponseEntity<ErrorResponse> handleDuplicateResourceException(DuplicateResourcehandle ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse.of(ex.getErrorCode(), ex.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        String errorCode=ex.getResourceName()+"NOT_FOUND"+ex.getIdentifier();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.of(errorCode, ex.getMessage()));
    }
}
