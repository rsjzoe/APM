package org.acme.application.domain.output;

import java.time.LocalDateTime;

import org.acme.application.domain.model.ApplicationBase;
import org.acme.application.domain.model.Status;
import org.acme.application.domain.model.Time;
import org.acme.category.domain.output.CategoryODAChildOutput;
import org.acme.classe.domain.output.ClasseOutput;
import org.acme.cost.domain.model.output.CostOutput;
import org.acme.departement.domain.Departement;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueOutput;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor

public class ApplicationHistoryOutput extends ApplicationBase {
    private Long id;
    private Long appId;
    protected double note;
    protected CategoryODAChildOutput category;
    protected Departement departement;
    protected ClasseOutput classe;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
    private String description;
    private String descriptionHistory;
    protected CostOutput cost;
    protected TechBusinessValueOutput techBusinessValue;
    protected Time time;
    protected boolean isDeleted;

    public ApplicationHistoryOutput(Long id, Long appId, String name, String description, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, Time time, int userTotal, double note,
            CategoryODAChildOutput category,
            Departement departement, ClasseOutput classe, LocalDateTime modifiedAt, String modifiedBy,
            String descriptionHistory,
            CostOutput cost,
            TechBusinessValueOutput techBusinessValue, boolean isDeleted) {
        super(name, description, startDate, lastUpdate, status, userTotal);
        this.id = id;
        this.appId = appId;
        this.note = note;
        this.category = category;
        this.departement = departement;
        this.classe = classe;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
        this.descriptionHistory = descriptionHistory;
        this.cost = cost;
        this.techBusinessValue = techBusinessValue;
        this.time = time;
        this.isDeleted = isDeleted;
    }

}
