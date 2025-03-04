package org.acme.application.domain.model.output;

import java.time.LocalDateTime;

import org.acme.application.domain.model.ApplicationBase;
import org.acme.application.domain.model.Status;
import org.acme.application.domain.model.Time;
import org.acme.category.domain.output.CategoryODAChildOutput;
import org.acme.cost.domain.model.output.CostOutput;
import org.acme.departement.domain.Departement;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueOutput;

public class ApplicationHistoryOutput extends ApplicationBase {
    private Long id;
    private Long appId;
    protected double note;
    protected CategoryODAChildOutput category;
    protected Departement departement;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
    private String description;
    private String descriptionHistory;
    protected CostOutput cost;
    protected TechBusinessValueOutput techBusinessValue;

    public ApplicationHistoryOutput() {
    }

    public ApplicationHistoryOutput(Long id, Long appId, String name, String description, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, Time time, int userTotal, double note, CategoryODAChildOutput category,
            Departement departement, LocalDateTime modifiedAt, String modifiedBy, String descriptionHistory, CostOutput cost,
            TechBusinessValueOutput techBusinessValue) {
        super(name, description, startDate, lastUpdate, status, time, userTotal);
        this.id = id;
        this.appId = appId;
        this.note = note;
        this.category = category;
        this.departement = departement;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
        this.descriptionHistory = descriptionHistory;
        this.cost = cost;
        this.techBusinessValue = techBusinessValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public CategoryODAChildOutput getCategory() {
        return category;
    }

    public void setCategory(CategoryODAChildOutput category) {
        this.category = category;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionHistory() {
        return descriptionHistory;
    }

    public void setDescriptionHistory(String descriptionHistory) {
        this.descriptionHistory = descriptionHistory;
    }

    public CostOutput getCost() {
        return cost;
    }

    public void setCost(CostOutput cost) {
        this.cost = cost;
    }

    public TechBusinessValueOutput getTechBusinessValue() {
        return techBusinessValue;
    }

    public void setTechBusinessValue(TechBusinessValueOutput techBusinessValue) {
        this.techBusinessValue = techBusinessValue;
    }
}
