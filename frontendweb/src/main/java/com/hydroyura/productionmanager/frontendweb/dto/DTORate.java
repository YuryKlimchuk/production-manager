package com.hydroyura.productionmanager.frontendweb.dto;

import com.hydroyura.productionmanager.sharedapi.dto.DTOPart;

public class DTORate {

    private DTOPart assembly;

    private DTOPart element;

    private long rate;

    private long replacement;


    public DTORate() {}


    public DTOPart getAssembly() {
        return assembly;
    }

    public void setAssembly(DTOPart assembly) {
        this.assembly = assembly;
    }

    public DTOPart getElement() {
        return element;
    }

    public void setElement(DTOPart element) {
        this.element = element;
    }

    public long getRate() {
        return rate;
    }

    public void setRate(long rate) {
        this.rate = rate;
    }

    public long getReplacement() {
        return replacement;
    }

    public void setReplacement(long replacement) {
        this.replacement = replacement;
    }
}
