package com.example.FireFly_frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FireFlyFrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FireFlyFrontendApplication.class, args);
	}

}
