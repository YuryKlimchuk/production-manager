package com.hydroyura.productionmanager.archive.controllers.rates;


import com.hydroyura.productionmanager.archive.entities.DBPartType;
import com.hydroyura.productionmanager.archive.services.rates.IRateService;
import com.hydroyura.productionmanager.sharedapi.dto.DTORate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "/rates")
public class ControllerRate implements IControllerRate<DTORate> {

    @Autowired
    @Qualifier(value = "RateService")
    private IRateService<?, DTORate> service;

    @Override
    public ResponseEntity<?> getSpecificationByAssemblyId(String rawId) {
        long validId = Long.valueOf(rawId);
        if(validId == -1) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Collection<DTORate> entities = service.getAllByAssemblyId(validId);
        if(entities.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        Map<String, List<DTORate>> specification = new HashMap<>();
        Arrays.stream(DBPartType.values()).forEach(value -> {
            specification.put(value.toString(), new ArrayList<>());
        });

        entities.stream().forEach(entity -> {
            String type = entity.getElement().getType();
            if(specification.containsKey(type)) specification.get(type).add(entity);
        });

        return new ResponseEntity<>(specification, HttpStatus.OK);
    }

}
