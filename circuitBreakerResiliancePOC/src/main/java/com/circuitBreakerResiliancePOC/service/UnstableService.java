package com.circuitBreakerResiliancePOC.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class UnstableService {
    private  int temp =0;
    @CircuitBreaker(name = "default")
    public  String unstableMethod()
    {
        temp++;
        if(temp % 2 == 0)
        {
            throw new RuntimeException("API simulated failure");
        }
        return "success attemp" +temp;
    }
}
