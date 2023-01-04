package com.hydroyura.productionmanager.archive.controllers.parts;

import com.hydroyura.productionmanager.archive.services.parts.IPartService;
import com.hydroyura.productionmanager.sharedapi.dto.DTOApiError;
import com.hydroyura.productionmanager.sharedapi.dto.DTOPart;
import com.hydroyura.productionmanager.sharedapi.feigninterfaces.IArchivePartRestController;
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
@RequestMapping(value = "/" + PartRestController.API_VERSION + "/parts")
public class PartRestController implements IArchivePartRestController<DTOPart> {

    public static final String API_VERSION = "v1";
    public final String ERROR_MSG_INVALID_ID = "Invalid ID";
    public final String ERROR_MSG_ID_NOT_EXIST = "Item with current ID not exist";
    public final String ERROR_MSG_INVALID_FILTER = "Invalid filter value";

    @Autowired @Qualifier(value = "PartService")
    private IPartService<?, DTOPart> service;

    @Override
    public ResponseEntity<?> getItemById(String rawId) {
        long id = validateId(rawId);

        if(id == -1) {
            DTOApiError apiError = new DTOApiError().setMsg(ERROR_MSG_INVALID_ID);
            //FIXME: add logger msg
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }

        Optional<DTOPart> entity = service.getItemById(id);
        if(entity.isEmpty()) {
            DTOApiError apiError = new DTOApiError().setMsg(ERROR_MSG_ID_NOT_EXIST);
            //FIXME: add logger msg
            return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(entity.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAll(Map<String, Object> filter) {

        if(filter == null || filter.isEmpty() || !filter.containsKey("type")) {
            DTOApiError apiError = new DTOApiError().setMsg(ERROR_MSG_INVALID_FILTER);
            //FIXME: add logger msg
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }

        Collection<DTOPart> items = service.getAll(filter);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(String rawId, DTOPart modifiedItem) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(String rawId) {
        //FIXME: add checking url
        service.delete(Long.valueOf(rawId));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> create(DTOPart item) {
        //FIXME: validate
        return new ResponseEntity<>(service.save(item), HttpStatus.OK);
    }

    private long validateId(String rawId) {
        long id = -1;
        try {
            id = Long.valueOf(rawId);
        } catch (Exception e) {
            // FIXME: add logger msg
            System.out.println("VALIDATION ERROR");
        }
        if(id <= 0) id = -1;
        return id;
    }
}
