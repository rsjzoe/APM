package org.acme.application.domain.input;

import java.time.LocalDateTime;

import org.acme.application.domain.model.ApplicationBase;
import org.acme.application.domain.model.Status;
import org.acme.application.domain.model.Time;
import org.acme.cost.domain.model.input.CreateCostWithoutApp;
import org.acme.techBusinessValue.domain.model.input.CreateTechBusinessValueWithoutApp;

public class UpdateApplicationServiceInput extends ApplicationBase {
    private Long categoryId;
    private Long departementId;
    private double note;
    private CreateCostWithoutApp costWithoutApp;
    private CreateTechBusinessValueWithoutApp techBusinessValueWithoutApp;

    public UpdateApplicationServiceInput(String name, String description, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, Time time, int userTotal, double note, Long categoryId,
            Long departementId, CreateCostWithoutApp costWithoutApp,
            CreateTechBusinessValueWithoutApp techBusinessValueWithoutApp) {
        super(name, description, startDate,
                lastUpdate, status, time, userTotal);
        this.note = note;
        this.categoryId = categoryId;
        this.departementId = departementId;
        this.costWithoutApp = costWithoutApp;
        this.techBusinessValueWithoutApp = techBusinessValueWithoutApp;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public Long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getDepartementId() {
        return this.departementId;
    }

    public void setDepartementId(Long departementId) {
        this.departementId = departementId;
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
