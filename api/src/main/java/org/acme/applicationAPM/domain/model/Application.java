package org.acme.applicationAPM.domain.model;

import java.time.LocalDateTime;

import org.acme.category.domain.Category;
import org.acme.departement.domain.Departement;

public class Application extends ApplicationBase {
    protected Long id;
    protected double note;
    protected Category category;
    protected Departement departement;

    public Application() {

    }

    public Application(Long id, String name, String description, double businessValue, double costBuild, double costRun,
            Category category, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, Time time, int userTotal, double note, Departement departement) {
        super(name, description, businessValue, costBuild, costRun, startDate,
                lastUpdate, status, time, userTotal);
        this.id = id;
        this.note = note;
        this.category = category;
        this.departement = departement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

}
