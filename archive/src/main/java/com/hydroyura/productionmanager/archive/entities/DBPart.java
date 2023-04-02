package com.hydroyura.productionmanager.archive.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "parts")
public class DBPart extends DBBaseEntity {

    @Column
    private String number;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private String status;

    private LocalDate created;

    @Column(name = "last_update")
    private LocalDate lastUpdate;

    @Column
    private String pdf;

    @Column(name = "other_file")
    private String otherFile;


    @OneToMany(mappedBy = "assembly", cascade = CascadeType.REMOVE) @JsonIgnore
    private Collection<DBRate> assemblies;

    @OneToMany(mappedBy = "element", cascade = CascadeType.REMOVE) @JsonIgnore
    private Collection<DBRate> elements;


    public DBPart() {}


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

    public Collection<DBRate> getAssemblies() {
        return assemblies;
    }

    public void setAssemblies(Collection<DBRate> assemblies) {
        this.assemblies = assemblies;
    }

    public Collection<DBRate> getElements() {
        return elements;
    }

    public void setElements(Collection<DBRate> elements) {
        this.elements = elements;
    }

}
