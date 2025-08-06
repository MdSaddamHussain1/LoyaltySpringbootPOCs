package com.wiremockPoc;

import org.springframework.boot.SpringApplication;

public class TestWiremockPocApplication {

	public static void main(String[] args) {
		SpringApplication.from(WiremockPocApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
