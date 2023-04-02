package com.hydroyura.productionmanager.archive.services.observer;

import java.util.Optional;

// FIXME: check null for getMessageBody
public class PublisherMessage {

    private Object body;
    private String actionType;

    public <T> Optional<T> getMessageBody(Class<T> clazz) {
        T obj = clazz.cast(body);
        return Optional.of(obj);
    }

    public PublisherMessage setMessageBody(Object object) {
        this.body = object;
        return this;
    }

    public PublisherMessage setActionType(String actionType) {
        this.actionType = actionType;
        return this;
    }

    public Optional<Object> getMessageBody() {
        return Optional.of(body);
    }

}
