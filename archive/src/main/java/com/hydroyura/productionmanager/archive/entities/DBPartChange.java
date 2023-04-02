package com.hydroyura.productionmanager.archive.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "parts_changes")
public class DBPartChange extends DBBaseEntity {

    @Column(name = "part_id")
    private long partId;
    private LocalDate update;
    private String object;

    private String action;


    public DBPartChange() {}

    public long getPartId() {
        return partId;
    }

    public void setPartId(long partId) {
        this.partId = partId;
    }

    public LocalDate getUpdate() {
        return update;
    }

    public void setUpdate(LocalDate update) {
        this.update = update;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}