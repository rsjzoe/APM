package org.acme.application.domain.input;

import java.time.LocalDateTime;

import org.acme.application.domain.model.ApplicationBase;
import org.acme.application.domain.model.Status;
import org.acme.application.domain.model.Time;

public class CreateApplicationRepositoryInput extends ApplicationBase {
    private Long categoryId;
    private Long departementId;
    private Long classeId;
    protected Time time;

    public CreateApplicationRepositoryInput() {
        super();
    }

    public CreateApplicationRepositoryInput(String name, String description, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, Time time, int userTotal,
            Long categoryId, Long departementId, Long classeId) {
        super(name, description, startDate, lastUpdate, status, userTotal);
        this.categoryId = categoryId;
        this.departementId = departementId;
        this.classeId = classeId;
        this.time = time;
    }

    public CreateApplicationRepositoryInput(CreateApplicationServiceInput createApplicationServiceInput, Time time) {
        super(createApplicationServiceInput.getName(), createApplicationServiceInput.getDescription(),
                createApplicationServiceInput.getStartDate(), createApplicationServiceInput.getLastUpdate(),
                createApplicationServiceInput.getStatus(),
                createApplicationServiceInput.getUserTotal());
        this.categoryId = createApplicationServiceInput.getCategoryId();
        this.departementId = createApplicationServiceInput.getDepartementId();
        this.classeId = createApplicationServiceInput.getClasseId();
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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
