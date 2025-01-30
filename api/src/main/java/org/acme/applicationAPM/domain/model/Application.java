package org.acme.applicationAPM.domain.model;

import java.time.LocalDateTime;

import org.acme.category.Category;

public class Application extends ApplicationBase {
    private Long id;
    private double note;
    private Category category;

    public Application() {

    }


    public Application(Long id, String name, String description, double businessValue, double costBuild, double costRun,
            String userTeam, Category category, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, Time time, int userTotal,double note) {
        super(name, description, businessValue, costBuild, costRun, userTeam, startDate,
                lastUpdate, status, time, userTotal);
        this.id = id;
        this.note = note;
        this.category = category;
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
}
