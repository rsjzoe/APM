package org.acme.applicationAPM.domain.input;

import java.time.LocalDateTime;

import org.acme.applicationAPM.domain.model.ApplicationBase;
import org.acme.applicationAPM.domain.model.Status;
import org.acme.applicationAPM.domain.model.Time;

public class CreateApplicationInput extends ApplicationBase {
    private Long categoryId;

    public CreateApplicationInput(String name, String description, double businessValue, double costBuild,
            double costRun,
            String userTeam, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, Time time, int userTotal, Long categoryId) {
        super(name, description, businessValue, costBuild, costRun, userTeam, startDate,
                lastUpdate, status, time, userTotal);
                this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
