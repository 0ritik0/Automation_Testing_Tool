package com.brokenlink;

import org.springframework.boot.SpringApplication;

public class TestBrokenLinkApplication {

	public static void main(String[] args) {
		SpringApplication.from(BrokenLinkApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
