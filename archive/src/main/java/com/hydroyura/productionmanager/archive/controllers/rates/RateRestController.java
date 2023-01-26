package com.hydroyura.productionmanager.archive.controllers.rates;

import com.hydroyura.productionmanager.sharedapi.dto.DTORate;
import com.hydroyura.productionmanager.sharedapi.feigninterfaces.IArchiveRateRestController;
import org.springframework.http.ResponseEntity;

public class RateRestController implements IArchiveRateRestController<DTORate> {

    @Override
    public ResponseEntity<?> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<?> getByAssembly(String rawAssemblyId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getByPart(String partId) {
        return null;
    }

    @Override
    public ResponseEntity<?> create(DTORate item) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(String rawRateId) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(String rawRateId, DTORate item) {
        return null;
    }
}
