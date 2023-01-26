package com.hydroyura.productionmanager.archive.controllers.rates;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface     IControllerRate<DTO> {

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> getSpecificationByAssemblyId(@PathVariable(name = "id") String rawId);

}
