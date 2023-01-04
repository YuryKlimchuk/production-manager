package com.hydroyura.productionmanager.sharedapi.dto;

public class DTOApiError {
    private String msg;

    public DTOApiError() {}

    public String getMsg() {
        return msg;
    }

    public DTOApiError setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
