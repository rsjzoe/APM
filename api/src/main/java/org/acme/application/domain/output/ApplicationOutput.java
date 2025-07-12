package org.acme.application.domain.output;

import java.time.LocalDateTime;
import java.util.List;

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
public class ApplicationOutput extends ApplicationBase {
    protected Long id;
    protected double noteBusinessValue;
    protected double noteTechnicalDebt;
    protected CategoryODAChildOutput category;
    protected List<Departement> departements;
    protected ClasseOutput classe;
    protected CostOutput currentCost;
    protected TechBusinessValueOutput currentTechBusinessValue;
    protected List<CostOutput> costs;
    protected List<TechBusinessValueOutput> techBusinessValues;
    protected Time time;
    protected boolean isDeleted;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    public ApplicationOutput(Long id, String name, String description,
            CategoryODAChildOutput category, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, Time time, int userTotal, double noteBusinessValue,
            double noteTechnicalDebt, List<Departement> departements,
            ClasseOutput classe, CostOutput currentCost, TechBusinessValueOutput currentTechBusinessValue,
            List<CostOutput> costs,
            List<TechBusinessValueOutput> techBusinessValues, boolean isDeleted, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        super(name, description, startDate,
                lastUpdate, status, userTotal);
        this.id = id;
        this.noteBusinessValue = noteBusinessValue;
        this.noteTechnicalDebt = noteTechnicalDebt;
        this.category = category;
        this.departements = departements;
        this.classe = classe;
        this.currentCost = currentCost;
        this.currentTechBusinessValue = currentTechBusinessValue;
        this.costs = costs;
        this.techBusinessValues = techBusinessValues;
        this.time = time;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ApplicationOutput{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", lastUpdate=" + lastUpdate +
                ", status=" + status +
                ", userTotal=" + userTotal +
                ", noteBusinessValue=" + noteBusinessValue +
                ", noteTechnicalDebt=" + noteTechnicalDebt +
                ", category=" + category +
                ", departement=" + departements +
                ", classe=" + classe +
                ", currentCost=" + currentCost +
                ", currentTechBusinessValue=" + currentTechBusinessValue +
                ", costs=" + costs +
                ", techBusinessValues=" + techBusinessValues +
                ", time=" + time +
                ", isDeleted=" + isDeleted +
                '}';
    }

}
