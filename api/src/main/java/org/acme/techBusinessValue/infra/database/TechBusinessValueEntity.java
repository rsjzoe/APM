package org.acme.techBusinessValue.infra.database;

import java.time.LocalDate;

import org.acme.application.infra.database.ApplicationEntity;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueOutput;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity(name = "tech_business_value")
public class TechBusinessValueEntity extends PanacheEntity {
    private double businessValue;
    private double technicalDebt;
    private LocalDate createdAt;
    @ManyToOne
    private ApplicationEntity application;

    public TechBusinessValueEntity() {
    }

    public TechBusinessValueEntity(double businessValue, double technicalDebt, LocalDate createdAt) {
        this.businessValue = businessValue;
        this.technicalDebt = technicalDebt;
        this.createdAt = createdAt;
    }

    public TechBusinessValueOutput toTechBusinessValueOutput() {
        return new TechBusinessValueOutput(id, businessValue, technicalDebt, createdAt, application.id);
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "TechBusinessValueEntity{" +
                "businessValue=" + businessValue +
                ", technicalDebt=" + technicalDebt +
                ", createdAt=" + createdAt +
                '}';
    }
}
