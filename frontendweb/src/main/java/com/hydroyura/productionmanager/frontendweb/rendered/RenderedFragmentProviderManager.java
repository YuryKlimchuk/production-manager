package com.hydroyura.productionmanager.frontendweb.rendered;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class RenderedFragmentProviderManager {

    @Autowired
    private Collection<RenderedFragmentProvider> providers;

    private Map<String, Collection<RenderedFragmentProvider>> jsMap = new HashMap<>();


    @PostConstruct
    void init() {
        Collection<String> controllersId = new HashSet<>();
        providers.forEach(provider -> controllersId.addAll(provider.getControllersIds()));

        controllersId.forEach(id -> jsMap.putIfAbsent(id, new ArrayList<>()));

        providers.forEach(provider -> {
            provider.getControllersIds().forEach(id -> {
                Collection<RenderedFragmentProvider> temp = jsMap.get(id);
                if(temp != null && (provider.getRenderedFragment().getType().equals(RenderedFragmentType.JS_BEGIN))) {
                    temp.add(provider);
                }
            });
        });


    }

    public Collection<RenderedFragmentProvider> getJsForController(String controllerId) {
        return jsMap.getOrDefault(controllerId, Collections.emptyList());
    }

}
