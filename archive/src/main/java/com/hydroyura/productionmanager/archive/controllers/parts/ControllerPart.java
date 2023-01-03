package com.hydroyura.productionmanager.archive.controllers.parts;

import com.hydroyura.productionmanager.archive.services.parts.IPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/parts")
public class ControllerPart implements IControllerPart<DTOPart> {

    @Autowired @Qualifier(value = "PartService")
    private IPartService<?, DTOPart> service;

    @Override
    public ResponseEntity<?> getItemById(String rawId) {
        long validId = Long.valueOf(rawId);
        if(validId == -1) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Optional<DTOPart> entity = service.getItemById(validId);
        if(entity.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(entity.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAll(Map<String, Object> filter) {
        Collection<DTOPart> items = service.getAll(filter);
        if(items.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(String rawId, DTOPart modifiedItem) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(String rawId) {
        service.delete(Long.valueOf(rawId));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> create(DTOPart item) {
        return null;
    }
}
