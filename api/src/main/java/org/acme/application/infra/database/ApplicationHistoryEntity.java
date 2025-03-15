package org.acme.application.infra.database;

import java.time.LocalDateTime;

import org.acme.application.domain.input.CreateApplicationHistoryRepository;
import org.acme.application.domain.model.Status;
import org.acme.application.domain.model.Time;
import org.acme.application.domain.output.ApplicationHistoryOutput;
import org.acme.category.infra.out.Entity.CategoryODAChildEntity;
import org.acme.classe.infra.database.ClasseEntity;
import org.acme.classe.infra.database.ClasseEntityHelper;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ApplicationHistoryEntity extends PanacheEntity {
    private Long appId;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
    private String descriptionHistory;
    private String description;
    private String name;
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
    @ManyToOne
    protected CostEntity costEntity;
    @ManyToOne
    protected TechBusinessValueEntity techBusinessValueEntity;
    protected boolean isDeleted;

    public ApplicationHistoryEntity(CreateApplicationHistoryRepository data) {
        this.appId = data.getAppId();
        this.noteCost = data.getNoteCost();
        this.noteTechBusiness = data.getNoteTechBusiness();
        this.category = new CategoryODAChildEntity();
        this.category.id = data.getCategoryId();
        this.departement = DepartementEntityHelper.entityFromId(data.getDepartementId());
        this.classe = ClasseEntityHelper.entityFromId(data.getClasseId());
        this.modifiedAt = LocalDateTime.now();
        this.modifiedBy = data.getModifiedBy();
        this.descriptionHistory = data.getDescriptionHistory();
        this.description = data.getDescription();
        this.costEntity = CostEntityHelper.entityFromId(data.getCostId());
        this.techBusinessValueEntity = TechBusinessValueEntityHelper.entityFromId(data.getTechBusinessValueId());
        this.name = data.getName();
        this.startDate = data.getStartDate();
        this.lastUpdate = data.getLastUpdate();
        this.status = data.getStatus();
        this.time = data.getTime();
        this.userTotal = data.getUserTotal();
    }

    public ApplicationHistoryOutput toOutput() {
        return new ApplicationHistoryOutput(id, appId, name, description, startDate, lastUpdate, status, time,
                userTotal, noteCost, noteTechBusiness, category.toCategoryODAChildOutput(), departement.toDepartement(),
                classe.toOutput(),
                modifiedAt, modifiedBy, descriptionHistory, costEntity.toCostOutput(),
                techBusinessValueEntity.toTechBusinessValueOutput(), isDeleted);
    }

}
