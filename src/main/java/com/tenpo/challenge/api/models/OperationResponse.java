package com.tenpo.challenge.api.models;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OperationResponse {

    private Integer numberOne;
    private Integer numberTwo;
    private BigDecimal percentage;
    private BigDecimal result;

    public OperationResponse(Integer numberOne, Integer numberTwo, BigDecimal percentage, BigDecimal result) {
        this.numberOne = numberOne;
        this.numberTwo = numberTwo;
        this.percentage = percentage;
        this.result = result;
    }

}
