package org.acme.techBusinessValue.domain.model.input;

public class CreateTechBusinessValue {
    private double businessValue;
    private double technicalDebt;
    private Long appId;

    public CreateTechBusinessValue() {
    }

    public CreateTechBusinessValue(double businessValue, double technicalDebt, Long appId) {
        this.businessValue = businessValue;
        this.technicalDebt = technicalDebt;
        this.appId = appId;
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

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }
}
