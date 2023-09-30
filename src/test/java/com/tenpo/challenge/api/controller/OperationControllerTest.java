package com.tenpo.challenge.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tenpo.challenge.api.exceptions.ApiException;
import com.tenpo.challenge.api.models.AddRequest;
import com.tenpo.challenge.api.models.OperationResponse;
import com.tenpo.challenge.api.repository.LogRepository;
import com.tenpo.challenge.api.service.LogService;
import com.tenpo.challenge.api.service.OperationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doThrow;

@SpringBootTest(classes = {OperationController.class})
public class OperationControllerTest {

    @Autowired
    private OperationController operationController;

    @MockBean
    private OperationService operationService;

    @MockBean
    private LogRepository repository;

    @MockBean
    private LogService logService;

    @Test
    public void addOK() throws JsonProcessingException {
        ResponseEntity<OperationResponse> response = operationController.add(new AddRequest(1,2));

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void addApiException() {
        doThrow(new ApiException("Error en el servicio para obtener el porcentaje")).when(operationService).calculate(new BigDecimal(1), new BigDecimal(2));

        ApiException apiException = assertThrows(ApiException.class, () -> operationController.add(new AddRequest(1,2)));
        Assertions.assertEquals(Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), apiException.getStatusCode());
        Assertions.assertEquals("Error en el servicio para obtener el porcentaje", apiException.getMessage());
    }

}
