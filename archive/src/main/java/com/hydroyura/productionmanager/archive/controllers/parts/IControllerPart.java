package com.hydroyura.productionmanager.archive.controllers.parts;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IControllerPart<DTO> {

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> getItemById(@PathVariable(name = "id") String rawId);

    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<?> getItemsByType(@RequestParam(name = "type", required = true, defaultValue = "NONE") String type);

    /*
    @RequestMapping(method = RequestMethod.PUT, value = "")
    public ResponseEntity<?> update(@RequestBody Entity entity);

    @RequestMapping(method = RequestMethod.DELETE, value = "")
    public ResponseEntity<?> delete(@RequestParam(name = "id", required = true) String rawId);

    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<?> save(@RequestBody Entity entity);
    */
}