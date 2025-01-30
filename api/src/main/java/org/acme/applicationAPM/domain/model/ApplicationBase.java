package org.acme.applicationAPM.domain.model;

import java.time.LocalDateTime;

// objet miasa amlai projet
public class ApplicationBase {
    protected String name;
    protected String description;
    protected double businessValue; // vola napidirin'ilay app
    protected double costBuild;
    protected double costRun;
    protected LocalDateTime startDate;
    protected LocalDateTime lastUpdate;
    protected Status status;
    protected Time time;
    protected int userTotal;

    public ApplicationBase() {
    }

    public ApplicationBase(String name, String description, double businessValue, double costBuild, double costRun, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, Time time, int userTotal) {
        this.name = name;
        this.description = description;
        this.businessValue = businessValue;
        this.costBuild = costBuild;
        this.costRun = costRun;
        this.startDate = startDate;
        this.lastUpdate = lastUpdate;
        this.status = status;
        this.time = time;
        this.userTotal = userTotal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBusinessValue() {
        return businessValue;
    }

    public void setBusinessValue(double businessValue) {
        this.businessValue = businessValue;
    }

    public double getCostBuild() {
        return costBuild;
    }

    public void setCostBuild(double costBuild) {
        this.costBuild = costBuild;
    }

    public double getCostRun() {
        return costRun;
    }

    public void setCostRun(double costRun) {
        this.costRun = costRun;
    }


    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }



    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getUserTotal() {
        return userTotal;
    }

    public void setUserTotal(int userTotal) {
        this.userTotal = userTotal;
    }

    
    

    @Override
    public String toString() {
        return "Application{" +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", businessValue=" + businessValue +
                ", costBuild=" + costBuild +
                ", costRun=" + costRun +
                ", startDate=" + startDate +
                ", lastUpdate=" + lastUpdate +
                ", status=" + status +
                ", time=" + time +
                ", userTotal=" + userTotal +
                '}';
    }
}
