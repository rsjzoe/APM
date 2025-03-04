package org.acme.cost.domain.model;

import java.time.LocalDateTime;

import org.acme.application.domain.model.Application;

public class Cost {
    private Long id;
    private double costBuild;
    private double costRun;
    private LocalDateTime createdAt;
    private Application application;

    public Cost(Long id, double costBuild, double costRun, LocalDateTime createdAt, Application application) {
        this.id = id;
        this.costBuild = costBuild;
        this.costRun = costRun;
        this.createdAt = createdAt;
        this.application = application;
    }

    public Long getId() {
        return id;
    }

    public double getCostBuild() {
        return costBuild;
    }

    public double getCostRun() {
        return costRun;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Application getApplication() {
        return application;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCostBuild(double costBuild) {
        this.costBuild = costBuild;
    }

    public void setCostRun(double costRun) {
        this.costRun = costRun;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    @Override
    public String toString() {
        return "Cost{" +
                "id=" + id +
                ", costBuild=" + costBuild +
                ", costRun=" + costRun +
                ", createdAt=" + createdAt +
                ", application=" + application +
                '}';
    }
}
