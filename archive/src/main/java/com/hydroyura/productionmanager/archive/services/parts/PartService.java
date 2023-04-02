package com.hydroyura.productionmanager.archive.services.parts;

import com.hydroyura.productionmanager.archive.entities.DBPart;
import com.hydroyura.productionmanager.archive.entities.QDBPart;
import com.hydroyura.productionmanager.archive.repositories.BaseRepository;
import com.hydroyura.productionmanager.archive.services.observer.IObserver;
import com.hydroyura.productionmanager.archive.services.observer.IPublisher;
import com.hydroyura.productionmanager.archive.services.observer.PublisherMessage;
import com.hydroyura.productionmanager.sharedapi.dto.DTOPart;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service(value = "PartService")
public class PartService implements IPartService<DBPart, DTOPart>, IPublisher {

    private Class<DTOPart> dtoType = DTOPart.class;
    private Class<DBPart> entityType = DBPart.class;

    @Autowired @Qualifier(value = "PartRepository")
    private BaseRepository<DBPart, Long> repository;

    @Autowired
    private ModelMapper modelMapper;

    private Collection<IObserver> observers = new ArrayList<>();


    @Override
    public Optional<DTOPart> getItemById(Long id) {

        Optional<DBPart> entity = repository.findById(id);
        if(entity.isEmpty()) return Optional.empty();

        DTOPart dto = modelMapper.map(entity.get(), dtoType);
        return Optional.of(dto);
    }

    @Override
    public Collection<DTOPart> getAll(Map<String, Object> filter) {

        String type = (String) filter.getOrDefault("type", "PART");
        String number = (String) filter.getOrDefault("number", "");
        String name = (String) filter.getOrDefault("name", "");
        String status = (String) filter.getOrDefault("status", "");

        BooleanExpression typeExp = QDBPart.dBPart.type.eq(type);
        BooleanExpression nameExp = QDBPart.dBPart.name.contains(name);
        BooleanExpression numberExp = QDBPart.dBPart.number.contains(number);

        Predicate predicate = typeExp.and(nameExp.and(numberExp));

        return
                StreamSupport.stream(repository.findAll(predicate).spliterator(), false)
                        .map(entity -> modelMapper.map(entity, dtoType))
                        .collect(Collectors.toList());
    }

    @Override
    public Optional<DTOPart> delete(Long id) {
        // FIXME: checking
        repository.deleteById(id);
        PublisherMessage message = createMessage("DELETE", null);
        notifyObservers(message);
        return Optional.empty();
    }

    @Override
    public Optional<DTOPart> save(DTOPart dto) {
        DBPart entity = modelMapper.map(dto, entityType);
        DTOPart savedDTO = modelMapper.map(repository.save(entity), dtoType);
        PublisherMessage message = createMessage("CREATE", entity);
        notifyObservers(message);
        return Optional.of(savedDTO);
    }

    @Override
    public Optional<DTOPart> update(DTOPart dto) {
        DBPart entity = modelMapper.map(dto, entityType);
        DTOPart savedDTO = modelMapper.map(repository.save(entity), dtoType);
        PublisherMessage message = createMessage("UPDATE", entity);
        notifyObservers(message);
        return Optional.of(savedDTO);
    }

    @Override
    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(PublisherMessage message) {
        observers.forEach(observer -> observer.update(message));
    }

    private PublisherMessage createMessage(String actionType, DBPart part) {
        PublisherMessage message = new PublisherMessage()
                .setMessageBody(part)
                .setActionType(actionType);
        return message;
    };
}
