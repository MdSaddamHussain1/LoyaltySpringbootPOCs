package com.InMemoryAuth.InMemoryAuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;

@SpringBootApplication
public class InMemoryAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(InMemoryAuthApplication.class, args);
		System.out.println("inMemory authentication Application running");
	}

}
