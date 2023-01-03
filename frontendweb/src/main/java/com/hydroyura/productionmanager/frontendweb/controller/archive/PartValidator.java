package com.hydroyura.productionmanager.frontendweb.controller.archive;

import com.hydroyura.productionmanager.frontendweb.dto.DTOPart;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component(value = "PartValidator")
public class PartValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return DTOPart.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
