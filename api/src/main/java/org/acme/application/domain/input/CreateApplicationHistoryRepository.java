package org.acme.application.domain.input;

import java.time.LocalDateTime;

import org.acme.application.domain.model.ApplicationBase;
import org.acme.application.domain.model.Status;
import org.acme.application.domain.model.Time;
import org.acme.application.domain.output.ApplicationOutput;

public class CreateApplicationHistoryRepository extends ApplicationBase {
    private Long appId;
    protected double note;
    protected Long categoryId;
    protected Long departementId;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
    private String descriptionHistory;
    protected Long costId;
    protected Long techBusinessValueId;

    public CreateApplicationHistoryRepository() {
        super();
    }

    public CreateApplicationHistoryRepository(String name, String description, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, Time time, int userTotal, Long appId, double note,
            Long categoryId, Long departementId, LocalDateTime modifiedAt, String modifiedBy, String descriptionHistory,
            Long costId, Long techBusinessValueId) {
        super(name, description, startDate, lastUpdate, status, time, userTotal);
        this.appId = appId;
        this.note = note;
        this.categoryId = categoryId;
        this.departementId = departementId;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
        this.descriptionHistory = descriptionHistory;
        this.costId = costId;
        this.techBusinessValueId = techBusinessValueId;
    }

    public CreateApplicationHistoryRepository(ApplicationOutput app, String modifiedBy, String descriptionHistory) {
        this(app.getName(), app.getDescription(), app.getStartDate(), app.getLastUpdate(), app.getStatus(),
                app.getTime(), app.getUserTotal(), app.getId(), app.getNote(), app.getCategory().getId(),
                app.getDepartement().getId(), LocalDateTime.now(), modifiedBy, descriptionHistory,
                app.getCurrentCost().getId(), app.getCurrentTechBusinessValue().getId());
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getDepartementId() {
        return departementId;
    }

    public void setDepartementId(Long departementId) {
        this.departementId = departementId;
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

    public Long getCostId() {
        return costId;
    }

    public void setCostId(Long costId) {
        this.costId = costId;
    }

    public Long getTechBusinessValueId() {
        return techBusinessValueId;
    }

    public void setTechBusinessValueId(Long techBusinessValueId) {
        this.techBusinessValueId = techBusinessValueId;
    }

    public String getDescriptionHistory() {
        return descriptionHistory;
    }

    public void setDescriptionHistory(String descriptionHistory) {
        this.descriptionHistory = descriptionHistory;
    }
}
