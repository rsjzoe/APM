package org.acme.application.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.acme.category.domain.Category;
import org.acme.departement.domain.Departement;

public class ApplicationHistory extends Application{
    private LocalDate modifiedAt;
    private  String modifiedBy;
    private Long applicationId;

    public ApplicationHistory(Long id, String name, String description, double businessValue, double costBuild, double costRun,
        Category category, LocalDateTime startDate, LocalDateTime lastUpdate, Status status, Time time, int userTotal,
        double note, Departement departement, LocalDate modifiedAt, String modifiedBy, Long applicationId) {
        super(id, name, description, businessValue, costBuild, costRun, category, startDate, lastUpdate, status, time, userTotal, note, departement);
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
        this.applicationId = applicationId;

    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }
    public LocalDate getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDate modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
