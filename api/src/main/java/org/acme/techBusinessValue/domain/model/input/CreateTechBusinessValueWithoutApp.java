package org.acme.techBusinessValue.domain.model.input;

public class CreateTechBusinessValueWithoutApp {
    private double businessValue;
    private double technicalDebt;

    public CreateTechBusinessValueWithoutApp() {
    }

    public CreateTechBusinessValueWithoutApp(double businessValue, double technicalDebt) {
        this.businessValue = businessValue;
        this.technicalDebt = technicalDebt;
    }

    public double getBusinessValue() {
        return businessValue;
    }

    public void setBusinessValue(double businessValue) {
        this.businessValue = businessValue;
    }

    public double getTechnicalDebt() {
        return technicalDebt;
    }

    public void setTechnicalDebt(double technicalDebt) {
        this.technicalDebt = technicalDebt;
    }

    
}
