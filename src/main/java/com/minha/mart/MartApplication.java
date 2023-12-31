package com.minha.mart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.minha.mart.Entity")
public class MartApplication {

	public static void main(String[] args) {
		SpringApplication.run(MartApplication.class, args);
	}

}
