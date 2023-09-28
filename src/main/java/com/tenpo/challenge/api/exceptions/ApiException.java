package com.tenpo.challenge.api.exceptions;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiException extends RuntimeException {

    private Integer statusCode;
    private String message;

    public ApiException(String message, Integer statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public ApiException(String message) {
        this.statusCode = 500;
        this.message = message;
    }


}
