package com.tenpo.challenge.api.mock;

import com.tenpo.challenge.api.service.ExternalService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;


@Service
public class ServiceExternalMock implements ExternalService {

    @Cacheable(value = "percentage")
    @Override
    public BigDecimal getPercentage() {
        Random r = new Random();
        return new BigDecimal(r.nextInt(100) + 1);
    }

}



