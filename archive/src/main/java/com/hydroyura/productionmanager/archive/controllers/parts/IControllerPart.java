package com.hydroyura.productionmanager.archive.controllers.parts;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public interface IControllerPart<DTO> {

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> getItemById(@PathVariable(name = "id") String rawId);

    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<?> getAll();

    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<?> getItemsByType(@RequestParam(name = "type", required = true, defaultValue = "NONE") String type);

    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<?> getItemsByTypeAndFilter(
            @RequestParam(name = "type", required = true, defaultValue = "NONE") String type,
            @RequestParam(name = "filter", required = true) Map<String, Object> filter);

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") String rawId, @RequestBody DTO modifiedItem);

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") String rawId);

    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<?> create(@RequestBody DTO item);

}