package com.hydroyura.productionmanager.archive.services.parts;

import com.hydroyura.productionmanager.archive.entities.DBBaseEntity;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface IPartService<Entity extends DBBaseEntity, DTO> {

    public Optional<DTO> getItemById(Long id);
    public Collection<DTO> getAll(Map<String, Object> filter);

    public Optional<DTO> delete(Long id);

    /*
    public Optional<DTO> update(Entity entity);
    public Optional<DTO> delete(Long id);
    public Optional<DTO> save(Entity entity);
    public Collection<DTO> getAllByFilter(String type, Map<String, Object> filter);
    public Collection<DTO> getAllByType(String type);
    */
}
