package com.hydroyura.productionmanager.archive;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.productionmanager.sharedapi.validators.DTOPartValidator;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.Validator;

@SpringBootApplication
public class ArchiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArchiveApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}


	@Bean(name = "DTOPartValidator")
	public Validator getDTOPartValidator() {
		return new DTOPartValidator();
	}

	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}
}
