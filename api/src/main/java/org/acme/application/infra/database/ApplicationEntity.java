package org.acme.application.infra.database;

import java.time.LocalDateTime;

import org.acme.application.domain.input.CreateApplicationInput;
import org.acme.application.domain.input.UpdateApplicationInput;
import org.acme.application.domain.model.Application;
import org.acme.application.domain.model.Status;
import org.acme.application.domain.model.Time;
import org.acme.category.infra.out.CategoryEntity;
import org.acme.category.infra.out.CategoryEntityHelper;
import org.acme.cost.infra.database.CostEntity;
import org.acme.cost.infra.database.CostEntityHelper;
import org.acme.departement.infra.out.DepartementEntity;
import org.acme.departement.infra.out.DepartementEntityHelper;
import org.acme.techBusinessValue.infra.database.TechBusinessValueEntity;
import org.acme.techBusinessValue.infra.database.TechBusinessValueEntityHelper;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Entity
public class ApplicationEntity extends PanacheEntity {
    private String name;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryEntity category;
    private LocalDateTime startDate;
    private LocalDateTime lastUpdate;
    private Status status;
    private Time time;
    private double note;
    private int userTotal;
    @ManyToOne(fetch = FetchType.LAZY)
    private DepartementEntity departement;
    @ManyToOne
    private CostEntity costEntity;
    @ManyToOne
    private TechBusinessValueEntity techBusinessValueEntity;

    public ApplicationEntity() {
    }

    public ApplicationEntity(String name, String description, LocalDateTime startDate, LocalDateTime lastUpdate,
            Status status,
            Time time, int userTotal, double note, CostEntity costEntity,
            TechBusinessValueEntity techBusinessValueEntity) {
        this.name = name;
        this.description = description;

        this.startDate = startDate;
        this.lastUpdate = lastUpdate;
        this.status = status;
        this.time = time;
        this.userTotal = userTotal;
        this.note = note;
        this.costEntity = costEntity;
        this.techBusinessValueEntity = techBusinessValueEntity;
    }

    public ApplicationEntity(CreateApplicationInput app) {

        this.name = app.getName();
        this.description = app.getDescription();
        this.startDate = app.getStartDate();
        this.lastUpdate = app.getLastUpdate();
        this.status = app.getStatus();
        this.time = app.getTime();
        this.userTotal = app.getUserTotal();
        this.note = 0;
        this.departement = DepartementEntityHelper.entityFromId(app.getDepartementId());
        // mapfandray application sy category
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.id = app.getCategoryId();
        this.category = categoryEntity;
        this.costEntity = CostEntityHelper.entityFromId(app.getCostId());
        this.techBusinessValueEntity = TechBusinessValueEntityHelper.entityFromId(app.getTechBusinessValueId());
    }

    public Application toApplication() {
        return new Application(id, name, description, category.toCategory(), startDate, lastUpdate, status, time,
                userTotal, note, departement.toDepartement(), costEntity.toCost(), techBusinessValueEntity.toTechBusinessValue());
    }

    public ApplicationEntity updateData(UpdateApplicationInput app) {
        this.name = app.getName();
        this.description = app.getDescription();

        this.startDate = app.getStartDate();
        this.lastUpdate = app.getLastUpdate();
        this.status = app.getStatus();
        this.time = app.getTime();
        this.userTotal = app.getUserTotal();
        this.note = app.getNote();
        this.departement = DepartementEntityHelper.entityFromId(app.getDepartementId());
        this.category = CategoryEntityHelper.entityFromId(app.getCategoryId());
        this.costEntity = CostEntityHelper.entityFromId(app.getCostId());
        this.techBusinessValueEntity = TechBusinessValueEntityHelper.entityFromId(app.getTechBusinessValueId());
        return this;
    }

}
