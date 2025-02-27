package org.acme.techBusinessValue;

import java.time.LocalDate;

public class TechBusinessValue {
    private Long id;
    private double businessValue;
    private double technicalDebt;
    private LocalDate createdAt;

    public TechBusinessValue(Long id, double businessValue, double technicalDebt, LocalDate createdAt) {
        this.id = id;
        this.businessValue = businessValue;
        this.technicalDebt = technicalDebt;
        this.createdAt = createdAt;
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

    // toString method
    @Override
    public String toString() {
        return "TechBusinessValue{" +
                "id=" + id +
                ", businessValue=" + businessValue +
                ", technicalDebt=" + technicalDebt +
                ", createdAt=" + createdAt +
                '}';
    }
}
