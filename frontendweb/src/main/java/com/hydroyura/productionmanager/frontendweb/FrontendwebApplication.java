package com.hydroyura.productionmanager.frontendweb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.productionmanager.sharedapi.validators.DTOPartValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.Validator;

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

	@Bean(name = "DTOPartValidator")
	public Validator getDTOPartValidator() {
		return new DTOPartValidator();
	}

}
