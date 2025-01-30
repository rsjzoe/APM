package org.acme.applicationAPM.domain.model;

import java.time.LocalDateTime;

public class Application extends ApplicationBase {
    private Long id;
    private double note;

    

    public Application() {
    }

    public Application(Long id, String name, String description, double businessValue, double costBuild, double costRun,
            String userTeam, Category category, LocalDateTime startDate,
            LocalDateTime lastUpdate, Performance performance, Status status, Time time, int userTotal,double note) {
        super(name, description, businessValue, costBuild, costRun, userTeam, category, startDate,
                lastUpdate, performance, status, time, userTotal);
        this.id = id;
        this.note = note;
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
}
