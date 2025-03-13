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
    protected double noteCost;
    protected double noteTechBusiness;
    protected CategoryODAChildOutput category;
    protected Departement departement;
    protected ClasseOutput classe;
    protected CostOutput currentCost;
    protected TechBusinessValueOutput currentTechBusinessValue;
    protected List<CostOutput> costs;
    protected List<TechBusinessValueOutput> techBusinessValues;
    protected Time time;
    protected boolean isDeleted;

    public ApplicationOutput(Long id, String name, String description,
            CategoryODAChildOutput category, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, Time time, int userTotal, double noteCost,
            double noteTechBusiness, Departement departement,
            ClasseOutput classe, CostOutput currentCost, TechBusinessValueOutput currentTechBusinessValue,
            List<CostOutput> costs,
            List<TechBusinessValueOutput> techBusinessValues, boolean isDeleted) {
        super(name, description, startDate,
                lastUpdate, status, userTotal);
        this.id = id;
        this.noteCost = noteCost;
        this.noteTechBusiness = noteTechBusiness;
        this.category = category;
        this.departement = departement;
        this.classe = classe;
        this.currentCost = currentCost;
        this.currentTechBusinessValue = currentTechBusinessValue;
        this.costs = costs;
        this.techBusinessValues = techBusinessValues;
        this.time = time;
        this.isDeleted = isDeleted;
    }

}
