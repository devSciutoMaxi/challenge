package com.tenpo.challenge.api.exceptions;

import lombok.Getter;

@Getter
public class ApiError {
    private String message;
    private Integer status;

    public ApiError() {
    }

    public ApiError(String message, Integer status) {
        super();
        this.message = message;
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
