package org.acme.applicationAPM.domain.input;

import java.time.LocalDateTime;

import org.acme.applicationAPM.domain.model.ApplicationBase;
import org.acme.applicationAPM.domain.model.Status;
import org.acme.applicationAPM.domain.model.Time;

public class UpdateApplicationInput extends ApplicationBase {
    private Long categoryId;
    private Long departementId;


    private double note;
    public UpdateApplicationInput(String name, String description, double businessValue, double costBuild,
            double costRun
            , LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, Time time, int userTotal, double note, Long categoryId, Long departementId) {
        // miantso ny constructeur anlay parent
        super(name, description, businessValue, costBuild, costRun, startDate,
                lastUpdate, status, time, userTotal);
                this.note = note;
                this.categoryId = categoryId;
                this.departementId = departementId;
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
}
