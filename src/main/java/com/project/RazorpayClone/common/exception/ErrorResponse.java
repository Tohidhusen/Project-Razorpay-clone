package com.project.RazorpayClone.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;



import java.time.LocalDateTime;
import java.util.List;



///it contains the method and field for create the json format of error
@JsonInclude(  JsonInclude.Include.NON_NULL)
public record ErrorResponse(
        String errorCode,
        String errorDescription,
        LocalDateTime timeStamp,
        List<FieldError> fieldErrors
) {
    public record FieldError (String errorCode,String errorDescription){}

    public static ErrorResponse of(String errorCode, String errorDescription) {
        return new ErrorResponse(errorCode, errorDescription, LocalDateTime.now(), null);
    }
    public static  ErrorResponse of(String errorCode, String errorDescription, LocalDateTime timeStamp, List<FieldError> fieldErrors) {
        return new ErrorResponse(errorCode, errorDescription, timeStamp, fieldErrors);
    }
}
