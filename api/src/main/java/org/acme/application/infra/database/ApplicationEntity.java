package org.acme.application.infra.database;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.acme.application.domain.input.CreateApplicationRepositoryInput;
import org.acme.application.domain.input.UpdateApplicationRepositoryInput;
import org.acme.application.domain.model.Status;
import org.acme.application.domain.model.Time;
import org.acme.application.domain.output.ApplicationOutput;
import org.acme.category.infra.out.Entity.CategoryODAChildEntity;
import org.acme.category.infra.out.Entity.CategoryODAChildHelper;
import org.acme.classe.infra.database.ClasseEntity;
import org.acme.classe.infra.database.ClasseEntityHelper;
import org.acme.cost.domain.model.output.CostOutput;
import org.acme.cost.infra.database.CostEntity;
import org.acme.departement.infra.out.DepartementEntity;
import org.acme.departement.infra.out.DepartementEntityHelper;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueOutput;
import org.acme.techBusinessValue.infra.database.TechBusinessValueEntity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
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
    private double noteCost;
    private double noteTechBusiness;
    private int userTotal;
    @ManyToOne(fetch = FetchType.LAZY)
    private DepartementEntity departement;
    @ManyToOne(fetch = FetchType.LAZY)
    private ClasseEntity classe;
    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CostEntity> costEntity = new ArrayList<>();
    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TechBusinessValueEntity> techBusinessValueEntity = new ArrayList<>();
    private boolean isDeleted;

    public ApplicationEntity(CreateApplicationRepositoryInput app) {
        this.name = app.getName();
        this.description = app.getDescription();
        this.startDate = app.getStartDate();
        this.lastUpdate = app.getLastUpdate();
        this.status = app.getStatus();
        this.time = app.getTime();
        this.userTotal = app.getUserTotal();
        this.noteCost = 0;
        this.noteTechBusiness = 0;
        this.departement = DepartementEntityHelper.entityFromId(app.getDepartementId());
        // mapfandray application sy category
        CategoryODAChildEntity categoryEntity = new CategoryODAChildEntity();
        categoryEntity.id = app.getCategoryId();
        this.category = categoryEntity;
        this.classe = ClasseEntityHelper.entityFromId(app.getCategoryId());
        this.isDeleted = false;

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
                startDate, lastUpdate, status, time, userTotal, noteCost, noteTechBusiness,
                departement.toDepartement(), classe.toOutput(), latestCost,
                latestTech,
                costEntity.stream().map(CostEntity::toCostOutput).toList(),
                techBusinessValueEntity.stream().map(TechBusinessValueEntity::toTechBusinessValueOutput).toList(),
                isDeleted);
    }

    public ApplicationEntity updateData(UpdateApplicationRepositoryInput app) {
        // null izi raha tsy passe avy any amin front
        if (app.getName() != null) {
            this.name = app.getName();
        }
        if (app.getDescription() != null) {
            this.description = app.getDescription();
        }
        if (app.getStartDate() != null) {
            this.startDate = app.getStartDate();
        }
        if (app.getLastUpdate() != null) {
            this.lastUpdate = app.getLastUpdate();
        }
        if (app.getStatus() != null) {
            this.status = app.getStatus();
        }
        if (app.getTime() != null) {
            this.time = app.getTime();
        }
        if (app.getUserTotal() != 0) {
            this.userTotal = app.getUserTotal();
        }
        if (app.getNoteCost() != 0) {
            this.noteCost = app.getNoteCost();
        }
        if (app.getNoteTechBusiness() != 0) {
            this.noteTechBusiness = app.getNoteTechBusiness();
        }
        if (app.getCategoryId() != null) {
            this.category = CategoryODAChildHelper.entityFromId(app.getCategoryId());
        }
        if (app.getDepartementId() != null) {
            this.departement = DepartementEntityHelper.entityFromId(app.getDepartementId());
        }
        if (app.getClasseId() != null) {
            this.classe = ClasseEntityHelper.entityFromId(app.getClasseId());
        }
        return this;
    }

}
