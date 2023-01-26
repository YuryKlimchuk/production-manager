package com.hydroyura.productionmanager.sharedapi.feigninterfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IArchiveRateRestController<DTO> {

    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<?> getAll();

    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<?> getByAssembly(@RequestParam(name = "assemblyId", required = true) String rawAssemblyId);

    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<?> getByPart(@RequestParam(name = "partId", required = true) String partId);

    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<?> create(@RequestBody DTO item);

    @RequestMapping(method = RequestMethod.DELETE, value = "/{rateId}")
    public ResponseEntity<?> delete(@PathVariable(name = "rateId") String rawRateId);

    @RequestMapping(method = RequestMethod.PUT, value = "/{rateId}")
    public ResponseEntity<?> update(@PathVariable(name = "rateId") String rawRateId, @RequestBody DTO item);

}
