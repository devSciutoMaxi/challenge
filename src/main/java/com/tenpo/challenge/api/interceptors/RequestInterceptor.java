package com.tenpo.challenge.api.interceptors;


import com.tenpo.challenge.api.util.Constants;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import io.github.bucket4j.Refill;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Duration;

import static org.springframework.http.HttpStatus.TOO_MANY_REQUESTS;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    private final static Logger log = LoggerFactory.getLogger(RequestInterceptor.class);

    private final Bucket bucket;

    public RequestInterceptor() {
        Refill refill = Refill.intervally(Constants.RPM, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(Constants.RPM, refill);
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();

    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        if (probe.isConsumed()) {
            response.addHeader("X-Rate-Limit-Remaining", String.valueOf(probe.getRemainingTokens()));
            return true;
        } else {
            long waitForRefill = probe.getNanosToWaitForRefill() / 1_000_000_000;
            response.addHeader("X-Rate-Limit-Retry-After-Seconds", String.valueOf(waitForRefill));
            response.sendError(TOO_MANY_REQUESTS.value(), "You have exhausted your API Request Quota");
            return false;
        }
    }


    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        log.info("[afterCompletion]");
    }
}