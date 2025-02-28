package org.acme.techBusinessValue.domain.model.output;

import java.time.LocalDate;

public class TechBusinessValueOutput {
    private Long id;
    private double businessValue;
    private double technicalDebt;
    private LocalDate createdAt;
    private Long applicationId;

    public TechBusinessValueOutput(Long id, double businessValue, double technicalDebt, LocalDate createdAt, Long applicationId) {
        this.id = id;
        this.businessValue = businessValue;
        this.technicalDebt = technicalDebt;
        this.createdAt = createdAt;
        this.applicationId = applicationId;
    }

    public Long getId() {
        return id;
    }

    public double getBusinessValue() {
        return businessValue;
    }

    public double getTechnicalDebt() {
        return technicalDebt;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setBusinessValue(double businessValue) {
        this.businessValue = businessValue;
    }

    public void setTechnicalDebt(double technicalDebt) {
        this.technicalDebt = technicalDebt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }
}
