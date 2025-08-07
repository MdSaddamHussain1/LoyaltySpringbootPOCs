package com.circuitBreakerResiliancePOC.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CircuitBreakerGlobalConfig {

    @Bean
   public CircuitBreakerRegistry circuitBreakerRegistry()
    {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .maxWaitDurationInHalfOpenState(Duration.ofSeconds(50))
                .slidingWindowSize(50)
                .build();
        return  CircuitBreakerRegistry.of(config);
    }
}
