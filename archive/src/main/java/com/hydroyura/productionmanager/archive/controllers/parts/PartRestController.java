package com.hydroyura.productionmanager.archive.controllers.parts;

import com.hydroyura.productionmanager.archive.services.parts.IPartService;
import com.hydroyura.productionmanager.sharedapi.dto.DTOApiError;
import com.hydroyura.productionmanager.sharedapi.dto.DTOPart;
import com.hydroyura.productionmanager.sharedapi.feigninterfaces.IArchivePartRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
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
    public final String ERROR_MSG_SERVER_ERROR = "SERVER ERROR";
    public final String ERROR_MSG_VALIDATION_FAILED = "Validation failed";

    @Autowired @Qualifier(value = "PartService")
    private IPartService<?, DTOPart> service;

    @Autowired @Qualifier(value = "DTOPartValidator")
    private Validator validator;

    @Override
    public ResponseEntity<?> getItemById(String rawId) {
        long id = validateId(rawId);

        if(id == -1) {
            DTOApiError apiError = new DTOApiError().setMsg(ERROR_MSG_INVALID_ID);
            //FIXME: add logger msg
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }

        Optional<DTOPart> entity = null;

        try {
            entity = service.getItemById(id);
        } catch (Exception e) {
            DTOApiError apiError = new DTOApiError().setMsg(ERROR_MSG_SERVER_ERROR);
            //FIXME: add logger msg
            return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(entity.isEmpty()) {
            DTOApiError apiError = new DTOApiError().setMsg(ERROR_MSG_ID_NOT_EXIST);
            //FIXME: add logger msg
            return new ResponseEntity<>(apiError, HttpStatus.NO_CONTENT);
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

        Collection<DTOPart> items = null;

        try {
            items = service.getAll(filter);
        } catch (Exception e) {
            DTOApiError apiError = new DTOApiError().setMsg(ERROR_MSG_SERVER_ERROR);
            //FIXME: add logger msg
            return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(String rawId, DTOPart modifiedItem) {
        long id = validateId(rawId);

        if(id == -1) {
            DTOApiError apiError = new DTOApiError().setMsg(ERROR_MSG_INVALID_ID);
            //FIXME: add logger msg
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }

        DataBinder dataBinder = new DataBinder(modifiedItem);
        dataBinder.setValidator(validator);
        dataBinder.validate();

        if(dataBinder.getBindingResult().hasErrors()) {
            DTOApiError apiError = new DTOApiError().setMsg(ERROR_MSG_VALIDATION_FAILED);
            //FIXME: add logger msg
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }

        Optional<DTOPart> currentItem = service.getItemById(id);

        if(currentItem.isEmpty()) {
            DTOApiError apiError = new DTOApiError().setMsg(ERROR_MSG_INVALID_ID);
            //FIXME: add logger msg
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }

        Optional<DTOPart> updatedItem = null;

        try {
            updatedItem = service.save(modifiedItem);
        } catch (Exception e) {
            DTOApiError apiError = new DTOApiError().setMsg(ERROR_MSG_SERVER_ERROR);
            //FIXME: add logger msg
            return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(updatedItem.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(String rawId) {
        long id = validateId(rawId);

        if(id == -1) {
            DTOApiError apiError = new DTOApiError().setMsg(ERROR_MSG_INVALID_ID);
            //FIXME: add logger msg
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }

        try {
            service.delete(Long.valueOf(rawId));
        } catch (Exception e) {
            DTOApiError apiError = new DTOApiError().setMsg(ERROR_MSG_SERVER_ERROR);
            //FIXME: add logger msg
            return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<?> create(DTOPart item) {

        DataBinder dataBinder = new DataBinder(item);
        dataBinder.setValidator(validator);
        dataBinder.validate();

        if(dataBinder.getBindingResult().hasErrors()) {
            DTOApiError apiError = new DTOApiError().setMsg(ERROR_MSG_VALIDATION_FAILED);
            //FIXME: add logger msg
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }

        Optional<DTOPart> savedDTO = null;

        try {
            savedDTO = service.save(item);
            return new ResponseEntity<>(savedDTO.get(), HttpStatus.CREATED);
        } catch (Exception e) {
            DTOApiError apiError = new DTOApiError().setMsg(ERROR_MSG_SERVER_ERROR);
            //FIXME: add logger msg
            return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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