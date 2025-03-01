package org.acme.application.domain.model.input;

import java.time.LocalDateTime;

import org.acme.application.domain.model.ApplicationBase;
import org.acme.application.domain.model.Status;
import org.acme.application.domain.model.Time;

public class UpdateApplicationInput extends ApplicationBase {
    private Long categoryId;
    private Long departementId;
    private double note;
    private Long costId;
    private Long techBusinessValueId;

    public UpdateApplicationInput(String name, String description, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, Time time, int userTotal, double note, Long categoryId,
            Long departementId, Long costId, Long techBusinessValueId) {
        super(name, description, startDate,
                lastUpdate, status, time, userTotal);
        this.note = note;
        this.categoryId = categoryId;
        this.departementId = departementId;
        this.costId = costId;
        this.techBusinessValueId = techBusinessValueId;
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

    @Override
    public String toString() {
        return "UpdateApplicationInput{" +
                "categoryId=" + categoryId +
                ", departementId=" + departementId +
                ", note=" + note +
                ", costId=" + costId +
                ", techBusinessValueId=" + techBusinessValueId +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", startDate=" + getStartDate() +
                ", lastUpdate=" + getLastUpdate() +
                ", status=" + getStatus() +
                ", time=" + getTime() +
                ", userTotal=" + getUserTotal() +
                '}';
    }
}
