package org.acme.cost;

import java.time.LocalDate;

public class Cost {
    private Long id;
    private double costBuild;
    private double costRun;
    private LocalDate createdAt;

    public Cost(Long id, double costBuild, double costRun, LocalDate createdAt) {
        this.id = id;
        this.costBuild = costBuild;
        this.costRun = costRun;
        this.createdAt = createdAt;
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

    public LocalDate getCreatedAt() {
        return createdAt;
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

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Cost{" +
                "id=" + id +
                ", costBuild=" + costBuild +
                ", costRun=" + costRun +
                ", createdAt=" + createdAt +
                '}';
    }
}
