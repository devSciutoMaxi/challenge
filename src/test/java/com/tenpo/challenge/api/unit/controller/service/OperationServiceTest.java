package com.tenpo.challenge.api.unit.controller.service;

import com.tenpo.challenge.api.mock.ServiceExternalMock;
import com.tenpo.challenge.api.service.OperationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = {OperationService.class})
class OperationServiceTest {


    @Autowired
    OperationService operationService;

    @MockBean
    ServiceExternalMock serviceExternalMock;


    @Test
    public void getCalculateValid() {
        when(serviceExternalMock.getPercentage()).thenReturn(new BigDecimal(10));
        BigDecimal result = operationService.calculate(new BigDecimal(5), new BigDecimal(5));
        assertNotNull(result);
        assertEquals(new BigDecimal("11"), result);
    }

    @Test
    public void getPercentageNull() {
        when(serviceExternalMock.getPercentage()).thenReturn(null);

        RuntimeException response = assertThrows(RuntimeException.class, () -> operationService.calculate(new BigDecimal(1), new BigDecimal(2)));

        assertEquals("Error en el servicio para obtener el porcentaje", response.getMessage());
    }


}