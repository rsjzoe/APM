package org.acme.application.domain.input;

import java.time.LocalDateTime;

import org.acme.application.domain.model.ApplicationBase;
import org.acme.application.domain.model.Status;
import org.acme.application.domain.model.Time;
import org.acme.category.domain.Category;
import org.acme.departement.domain.Departement;

public class CreateApplicationHistoryInput extends ApplicationBase{
    private  String modifiedBy;
    private Long applicationId;
    protected double note;
    protected Category category;
    protected Departement departement;

    
    public CreateApplicationHistoryInput(String name, String description, double businessValue, double costBuild, double costRun, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, Time time, int userTotal, String modifiedBy, Long applicationId, double note, Category category, Departement departement) {
        super(name, description, businessValue, costBuild, costRun, startDate, lastUpdate, status, time, userTotal);
        this.modifiedBy = modifiedBy;
        this.applicationId = applicationId;
        this.note = note;
        this.category = category;
        this.departement = departement;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

}
