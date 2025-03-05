package org.acme.cost.domain.model.input;

public class CreateCostWithoutApp {
    private double costBuild;
    private double costRun;

    public CreateCostWithoutApp(double costBuild, double costRun) {
        this.costBuild = costBuild;
        this.costRun = costRun;
    }

    public double getCostBuild() {
        return costBuild;
    }

    public double getCostRun() {
        return costRun;
    }

    public void setCostBuild(double costBuild) {
        this.costBuild = costBuild;
    }

    public void setCostRun(double costRun) {
        this.costRun = costRun;
    }

}
