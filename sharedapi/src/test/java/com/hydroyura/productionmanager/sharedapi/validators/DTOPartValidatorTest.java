package com.hydroyura.productionmanager.sharedapi.validators;

import com.hydroyura.productionmanager.sharedapi.dto.DTOPart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {ValidatorTestConfiguration.class})
class DTOPartValidatorTest {

    @Autowired @Qualifier(value = "DTOPartValidator")
    Validator validator;

    @Test
    void validatorNotEmpty() {
        assertNotNull(validator);
    }


    @Test
    void failedValidationWithEmptyObject() {
        DTOPart dtoPart = new DTOPart();
        dtoPart.setType("test");
        DataBinder dataBinder = new DataBinder(dtoPart);
        dataBinder.setValidator(validator);
        dataBinder.validate();

        assertEquals(true, dataBinder.getBindingResult().hasErrors());
    }

    @Test
    void successValidationWithDefaultValue() {
        DTOPart defaultPart = new DTOPart();
        defaultPart.setNumber("TEST");
        defaultPart.setName("TEST");
        defaultPart.setType("PART");
        defaultPart.setStatus("TEST");
        defaultPart.setOtherFile("TEST");
        defaultPart.setPdf("TEST");
        defaultPart.setCreated("TEST");
        defaultPart.setLastUpdate("TEST");

        DataBinder dataBinder = new DataBinder(defaultPart);
        dataBinder.setValidator(validator);
        dataBinder.validate();

        assertEquals(false, dataBinder.getBindingResult().hasErrors());
    }


}