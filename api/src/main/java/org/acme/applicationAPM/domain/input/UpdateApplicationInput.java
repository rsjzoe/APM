package org.acme.applicationAPM.domain.input;

import java.time.LocalDateTime;
import java.util.List;

import org.acme.applicationAPM.domain.model.ApplicationBase;
import org.acme.applicationAPM.domain.model.Category;
import org.acme.applicationAPM.domain.model.Performance;
import org.acme.applicationAPM.domain.model.Status;
import org.acme.applicationAPM.domain.model.Technology;
import org.acme.applicationAPM.domain.model.Time;

public class UpdateApplicationInput extends ApplicationBase {

    public UpdateApplicationInput(String name, String description, double businessValue, double costBuild,
            double costRun,
            String userTeam, Category category, List<Technology> technologies, LocalDateTime startDate,
            LocalDateTime lastUpdate, Performance performance, Status status, Time time, int userTotal) {
        super(name, description, businessValue, costBuild, costRun, userTeam, category, technologies, startDate,
                lastUpdate, performance, status, time, userTotal);
    }
}
