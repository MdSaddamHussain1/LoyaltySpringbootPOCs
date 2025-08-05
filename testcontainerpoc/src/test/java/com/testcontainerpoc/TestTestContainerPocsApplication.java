package com.testcontainerpoc;

import org.springframework.boot.SpringApplication;

public class TestTestContainerPocsApplication {

	public static void main(String[] args) {
		SpringApplication.from(TestContainerPocsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
