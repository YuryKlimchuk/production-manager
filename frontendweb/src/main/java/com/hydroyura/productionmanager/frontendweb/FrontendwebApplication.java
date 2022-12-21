package com.hydroyura.productionmanager.frontendweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FrontendwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontendwebApplication.class, args);
	}

}
