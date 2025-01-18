package org.acme.applicationAPM.domain.input;

import java.time.LocalDateTime;

import org.acme.applicationAPM.domain.model.ApplicationBase;
import org.acme.applicationAPM.domain.model.Category;
import org.acme.applicationAPM.domain.model.Performance;
import org.acme.applicationAPM.domain.model.Status;
import org.acme.applicationAPM.domain.model.Time;

public class CreateApplicationInput extends ApplicationBase {

    public CreateApplicationInput(String name, String description, double businessValue, double costBuild,
            double costRun,
            String userTeam, Category category, LocalDateTime startDate,
            LocalDateTime lastUpdate, Performance performance, Status status, Time time, int userTotal) {
        super(name, description, businessValue, costBuild, costRun, userTeam, category, startDate,
                lastUpdate, performance, status, time, userTotal);
    }
}
