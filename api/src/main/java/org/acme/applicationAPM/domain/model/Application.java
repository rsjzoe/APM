package org.acme.applicationAPM.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class Application extends ApplicationBase {
    private Long id;

    public Application() {
    }

    public Application(Long id, String name, String description, double businessValue, double costBuild, double costRun,
            String userTeam, Category category, List<Technology> technologies, LocalDateTime startDate,
            LocalDateTime lastUpdate, Performance performance, Status status, Time time, int userTotal) {
        super(name, description, businessValue, costBuild, costRun, userTeam, category, technologies, startDate,
                lastUpdate, performance, status, time, userTotal);
        this.id = id;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
