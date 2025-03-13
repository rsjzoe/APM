package org.acme.application.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import org.acme.category.domain.CategoryODAChild;
import org.acme.classe.domain.Classe;
import org.acme.cost.domain.model.Cost;
import org.acme.departement.domain.Departement;
import org.acme.documentation.domain.Documentation;
import org.acme.techBusinessValue.domain.model.TechBusinessValue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Application extends ApplicationBase {
    protected Long id;
    protected CategoryODAChild category;
    protected Departement departement;
    protected Classe classe;
    protected double noteCost;
    protected double noteTechBusiness;
    protected Cost currentCost;
    protected TechBusinessValue currentTechBusinessValue;
    protected List<Cost> costs;
    protected List<TechBusinessValue> techBusinessValues;
    protected List<Documentation> documentations;
    protected Time time;
    protected boolean isDeleted;

    public Application(Long id, String name, String description,
            CategoryODAChild category, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, int userTotal, Departement departement, double noteCost,
            double noteTechBusiness,
            Classe classe, Cost currentCost, TechBusinessValue currentTechBusinessValue, List<Cost> costs,
            List<TechBusinessValue> techBusinessValues, List<Documentation> documentations, Time time,
            boolean isDeleted) {
        super(name, description, startDate,
                lastUpdate, status, userTotal);
        this.id = id;
        this.category = category;
        this.departement = departement;
        this.classe = classe;
        this.noteCost = noteCost;
        this.noteTechBusiness = noteTechBusiness;
        this.currentCost = currentCost;
        this.currentTechBusinessValue = currentTechBusinessValue;
        this.costs = costs;
        this.techBusinessValues = techBusinessValues;
        this.documentations = documentations;
        this.time = time;
        this.isDeleted = isDeleted;
    }

}
