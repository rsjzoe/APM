package org.acme.techBusinessValue.infra.database;

import java.time.LocalDateTime;

import org.acme.application.infra.database.ApplicationEntity;
import org.acme.application.infra.database.ApplicationEntityHelper;
import org.acme.techBusinessValue.domain.model.input.CreateTechBusinessValue;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueOutput;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tech_business_value")
public class TechBusinessValueEntity extends PanacheEntity {
    private double businessValue;
    private double technicalDebt;
    private LocalDateTime createdAt;
    @ManyToOne
    private ApplicationEntity application;


    public TechBusinessValueEntity(double businessValue, double technicalDebt, LocalDateTime createdAt) {
        this.businessValue = businessValue;
        this.technicalDebt = technicalDebt;
        this.createdAt = createdAt;
    }

    public TechBusinessValueEntity(CreateTechBusinessValue data) {
        this.businessValue = data.getBusinessValue();
        this.technicalDebt = data.getTechnicalDebt();
        if (data.getAppId() != null) {
            this.application = ApplicationEntityHelper.entityFromId(data.getAppId());
        }
        this.createdAt = LocalDateTime.now();
    }

    public TechBusinessValueOutput toTechBusinessValueOutput() {
        return new TechBusinessValueOutput(id, businessValue, technicalDebt, createdAt,
                application == null ? null : application.id);
    }

}
