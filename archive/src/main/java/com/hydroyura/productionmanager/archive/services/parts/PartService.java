package com.hydroyura.productionmanager.archive.services.parts;

import com.hydroyura.productionmanager.archive.dto.DTOPart;
import com.hydroyura.productionmanager.archive.entities.DBPart;
import com.hydroyura.productionmanager.archive.entities.QDBPart;
import com.hydroyura.productionmanager.archive.repositories.BaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service(value = "PartService")
public class PartService implements IPartService<DBPart, DTOPart> {

    private Class<DTOPart> dtoType = DTOPart.class;

    @Autowired @Qualifier(value = "PartRepository")
    private BaseRepository<DBPart, Long> repository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Optional<DTOPart> getItemById(Long id) {

        Optional<DBPart> entity = repository.findById(id);
        if(entity.isEmpty()) return Optional.empty();

        DTOPart dto = modelMapper.map(entity.get(), dtoType);
        return Optional.of(dto);
    }

    @Override
    public Collection<DTOPart> getAllByType(String type) {
        return
                StreamSupport.stream(repository.findAll(QDBPart.dBPart.type.eq(type)).spliterator(), false)
                        .map(entity -> modelMapper.map(entity, dtoType))
                        .collect(Collectors.toList());
    }



    /*
    @Override
    public Optional<DTO> update(Entity entity) {
        if(!repository.existsById(entity.getId())) return Optional.empty();

        objectMapper.convertValue(entity, new TypeReference<DTO>() {});

        return Optional.of(repository.save(entity));
    }

    @Override
    public Optional<DTO> delete(Long id) {
        if(!repository.existsById(id)) return Optional.empty();
        Entity entityForDelete = repository.findById(id).get();
        repository.delete(entityForDelete);
        return Optional.of(entityForDelete);
    }

    @Override
    public Optional<DTO> save(Entity entity) {
        return Optional.of(repository.save(entity));
    }

    @Override
    public Collection<DTO> getAllByFilter(String type, Map<String, Object> filter) {
        return Collections.EMPTY_LIST;
    }

    @Override
    public Collection<DTO> getAllByType(String type) {
        return Collections.EMPTY_LIST;
    }

     */
}
