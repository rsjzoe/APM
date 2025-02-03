package org.acme.applicationAPM.infra.database;

import java.time.LocalDateTime;

import org.acme.applicationAPM.domain.input.CreateApplicationHistoryInput;
import org.acme.applicationAPM.domain.model.ApplicationHistory;
import org.acme.applicationAPM.domain.model.Status;
import org.acme.applicationAPM.domain.model.Time;
import org.acme.category.CategoryEntity;
import org.acme.category.CategoryEntityHelper;
import org.acme.departement.DepartementEntity;
import org.acme.departement.DepartementEntityHelper;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Entity
public class ApplicationHistoryEntity extends PanacheEntity {
    public String name;
    public String description;
    public double businessValue; // vola napidirin'ilay app
    public double costBuild;
    public double costRun;
    public String userTeam;
    @ManyToOne(fetch = FetchType.LAZY)
    public CategoryEntity category;
    public LocalDateTime startDate;
    public LocalDateTime lastUpdate;
    public Status status;
    public Time time;
    public double note;
    public int userTotal;
    public LocalDateTime modifiedAt;
    public String modifiedBy;
    @ManyToOne(fetch = FetchType.LAZY)
    public DepartementEntity departement;
    @ManyToOne(fetch = FetchType.LAZY)
    public ApplicationEntity applicationEntity;

    public ApplicationHistoryEntity() {
    }

    public ApplicationHistoryEntity(String name, String description, double businessValue, double costBuild,
            double costRun, LocalDateTime startDate, LocalDateTime lastUpdate, Status status,
            Time time, int userTotal, double note, LocalDateTime modifiedAt, String modifiedBy) {
        this.name = name;
        this.description = description;
        this.businessValue = businessValue;
        this.costBuild = costBuild;
        this.costRun = costRun;
        this.startDate = startDate;
        this.lastUpdate = lastUpdate;
        this.status = status;
        this.time = time;
        this.userTotal = userTotal;
        this.note = note;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
    }

    public ApplicationHistoryEntity(CreateApplicationHistoryInput app) {

        this.name = app.getName();
        this.description = app.getDescription();
        this.businessValue = app.getBusinessValue();
        this.costBuild = app.getCostBuild();
        this.costRun = app.getCostRun();
        this.startDate = app.getStartDate();
        this.lastUpdate = app.getLastUpdate();
        this.status = app.getStatus();
        this.time = app.getTime();
        this.userTotal = app.getUserTotal();
        this.note = 0;
        this.departement = DepartementEntityHelper.entityFromId(app.getDepartement().getId());
        // mapfandray application sy category
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.id = app.getCategory().getId();
        this.category = CategoryEntityHelper.entityFromId(app.getCategory().getId());
        this.modifiedAt = LocalDateTime.now();
        this.modifiedBy = "system";

        this.applicationEntity = ApplicationEntityHelper.entityFromId(app.getApplicationId());

    }

    public ApplicationHistory toApplicationHistory() {
        return new ApplicationHistory(
                this.id,
                this.name,
                this.description,
                this.businessValue,
                this.costBuild,
                this.costRun,
                this.category.toCategory(),
                this.startDate,
                this.lastUpdate,
                this.status,
                this.time,
                this.userTotal,
                this.note,
                this.departement.toDepartement(),
                this.modifiedAt.toLocalDate(),
                this.modifiedBy,
                this.applicationEntity.id);
    }

}
