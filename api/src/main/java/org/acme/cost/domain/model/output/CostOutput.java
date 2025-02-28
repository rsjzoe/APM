package org.acme.cost.domain.model.output;

import java.time.LocalDate;


public class CostOutput {
    private Long id;
    private Long applicationId;
    private double costBuild;
    private double costRun;
    private LocalDate createdAt;

    public CostOutput(Long id, Long applicationId, double costBuild, double costRun, LocalDate createdAt) {
        this.id = id;
        this.applicationId = applicationId;
        this.costBuild = costBuild;
        this.costRun = costRun;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

}
