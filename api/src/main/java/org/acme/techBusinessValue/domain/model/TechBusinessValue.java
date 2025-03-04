package org.acme.techBusinessValue.domain.model;

import java.time.LocalDateTime;

import org.acme.application.domain.model.Application;

public class TechBusinessValue {
    private Long id;
    private double businessValue;
    private double technicalDebt;
    private LocalDateTime createdAt;
    private Application application;

    public TechBusinessValue(Long id, double businessValue, double technicalDebt, LocalDateTime createdAt, Application application) {
        this.id = id;
        this.businessValue = businessValue;
        this.technicalDebt = technicalDebt;
        this.createdAt = createdAt;
        this.application = application;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Application getApplication() {
        return application;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBusinessValue(double businessValue) {
        this.businessValue = businessValue;
    }

    public void setTechnicalDebt(double technicalDebt) {
        this.technicalDebt = technicalDebt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    @Override
    public String toString() {
        return "TechBusinessValue{" +
                "id=" + id +
                ", businessValue=" + businessValue +
                ", technicalDebt=" + technicalDebt +
                ", createdAt=" + createdAt +
                ", application=" + application +
                '}';
    }
}
