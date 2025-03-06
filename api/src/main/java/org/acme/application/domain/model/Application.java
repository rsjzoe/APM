package org.acme.application.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import org.acme.category.domain.CategoryODAChild;
import org.acme.cost.domain.model.Cost;
import org.acme.departement.domain.Departement;
import org.acme.documentation.domain.Documentation;
import org.acme.techBusinessValue.domain.model.TechBusinessValue;

public class Application extends ApplicationBase {
    protected Long id;
    protected double note;
    protected CategoryODAChild category;
    protected Departement departement;
    protected Cost currentCost;
    protected TechBusinessValue currentTechBusinessValue;
    protected List<Cost> costs;
    protected List<TechBusinessValue> techBusinessValues;
    protected List<Documentation> documentations;
    protected boolean isDeleted;

    public Application() {

    }

    public Application(Long id, String name, String description,
            CategoryODAChild category, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, Time time, int userTotal, double note, Departement departement, Cost currentCost, TechBusinessValue currentTechBusinessValue, List<Cost> costs, List<TechBusinessValue> techBusinessValues, List<Documentation> documentations, boolean isDeleted) {
        super(name, description, startDate,
                lastUpdate, status, time, userTotal);
        this.id = id;
        this.note = note;
        this.category = category;
        this.departement = departement;
        this.currentCost = currentCost;
        this.currentTechBusinessValue = currentTechBusinessValue;
        this.costs = costs;
        this.techBusinessValues = techBusinessValues;
        this.documentations = documentations;
        this.isDeleted = isDeleted;
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

    public CategoryODAChild getCategory() {
        return category;
    }

    public void setCategory(CategoryODAChild category) {
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

    public TechBusinessValue getCurrentTechBusinessValue() {
        return currentTechBusinessValue;
    }

    public void setCurrentTechBusinessValue(TechBusinessValue currentTechBusinessValue) {
        this.currentTechBusinessValue = currentTechBusinessValue;
    }

    public List<Cost> getCosts() {
        return costs;
    }

    public void setCosts(List<Cost> costs) {
        this.costs = costs;
    }

    public List<TechBusinessValue> getTechBusinessValues() {
        return techBusinessValues;
    }

    public void setTechBusinessValues(List<TechBusinessValue> techBusinessValues) {
        this.techBusinessValues = techBusinessValues;
    }

    public List<Documentation> getDocumentations() {
        return documentations;
    }

    public void setDocumentations(List<Documentation> documentations) {
        this.documentations = documentations;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
