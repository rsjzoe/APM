package org.acme.application.domain.model.input;


import org.acme.application.domain.model.ApplicationBase;

public class CreateApplicationHistoryInput extends ApplicationBase {
    private Long applicationId;
    private String modifiedBy;
    private String description;

    public CreateApplicationHistoryInput() {
    }

    public CreateApplicationHistoryInput(Long applicationId, String modifiedBy, String description) {
        this.applicationId = applicationId;
        this.modifiedBy = modifiedBy;
        this.description = description;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
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
}
