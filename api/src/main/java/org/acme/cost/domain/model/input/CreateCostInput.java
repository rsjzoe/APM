package org.acme.cost.domain.model.input;


public class CreateCostInput {
    private double costBuild;
    private double costRun;
    private Long applicationId;

    public CreateCostInput(double costBuild, double costRun,  Long applicationId) {
        this.costBuild = costBuild;
        this.costRun = costRun;
        this.applicationId = applicationId;
    }

    public double getCostBuild() {
        return costBuild;
    }

    public double getCostRun() {
        return costRun;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setCostBuild(double costBuild) {
        this.costBuild = costBuild;
    }

    public void setCostRun(double costRun) {
        this.costRun = costRun;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }
}
