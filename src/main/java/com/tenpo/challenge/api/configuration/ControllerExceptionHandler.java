package com.tenpo.challenge.api.configuration;


import com.tenpo.challenge.api.exceptions.ApiError;
import com.tenpo.challenge.api.exceptions.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {ApiException.class})
    public ResponseEntity<ApiError> handleApiException(ApiException e) {
        Integer statusCode = e.getStatusCode();

        ApiError apiError = new ApiError(e.getMessage(), statusCode);
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

}