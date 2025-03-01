package org.acme.application.domain.model.output;

import java.time.LocalDateTime;
import java.util.List;

import org.acme.application.domain.model.ApplicationBase;
import org.acme.application.domain.model.Status;
import org.acme.application.domain.model.Time;
import org.acme.category.domain.Category;
import org.acme.cost.domain.model.Cost;
import org.acme.cost.domain.model.output.CostOutput;
import org.acme.departement.domain.Departement;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueOutput;

public class ApplicationOutput extends ApplicationBase {
    protected Long id;
    protected double note;
    protected Category category;
    protected Departement departement;
    protected Cost currentCost;
    protected List<CostOutput> costs;
    protected List<TechBusinessValueOutput> techBusinessValues;

    public ApplicationOutput() {

    }

    public ApplicationOutput(Long id, String name, String description,
            Category category, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, Time time, int userTotal, double note, Departement departement,Cost currentCost, List<CostOutput> costs, List<TechBusinessValueOutput> techBusinessValues) {
        super(name, description, startDate,
                lastUpdate, status, time, userTotal);
        this.id = id;
        this.note = note;
        this.category = category;
        this.departement = departement;
        this.currentCost = currentCost;
        this.costs = costs;
        this.techBusinessValues = techBusinessValues;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

     public Cost getCurrentCost(){
        return currentCost;
    }

    public void setCurrentCost(Cost currentCost){
        this.currentCost= currentCost;
    }

    public List<CostOutput> getCosts() {
        return costs;
    }

    public void setCosts(List<CostOutput> costs) {
        this.costs = costs;
    }

    public List<TechBusinessValueOutput> getTechBusinessValues() {
        return techBusinessValues;
    }

    public void setTechBusinessValues(List<TechBusinessValueOutput> techBusinessValues) {
        this.techBusinessValues = techBusinessValues;
    }


}
