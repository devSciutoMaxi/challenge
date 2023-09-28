package com.tenpo.challenge.api.service;

import com.tenpo.challenge.api.mock.ServiceExternalMock;
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
        when(serviceExternalMock.getPercentage()).thenReturn(new BigDecimal(5));
        BigDecimal result = operationService.calculate(new BigDecimal(1), new BigDecimal(2));
        assertNotNull(result);
    }

    @Test
    public void getPercentageNull() {
        when(serviceExternalMock.getPercentage()).thenReturn(null);

        RuntimeException x = assertThrows(RuntimeException.class, () -> operationService.calculate(new BigDecimal(1), new BigDecimal(2)));

        assertEquals("Error en el servicio para obtener el porcentaje", x.getMessage());
    }


}