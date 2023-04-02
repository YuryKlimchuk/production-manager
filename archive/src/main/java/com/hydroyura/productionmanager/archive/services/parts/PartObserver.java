package com.hydroyura.productionmanager.archive.services.parts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.productionmanager.archive.entities.DBPart;
import com.hydroyura.productionmanager.archive.entities.DBPartChange;
import com.hydroyura.productionmanager.archive.repositories.BaseRepository;
import com.hydroyura.productionmanager.archive.services.observer.IObserver;
import com.hydroyura.productionmanager.archive.services.observer.IPublisher;
import com.hydroyura.productionmanager.archive.services.observer.PublisherMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PartObserver implements IObserver {


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired @Qualifier(value = "PartChangeRepository")
    private BaseRepository<DBPartChange, Long> repository;

    @Autowired @Qualifier(value = "PartService")
    private IPublisher publisher;

    @PostConstruct
    void init() {
        publisher.registerObserver(this);
    }


    // Need to refactoring
    @Override
    public void update(PublisherMessage message) {

        DBPart dbPart = message.getMessageBody(DBPart.class).get();

        DBPartChange dbPartChange = new DBPartChange();
        dbPartChange.setPartId(dbPart.getId());
        dbPartChange.setUpdate(dbPart.getLastUpdate());
        dbPartChange.setObject(null);

        try {
            dbPartChange.setObject(objectMapper.writeValueAsString(dbPart));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        repository.save(dbPartChange);
    }

}
