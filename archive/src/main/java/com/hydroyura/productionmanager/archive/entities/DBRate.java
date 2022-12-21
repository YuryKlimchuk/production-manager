package com.hydroyura.productionmanager.archive.entities;

import javax.persistence.*;

@Entity
@Table(name = "rates")
public class DBRate extends DBBaseEntity {

    @ManyToOne
    @JoinColumn(name = "assembly_id")
    private DBPart assembly;

    @ManyToOne
    @JoinColumn(name="part_id")
    private DBPart element;

    @Column
    private long rate;

    @Column
    private long replacement;


    public DBRate() {}


    public DBPart getAssembly() {
        return assembly;
    }

    public void setAssembly(DBPart assembly) {
        this.assembly = assembly;
    }

    public DBPart getElement() {
        return element;
    }

    public void setElement(DBPart element) {
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
