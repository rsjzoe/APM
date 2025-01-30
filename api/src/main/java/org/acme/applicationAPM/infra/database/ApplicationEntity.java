package org.acme.applicationAPM.infra.database;

import java.time.LocalDateTime;

import org.acme.applicationAPM.domain.input.CreateApplicationInput;
import org.acme.applicationAPM.domain.input.UpdateApplicationInput;
import org.acme.applicationAPM.domain.model.Application;
import org.acme.applicationAPM.domain.model.Status;
import org.acme.applicationAPM.domain.model.Time;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class ApplicationEntity extends PanacheEntity {
    public String name;
    public String description;
    public double businessValue; // vola napidirin'ilay app
    public double costBuild;
    public double costRun;
    public String userTeam;
    // public Category category;
    // public List<Technology> technologies;
    public LocalDateTime startDate;
    public LocalDateTime lastUpdate;
    // public Performance performance;
    public Status status;
    public Time time;
    public double note;
    public int userTotal;

    public ApplicationEntity() {
    }

    public ApplicationEntity(String name, String description, double businessValue, double costBuild, double costRun,
            String userTeam, LocalDateTime startDate, LocalDateTime lastUpdate, Status status,
            Time time, int userTotal, double note) {
        this.name = name;
        this.description = description;
        this.businessValue = businessValue;
        this.costBuild = costBuild;
        this.costRun = costRun;
        this.userTeam = userTeam;
        this.startDate = startDate;
        this.lastUpdate = lastUpdate;
        this.status = status;
        this.time = time;
        this.userTotal = userTotal;
        this.note = note;
    }

    public ApplicationEntity(CreateApplicationInput app) {
        this.name = app.getName();
        this.description = app.getDescription();
        this.businessValue = app.getBusinessValue();
        this.costBuild = app.getCostBuild();
        this.costRun = app.getCostRun();
        this.userTeam = app.getUserTeam();
        this.startDate = app.getStartDate();
        this.lastUpdate = app.getLastUpdate();
        this.status = app.getStatus();
        this.time = app.getTime();
        this.userTotal = app.getUserTotal();
        this.note = 0;
    }

    public Application toApplication() {
        return new Application(id, name, description, businessValue, costBuild, costRun, userTeam, null,
                startDate, lastUpdate, null, status, time, userTotal, note);
    }

    public ApplicationEntity updateData(UpdateApplicationInput app) {
        this.name = app.getName();
        this.description = app.getDescription();
        this.businessValue = app.getBusinessValue();
        this.costBuild = app.getCostBuild();
        this.costRun = app.getCostRun();
        this.userTeam = app.getUserTeam();
        this.startDate = app.getStartDate();
        this.lastUpdate = app.getLastUpdate();
        this.status = app.getStatus();
        this.time = app.getTime();
        this.userTotal = app.getUserTotal();
        this.note = app.getNote();
        return this;
    }

}
