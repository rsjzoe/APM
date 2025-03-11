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

public class ApplicationOutput extends ApplicationBase {
    protected Long id;
    protected double note;
    protected CategoryODAChildOutput category;
    protected Departement departement;
    protected ClasseOutput classe;
    protected CostOutput currentCost;
    protected TechBusinessValueOutput currentTechBusinessValue;
    protected List<CostOutput> costs;
    protected List<TechBusinessValueOutput> techBusinessValues;
    protected Time time;
    protected boolean isDeleted;

    public ApplicationOutput() {

    }

    public ApplicationOutput(Long id, String name, String description,
            CategoryODAChildOutput category, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, Time time, int userTotal, double note, Departement departement,
            ClasseOutput classe, CostOutput currentCost, TechBusinessValueOutput currentTechBusinessValue,
            List<CostOutput> costs,
            List<TechBusinessValueOutput> techBusinessValues, boolean isDeleted) {
        super(name, description, startDate,
                lastUpdate, status, userTotal);
        this.id = id;
        this.note = note;
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

    public CategoryODAChildOutput getCategory() {
        return category;
    }

    public void setCategory(CategoryODAChildOutput category) {
        this.category = category;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public ClasseOutput getClasse() {
        return classe;
    }

    public void setClasse(ClasseOutput classe) {
        this.classe = classe;
    }

    public CostOutput getCurrentCost() {
        return currentCost;
    }

    public void setCurrentCost(CostOutput currentCost) {
        this.currentCost = currentCost;
    }

    public TechBusinessValueOutput getCurrentTechBusinessValue() {
        return currentTechBusinessValue;
    }

    public void setCurrentTechBusinessValue(TechBusinessValueOutput currentTechBusinessValue) {
        this.currentTechBusinessValue = currentTechBusinessValue;
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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
