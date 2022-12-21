package com.hydroyura.productionmanager.frontendweb.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "archive-rates", url = "http://localhost:8100/rates")
public interface IRateService {

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> getSpecificationByAssemblyId(@PathVariable(name = "id") String rawId);

}
