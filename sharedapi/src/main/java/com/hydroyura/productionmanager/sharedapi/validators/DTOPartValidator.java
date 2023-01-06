package com.hydroyura.productionmanager.sharedapi.validators;

import com.hydroyura.productionmanager.sharedapi.dto.DTOPart;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.Collection;

public class DTOPartValidator implements Validator {

    private static final Collection<String> PART_TYPES = Arrays.asList("PART", "ASSEMBLY", "BUY_PART", "STANDARD_PART");
    private static final Collection<String> PART_STATUSES = Arrays.asList("DESIGN", "PRODUCTION", "TEST", "NONE");

    @Override
    public boolean supports(Class<?> clazz) {
        return DTOPart.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(target instanceof DTOPart part) {


            String type = part.getType();
            if(part.getType() != null && !PART_TYPES.contains(part.getType())) {
                errors.rejectValue("type", "type.INCORRECT_VALUE");
            }

            /*
            // check fields to not null, not empty
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "number", "number.empty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "status.empty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastUpdate", "lastUpdate.empty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "created", "created.empty");
            //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "type.empty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pdf", "pdf.empty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "otherFile", "otherFile.empty");

            // check field type has allowed value
            if(part.getType() != null && !PART_TYPES.contains(part.getType())) {
                errors.rejectValue("type", "type.INCORRECT_VALUE");
            }

            // check field status has allowed value


            // 2. FIXME: add checking date format
            */
        } else {
            // 3. FIXME: logger
            throw new RuntimeException("");
        }
    }
}
