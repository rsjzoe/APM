package org.acme.application.domain.model;

import java.time.LocalDate;
import org.acme.cost.domain.model.Cost;
import org.acme.techBusinessValue.domain.model.TechBusinessValue;

public class ApplicationHistory {
    private LocalDate modifiedAt;
    private String modifiedBy;
    private Cost cost;
    private TechBusinessValue techBusinessValue;
    private Application application;

    public ApplicationHistory( LocalDate modifiedAt, String modifiedBy,
            Cost cost, TechBusinessValue techBusinessValue, Application application) {
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
        this.application = application;
        this.cost = cost;
        this.techBusinessValue = techBusinessValue;
    }

    public LocalDate getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDate modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public TechBusinessValue getTechBusinessValue() {
        return techBusinessValue;
    }

    public void setTechBusinessValue(TechBusinessValue techBusinessValue) {
        this.techBusinessValue = techBusinessValue;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
