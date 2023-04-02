package com.hydroyura.productionmanager.archive.services.observer;

public interface IPublisher {

    void registerObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers(PublisherMessage message);

}
