package org.acme.application.infra.database;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.acme.application.domain.input.CreateApplicationHistoryRepository;
import org.acme.application.domain.model.Status;
import org.acme.application.domain.model.Time;
import org.acme.application.domain.output.ApplicationHistoryOutput;
import org.acme.category.infra.database.entity.CategoryODAChildEntity;
import org.acme.classe.infra.database.ClasseEntity;
import org.acme.classe.infra.database.ClasseEntityHelper;
import org.acme.cost.infra.database.CostEntity;
import org.acme.cost.infra.database.CostEntityHelper;
import org.acme.departement.infra.database.DepartementEntity;
import org.acme.departement.infra.database.DepartementEntityHelper;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueOutput;
import org.acme.techBusinessValue.infra.database.TechBusinessValueEntity;
import org.acme.techBusinessValue.infra.database.TechBusinessValueEntityHelper;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
    @Column(columnDefinition = "TEXT")
    private String descriptionHistory;
    private String description;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryODAChildEntity category;
    private LocalDateTime startDate;
    private LocalDateTime lastUpdate;
    private Status status;
    private Time time;
    private double noteBusinessValue;
    private double noteTechnicalDebt;
    private int userTotal;
    @ManyToMany
    @JoinTable(name = "ApplicationHistoryEntity_DepartementEntity", joinColumns = @JoinColumn(name = "application_history_id"), inverseJoinColumns = @JoinColumn(name = "departement_id"))
    private List<DepartementEntity> departements = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private ClasseEntity classe;
    @ManyToOne
    protected CostEntity costEntity;
    @ManyToOne
    protected TechBusinessValueEntity techBusinessValueEntity;
    protected boolean isDeleted;

    public ApplicationHistoryEntity(CreateApplicationHistoryRepository data) {
        this.appId = data.getAppId();
        this.noteBusinessValue = data.getNoteBusinessValue();
        this.noteTechnicalDebt = data.getNoteTechnicalDebt();
        this.category = new CategoryODAChildEntity();
        this.category.id = data.getCategoryId();
        this.departements = data.getDepartementIds().stream()
                .map(departementId -> DepartementEntityHelper.entityFromId(departementId)).toList();
        this.classe = ClasseEntityHelper.entityFromId(data.getClasseId());
        this.modifiedAt = LocalDateTime.now();
        this.modifiedBy = data.getModifiedBy();
        this.descriptionHistory = data.getDescriptionHistory();
        this.description = data.getDescription();
        if (data.getCostId() != null) {
            this.costEntity = CostEntityHelper.entityFromId(data.getCostId());
        }

        if (data.getTechBusinessValueId() != null) {
            this.techBusinessValueEntity = TechBusinessValueEntityHelper.entityFromId(data.getTechBusinessValueId());
        }
        this.name = data.getName();
        this.startDate = data.getStartDate();
        this.lastUpdate = data.getLastUpdate();
        this.status = data.getStatus();
        this.time = data.getTime();
        this.userTotal = data.getUserTotal();
    }

    public ApplicationHistoryOutput toOutput() {
        TechBusinessValueOutput techBusinessValueOutputFromNote = new TechBusinessValueOutput();
        techBusinessValueOutputFromNote.setBusinessValue(noteBusinessValue);
        techBusinessValueOutputFromNote.setTechnicalDebt(noteTechnicalDebt);

        return new ApplicationHistoryOutput(id, appId, name, description, startDate, lastUpdate, status, time,
                userTotal, noteBusinessValue, noteTechnicalDebt, category.toCategoryODAChildOutput(),
                departements.stream().map(DepartementEntity::toDepartement).toList(),
                classe.toOutput(),
                modifiedAt, modifiedBy, descriptionHistory, costEntity == null ? null : costEntity.toCostOutput(),
                techBusinessValueEntity == null ? techBusinessValueOutputFromNote
                        : techBusinessValueEntity.toTechBusinessValueOutput(),
                isDeleted);
    }

}
