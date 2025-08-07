package com.circuitBreakerResiliancePOC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CircuitBreakerResiliancePocApplication {

	public static void main(String[] args) {
		SpringApplication.run(CircuitBreakerResiliancePocApplication.class, args);
		System.out.println("Resiliance4j foult tollerence implementation using circuit breaker registry");

	}

}
