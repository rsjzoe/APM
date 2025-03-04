package org.acme.application.domain.model.input;

import java.time.LocalDateTime;

import org.acme.application.domain.model.ApplicationBase;
import org.acme.application.domain.model.Status;
import org.acme.application.domain.model.Time;

public class CreateApplicationInput extends ApplicationBase {
    private Long categoryId;
    private Long departementId;
    private Long costId;
    private Long techBusinessValueId;
    

    public CreateApplicationInput(String name, String description, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, Time time, int userTotal, Long categoryId, Long departementId, Long costId, Long techBusinessValueId) {
        super(name, description, startDate,
                lastUpdate, status, time, userTotal);
        this.categoryId = categoryId;
        this.departementId = departementId;
        this.costId = costId;
        this.techBusinessValueId = techBusinessValueId;
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

    public Long getCostId() {
        return this.costId;
    }

    public void setCostId(Long costId) {
        this.costId = costId;
    }

    public Long getTechBusinessValueId() {
        return this.techBusinessValueId;
    }

    public void setTechBusinessValueId(Long techBusinessValueId) {
        this.techBusinessValueId = techBusinessValueId;
    }
}
