package org.acme.cost.infra.database;

import java.time.LocalDateTime;

import org.acme.application.infra.database.ApplicationEntity;
import org.acme.application.infra.database.ApplicationEntityHelper;
import org.acme.cost.domain.model.input.CreateCostInput;
import org.acme.cost.domain.model.output.CostOutput;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity(name = "cost")
public class CostEntity extends PanacheEntity {
    private double costBuild;
    private double costRun;
    private LocalDateTime createdAt;
    @ManyToOne
    private ApplicationEntity application;

    public CostEntity() {
    }

    public CostEntity(double costBuild, double costRun, LocalDateTime createdAt) {
        this.costBuild = costBuild;
        this.costRun = costRun;
        this.createdAt = createdAt;
    }

    public CostEntity(CreateCostInput data){
        this.costBuild = data.getCostBuild();
        this.costRun = data.getCostRun();
        this.createdAt = LocalDateTime.now();
        if (data.getApplicationId() != null) {
            
            this.application = ApplicationEntityHelper.entityFromId(data.getApplicationId());
        }
    }

    public CostOutput toCostOutput() {
        return new CostOutput(id, application == null ? null : application.id, costBuild, costRun, createdAt);
    }

    public CostOutput toCostOutputWithoutApp(){
        return new CostOutput(id, null, costBuild, costRun, createdAt);
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ApplicationEntity getApplication() {
        return application;
    }

    public void setApplication(ApplicationEntity application) {
        this.application = application;
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
