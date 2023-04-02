package com.hydroyura.productionmanager.sharedapi.dto;

import java.time.LocalDate;

public class DTOPart {

    private long id;

    private String number;

    private String name;

    private String type;

    private String status;

    private LocalDate created;

    private LocalDate lastUpdate;

    private String pdf;

    private String otherFile;

    public DTOPart() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getOtherFile() {
        return otherFile;
    }

    public void setOtherFile(String otherFile) {
        this.otherFile = otherFile;
    }
}
