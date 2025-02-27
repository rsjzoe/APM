package org.acme.cost;

import java.time.LocalDate;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity(name = "cost")
public class CostEntity extends PanacheEntity {
    private double costBuild;
    private double costRun;
    private LocalDate createdAt;

    public CostEntity() {
    }

    public CostEntity(double costBuild, double costRun, LocalDate createdAt) {
        this.costBuild = costBuild;
        this.costRun = costRun;
        this.createdAt = createdAt;
    }

    public Cost toCost() {
        return new Cost(id, costBuild, costRun, createdAt);
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

    @Override
    public String toString() {
        return "CostEntity{" +
                "costBuild=" + costBuild +
                ", costRun=" + costRun +
                ", createdAt=" + createdAt +
                '}';
    }
}
