package org.acme.application.domain.model;

import java.time.LocalDateTime;

// objet miasa amlai projet
public class ApplicationBase {
    protected String name;
    protected String description;
    protected LocalDateTime startDate;
    protected LocalDateTime lastUpdate;
    protected Status status;
    protected int userTotal;

    public ApplicationBase() {
    }

    public ApplicationBase(String name, String description, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, int userTotal) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.lastUpdate = lastUpdate;
        this.status = status;
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
                ", startDate=" + startDate +
                ", lastUpdate=" + lastUpdate +
                ", status=" + status +
                ", userTotal=" + userTotal +
                '}';
    }
}
