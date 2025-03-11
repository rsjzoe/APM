package org.acme.application.domain.input;

import java.time.LocalDateTime;
import org.acme.application.domain.model.ApplicationBase;
import org.acme.application.domain.model.Status;
import org.acme.application.domain.model.Time;

public class UpdateApplicationRepositoryInput extends ApplicationBase {
    private Long categoryId;
    private Long departementId;
    private Long classeId;
    private Double note;
    protected Time time;

    public UpdateApplicationRepositoryInput() {
        super();
    }

    public UpdateApplicationRepositoryInput(String name, String description, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, Time time, int userTotal,
            Long categoryId, Long departementId, Long classeId, Double note) {
        super(name, description, startDate, lastUpdate, status, userTotal);
        this.categoryId = categoryId;
        this.departementId = departementId;
        this.classeId = classeId;
        this.note = note;
        this.time = time;
    }

    public UpdateApplicationRepositoryInput(UpdateApplicationServiceInput updated, Time time) {
        super(updated.getName(), updated.getDescription(), updated.getStartDate(), updated.getLastUpdate(),
                updated.getStatus(), updated.getUserTotal());
        this.categoryId = updated.getCategoryId();
        this.departementId = updated.getDepartementId();
        this.classeId = updated.getClasseId();
        this.note = updated.getNote();
        this.time = time;
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

    public Long getClasseId() {
        return classeId;
    }

    public void setClasseId(Long classeId) {
        this.classeId = classeId;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
