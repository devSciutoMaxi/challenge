package com.tenpo.challenge.api.mock;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;


@Service
public class ServiceExternalMock {

    @Cacheable(value = "percentage")
    public BigDecimal getPercentage() {
        Random r = new Random();
        return new BigDecimal(r.nextInt(100) + 1);
    }

}



