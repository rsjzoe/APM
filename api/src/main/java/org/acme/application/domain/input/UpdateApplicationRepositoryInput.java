package org.acme.application.domain.input;

import java.time.LocalDateTime;
import org.acme.application.domain.model.ApplicationBase;
import org.acme.application.domain.model.Status;
import org.acme.application.domain.model.Time;

public class UpdateApplicationRepositoryInput extends ApplicationBase {
    private Long categoryId;
    private Long departementId;
    private Double note;

    public UpdateApplicationRepositoryInput() {
        super();
    }

    public UpdateApplicationRepositoryInput(String name, String description, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, Time time, int userTotal,
            Long categoryId, Long departementId, Double note) {
        super(name, description, startDate, lastUpdate, status, time, userTotal);
        this.categoryId = categoryId;
        this.departementId = departementId;
        this.note = note;
    }

    public UpdateApplicationRepositoryInput(UpdateApplicationServiceInput updated) {
        super(updated.getName(), updated.getDescription(), updated.getStartDate(), updated.getLastUpdate(),
                updated.getStatus(), updated.getTime(), updated.getUserTotal());
        this.categoryId = updated.getCategoryId();
        this.departementId = updated.getDepartementId();
        this.note = updated.getNote();
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

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }
}
