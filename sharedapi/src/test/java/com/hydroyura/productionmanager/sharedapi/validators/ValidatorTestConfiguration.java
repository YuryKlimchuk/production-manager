package com.hydroyura.productionmanager.sharedapi.validators;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.Validator;

@TestConfiguration
public class ValidatorTestConfiguration {

    @Bean(name = "DTOPartValidator")
    public Validator getDTOPartValidator() {
        return new DTOPartValidator();
    }

}
