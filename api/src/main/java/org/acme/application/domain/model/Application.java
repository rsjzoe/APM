package org.acme.application.domain.model;

import java.time.LocalDateTime;

import org.acme.category.domain.Category;
import org.acme.cost.Cost;
import org.acme.departement.domain.Departement;
import org.acme.techBusinessValue.TechBusinessValue;

public class Application extends ApplicationBase {
    protected Long id;
    protected double note;
    protected Category category;
    protected Departement departement;
    protected Cost cost;
    protected TechBusinessValue techBusinessValue;

    public Application() {

    }

    public Application(Long id, String name, String description,
            Category category, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, Time time, int userTotal, double note, Departement departement, Cost cost, TechBusinessValue techBusinessValue) {
        super(name, description, startDate,
                lastUpdate, status, time, userTotal);
        this.id = id;
        this.note = note;
        this.category = category;
        this.departement = departement;
        this.cost = cost;
        this.techBusinessValue = techBusinessValue;
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

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public TechBusinessValue getTechBusinessValue() {
        return techBusinessValue;
    }

    public void setTechBusinessValue(TechBusinessValue techBusinessValue) {
        this.techBusinessValue = techBusinessValue;
    }

}
