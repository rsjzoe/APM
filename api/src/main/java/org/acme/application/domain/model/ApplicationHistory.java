package org.acme.application.domain.model;

import java.time.LocalDateTime;

import org.acme.category.domain.CategoryODAChild;
import org.acme.classe.domain.Classe;
import org.acme.cost.domain.model.Cost;
import org.acme.departement.domain.Departement;
import org.acme.techBusinessValue.domain.model.TechBusinessValue;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationHistory extends ApplicationBase {
    private Long id;
    private Long appId;
    protected double note;
    protected CategoryODAChild category;
    protected Departement departement;
    protected Classe classe;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
    private String descriptionHistory;
    protected Cost cost;
    protected Time time;
    protected TechBusinessValue techBusinessValue;
    protected boolean isDeleted;

  

    public ApplicationHistory(Long id, Long appId, String name, String description, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, Time time, int userTotal, double note, CategoryODAChild category,
            Departement departement, Classe classe, LocalDateTime modifiedAt, String modifiedBy,
            String descriptionHistory, Cost cost,
            TechBusinessValue techBusinessValue, boolean isDeleted) {
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
        this.time = time;
        this.techBusinessValue = techBusinessValue;
        this.isDeleted = isDeleted;
    }

}
