package com.hydroyura.productionmanager.archive.services.rates;

import com.hydroyura.productionmanager.archive.entities.DBBaseEntity;

import java.util.Collection;

public interface IRateService<Entity extends DBBaseEntity, DTO> {

    public Collection<DTO> getAllByAssemblyId(long id);

}
