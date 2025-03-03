package org.acme.application.infra.database;

import java.time.LocalDateTime;

import org.acme.application.domain.model.ApplicationHistory;
import org.acme.application.domain.model.input.CreateApplicationHistoryInput;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Entity
public class ApplicationHistoryEntity extends PanacheEntity {
    private String description;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
    @ManyToOne(fetch = FetchType.LAZY)
    private ApplicationEntity applicationEntity;

    public ApplicationHistoryEntity() {
    }

    public ApplicationHistoryEntity( LocalDateTime modifiedAt, String modifiedBy,String description, ApplicationEntity applicationEntity) {
        this.description = description;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
        this.applicationEntity = applicationEntity;
    }

    public ApplicationHistoryEntity(CreateApplicationHistoryInput app) {
        this.description = app.getDescription();
        this.modifiedAt = LocalDateTime.now();
        this.modifiedBy = "system";
        this.applicationEntity = ApplicationEntityHelper.entityFromId(app.getApplicationId());
    }

    public ApplicationHistory toApplicationHistory() {
        return new ApplicationHistory(this.getModifiedAt().toLocalDate(), this.getModifiedBy(), this.getDescription(), applicationEntity.toApplicationOutput());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public ApplicationEntity getApplicationEntity() {
        return applicationEntity;
    }

    public void setApplicationEntity(ApplicationEntity applicationEntity) {
        this.applicationEntity = applicationEntity;
    }
}
