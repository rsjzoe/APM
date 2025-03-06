package org.acme.application.infra.database;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.acme.application.domain.input.CreateApplicationRepositoryInput;
import org.acme.application.domain.input.UpdateApplicationRepositoryInput;
import org.acme.application.domain.model.Status;
import org.acme.application.domain.model.Time;
import org.acme.application.domain.output.ApplicationOutput;
import org.acme.category.adapters.out.Entity.CategoryODAChildEntity;
import org.acme.category.adapters.out.Entity.CategoryODAChildHelper;
import org.acme.cost.domain.model.output.CostOutput;
import org.acme.cost.infra.database.CostEntity;
import org.acme.departement.infra.out.DepartementEntity;
import org.acme.departement.infra.out.DepartementEntityHelper;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueOutput;
import org.acme.techBusinessValue.infra.database.TechBusinessValueEntity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class ApplicationEntity extends PanacheEntity {
    private String name;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryODAChildEntity category;
    private LocalDateTime startDate;
    private LocalDateTime lastUpdate;
    private Status status;
    private Time time;
    private double note;
    private int userTotal;
    @ManyToOne(fetch = FetchType.LAZY)
    private DepartementEntity departement;
    @OneToMany(mappedBy = "application")
    private List<CostEntity> costEntity = new ArrayList<>();
    @OneToMany(mappedBy = "application")
    private List<TechBusinessValueEntity> techBusinessValueEntity = new ArrayList<>();

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
    }

    public ApplicationEntity(CreateApplicationRepositoryInput app) {

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
        CategoryODAChildEntity categoryEntity = new CategoryODAChildEntity();
        categoryEntity.id = app.getCategoryId();
        this.category = categoryEntity;

    }

    public ApplicationOutput toApplicationOutput() {

        CostEntity latestCostEntity = CostEntity.find("application", this)
                .stream()
                .map(entity -> (CostEntity) entity)
                .sorted((b1, b2) -> b2.getCreatedAt().compareTo(b1.getCreatedAt()))
                .findFirst()
                .orElse(null);

        CostOutput latestCost = latestCostEntity != null ? latestCostEntity.toCostOutput() : null;

        TechBusinessValueEntity latestTechEntity = TechBusinessValueEntity.find("application", this)
                .stream()
                .map(entity -> (TechBusinessValueEntity) entity)
                .sorted((b1, b2) -> b2.getCreatedAt().compareTo(b1.getCreatedAt()))
                .findFirst()
                .orElse(null);

        TechBusinessValueOutput latestTech = latestTechEntity != null ? latestTechEntity.toTechBusinessValueOutput()
                : null;
        return new ApplicationOutput(id, name, description, category.toCategoryODAChildOutput(),
                startDate, lastUpdate, status, time, userTotal, note, departement.toDepartement(), latestCost,
                latestTech,
                costEntity.stream().map(CostEntity::toCostOutput).toList(),
                techBusinessValueEntity.stream().map(TechBusinessValueEntity::toTechBusinessValueOutput).toList());
    }

    public ApplicationEntity updateData(UpdateApplicationRepositoryInput app) {
        this.name = app.getName();
        this.description = app.getDescription();
        this.startDate = app.getStartDate();
        this.lastUpdate = app.getLastUpdate();
        this.status = app.getStatus();
        this.time = app.getTime();
        this.userTotal = app.getUserTotal();
        this.note = app.getNote();
        this.departement = DepartementEntityHelper.entityFromId(app.getDepartementId());
        this.category = CategoryODAChildHelper.entityFromId(app.getCategoryId());

        return this;
    }

}
