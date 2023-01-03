package com.hydroyura.productionmanager.frontendweb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class FrontendwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontendwebApplication.class, args);
	}


	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}

}
