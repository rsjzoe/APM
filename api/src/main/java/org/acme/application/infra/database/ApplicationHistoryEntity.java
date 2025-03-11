package org.acme.application.infra.database;

import java.time.LocalDateTime;

import org.acme.application.domain.input.CreateApplicationHistoryRepository;
import org.acme.application.domain.model.Status;
import org.acme.application.domain.model.Time;
import org.acme.application.domain.output.ApplicationHistoryOutput;
import org.acme.category.adapter.out.Entity.CategoryODAChildEntity;
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
    private double note;
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

    public ApplicationHistoryEntity() {
    }

    public ApplicationHistoryEntity(CreateApplicationHistoryRepository data) {
        this.appId = data.getAppId();
        this.note = data.getNote();
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
                userTotal, note, category.toCategoryODAChildOutput(), departement.toDepartement(), classe.toOutput(),
                modifiedAt, modifiedBy, descriptionHistory, costEntity.toCostOutput(),
                techBusinessValueEntity.toTechBusinessValueOutput(), isDeleted);
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getDescriptionHistory() {
        return descriptionHistory;
    }

    public void setDescriptionHistory(String descriptionHistory) {
        this.descriptionHistory = descriptionHistory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryODAChildEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryODAChildEntity category) {
        this.category = category;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public int getUserTotal() {
        return userTotal;
    }

    public void setUserTotal(int userTotal) {
        this.userTotal = userTotal;
    }

    public DepartementEntity getDepartement() {
        return departement;
    }

    public void setDepartement(DepartementEntity departement) {
        this.departement = departement;
    }

    public ClasseEntity getClasseEntity() {
        return classe;
    }

    public void seClasseEntity(ClasseEntity classe) {
        this.classe = classe;
    }

    public CostEntity getCostEntity() {
        return costEntity;
    }

    public void setCostEntity(CostEntity costEntity) {
        this.costEntity = costEntity;
    }

    public TechBusinessValueEntity getTechBusinessValueEntity() {
        return techBusinessValueEntity;
    }

    public void setTechBusinessValueEntity(TechBusinessValueEntity techBusinessValueEntity) {
        this.techBusinessValueEntity = techBusinessValueEntity;
    }
}
