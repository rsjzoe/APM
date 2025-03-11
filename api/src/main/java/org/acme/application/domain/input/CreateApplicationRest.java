package org.acme.application.domain.input;

import java.time.LocalDateTime;

import org.acme.application.domain.model.ApplicationBase;
import org.acme.application.domain.model.Status;
import org.acme.cost.domain.model.input.CreateCostWithoutApp;
import org.acme.techBusinessValue.domain.model.input.CreateTechBusinessValueWithoutApp;

public class CreateApplicationRest extends ApplicationBase {
    private Long categoryId;
    private Long departementId;
    private Long classeId;
    private CreateCostWithoutApp costWithoutApp;
    private CreateTechBusinessValueWithoutApp techBusinessValueWithoutApp;

    public CreateApplicationRest() {
        super();
    }

    public CreateApplicationRest(String name, String description, LocalDateTime startDate, LocalDateTime lastUpdate,
            Status status, int userTotal, Long categoryId, Long departementId, Long classeId,
            CreateCostWithoutApp costWithoutApp, CreateTechBusinessValueWithoutApp techBusinessValueWithoutApp) {
        super(name, description, startDate, lastUpdate, status, userTotal);
        this.categoryId = categoryId;
        this.departementId = departementId;
        this.classeId = classeId;
        this.costWithoutApp = costWithoutApp;
        this.techBusinessValueWithoutApp = techBusinessValueWithoutApp;
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

    public CreateCostWithoutApp getCostWithoutApp() {
        return costWithoutApp;
    }

    public void setCostWithoutApp(CreateCostWithoutApp costWithoutApp) {
        this.costWithoutApp = costWithoutApp;
    }

    public CreateTechBusinessValueWithoutApp getTechBusinessValueWithoutApp() {
        return techBusinessValueWithoutApp;
    }

    public void setTechBusinessValueWithoutApp(CreateTechBusinessValueWithoutApp techBusinessValueWithoutApp) {
        this.techBusinessValueWithoutApp = techBusinessValueWithoutApp;
    }

}
