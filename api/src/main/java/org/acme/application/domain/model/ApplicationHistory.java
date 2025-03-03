package org.acme.application.domain.model;

import java.time.LocalDate;

import org.acme.application.domain.model.output.ApplicationOutput;

public class ApplicationHistory {
    private LocalDate modifiedAt;
    private String modifiedBy;
    private String description;
    private ApplicationOutput application;

    public ApplicationHistory(LocalDate modifiedAt, String modifiedBy, String description, ApplicationOutput application) {
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
        this.description = description;
        this.application = application;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ApplicationOutput getApplication() {
        return application;
    }

    public void setApplication(ApplicationOutput application) {
        this.application = application;
    }
}
