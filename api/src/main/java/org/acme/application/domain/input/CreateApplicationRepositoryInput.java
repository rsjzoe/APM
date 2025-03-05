package org.acme.application.domain.input;

import java.time.LocalDateTime;

import org.acme.application.domain.model.ApplicationBase;
import org.acme.application.domain.model.Status;
import org.acme.application.domain.model.Time;

public class CreateApplicationRepositoryInput extends ApplicationBase {
    private Long categoryId;
    private Long departementId;

    public CreateApplicationRepositoryInput() {
        super();
    }

    public CreateApplicationRepositoryInput(String name, String description, LocalDateTime startDate,
                                       LocalDateTime lastUpdate, Status status, Time time, int userTotal,
                                       Long categoryId, Long departementId) {
        super(name, description, startDate, lastUpdate, status, time, userTotal);
        this.categoryId = categoryId;
        this.departementId = departementId;
    }

    public CreateApplicationRepositoryInput(CreateApplicationServiceInput createApplicationServiceInput) {
        super(createApplicationServiceInput.getName(), createApplicationServiceInput.getDescription(),
                createApplicationServiceInput.getStartDate(), createApplicationServiceInput.getLastUpdate(),
                createApplicationServiceInput.getStatus(), createApplicationServiceInput.getTime(),
                createApplicationServiceInput.getUserTotal());
        this.categoryId = createApplicationServiceInput.getCategoryId();
        this.departementId = createApplicationServiceInput.getDepartementId();
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
}
