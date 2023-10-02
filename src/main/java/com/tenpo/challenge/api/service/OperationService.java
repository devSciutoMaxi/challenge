package com.tenpo.challenge.api.service;

import com.tenpo.challenge.api.exceptions.ApiException;
import com.tenpo.challenge.api.mock.ServiceExternalMock;
import com.tenpo.challenge.api.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OperationService {

    @Autowired
    private ServiceExternalMock serviceExternalMock;
    public BigDecimal percentage = null;

    private final static Logger log = LoggerFactory.getLogger(OperationService.class);

    public OperationService() {
    }

    public BigDecimal calculate(BigDecimal one, BigDecimal two) {
        BigDecimal suma = one.add(two);
        try {
            getPercentage();
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }

        return suma.multiply(this.percentage).divide(Constants.ONE_HUNDRED).add(suma);
    }

    private void getPercentage() throws Exception {
        int i = 0;
        do {
            this.percentage = serviceExternalMock.getPercentage();
            i++;
        } while (i < 3 && this.percentage == null);
        if (this.percentage == null) {
            String message = "Error en el servicio para obtener el porcentaje";
            log.info(message);
            throw new Exception(message);
        }
    }

}
