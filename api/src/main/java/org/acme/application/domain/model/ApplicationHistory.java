package org.acme.application.domain.model;

import java.time.LocalDateTime;

import org.acme.category.domain.CategoryODAChild;
import org.acme.cost.domain.model.Cost;
import org.acme.departement.domain.Departement;
import org.acme.techBusinessValue.domain.model.TechBusinessValue;

public class ApplicationHistory extends ApplicationBase {
    private Long id;
    private Long appId;
    protected double note;
    protected CategoryODAChild category;
    protected Departement departement;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
    private String descriptionHistory;
    protected Cost cost;
    protected TechBusinessValue techBusinessValue;
    protected boolean isDeleted;

    public ApplicationHistory() { 
    }

    public ApplicationHistory(Long id, Long appId, String name, String description, LocalDateTime startDate,
        LocalDateTime lastUpdate, Status status, Time time, int userTotal, double note, CategoryODAChild category,
        Departement departement, LocalDateTime modifiedAt, String modifiedBy, String descriptionHistory, Cost cost,
        TechBusinessValue techBusinessValue, boolean isDeleted) {
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
        this.isDeleted = isDeleted;
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

    public CategoryODAChild getCategory() {
        return category;
    }

    public void setCategory(CategoryODAChild category) {
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

    public String getDescriptionHistory() {
        return descriptionHistory;
    }

    public void setDescriptionHistory(String descriptionHistory) {
        this.descriptionHistory = descriptionHistory;
    }

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public TechBusinessValue getTechBusinessValue() {
        return techBusinessValue;
    }

    public void setTechBusinessValue(TechBusinessValue techBusinessValue) {
        this.techBusinessValue = techBusinessValue;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
