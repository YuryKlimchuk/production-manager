package com.hydroyura.productionmanager.frontendweb.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "archive-parts", url = "http://localhost:8100/parts")
public interface IPartService {

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> getItemById(@PathVariable(name = "id") String rawId);

    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<?> getItemsByType(@RequestParam(name = "type", required = true, defaultValue = "NONE") String type);

}
