package com.hydroyura.productionmanager.frontendweb.controller;

import org.springframework.ui.Model;

import java.util.Collection;
import java.util.Map;

public enum URL implements IPartController {

    STATE() {
    @Override
    public String displayList(String type, Model model) {
        return null;
    }

    @Override
    public String displayItem(String id, Model model) {
        return null;
    }

    @Override
    public String displaySpecification(String id, Model model) {
        return null;
    }

    @Override
    public Collection<Map<String, Object>> getItems(String type) {
        return null;
    }},
    ;


}
