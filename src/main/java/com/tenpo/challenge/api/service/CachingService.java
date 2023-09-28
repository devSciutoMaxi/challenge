package com.tenpo.challenge.api.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableCaching
@EnableScheduling
public class CachingService {

    private final static Logger log = LoggerFactory.getLogger(CachingService.class);

    @CacheEvict(allEntries = true, cacheNames = { "percentage" })
    @Scheduled(fixedRateString = "${caching.spring.percentage.ttl}")
    public void cacheEvict() {
        log.info("clear cache");
    }

}
