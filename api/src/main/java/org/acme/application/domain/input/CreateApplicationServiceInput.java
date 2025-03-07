package org.acme.application.domain.input;

import java.time.LocalDateTime;
import java.util.List;

import org.acme.application.domain.model.ApplicationBase;
import org.acme.cost.domain.model.input.CreateCostWithoutApp;
import org.acme.documentation.domain.input.CreateDocumentationFileWithoutApp;
import org.acme.techBusinessValue.domain.model.input.CreateTechBusinessValueWithoutApp;
import org.acme.application.domain.model.Status;

public class CreateApplicationServiceInput extends ApplicationBase {
    private Long categoryId;
    private Long departementId;
    private CreateCostWithoutApp costWithoutApp;
    private CreateTechBusinessValueWithoutApp techBusinessValueWithoutApp;
    private List<CreateDocumentationFileWithoutApp> documentationsFileWithoutApp;

    public CreateApplicationServiceInput() {
        super();
    }

    public CreateApplicationServiceInput(String name, String description, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, int userTotal, Long categoryId, Long departementId,
            CreateCostWithoutApp costWithoutApp, CreateTechBusinessValueWithoutApp techBusinessValueWithoutApp,
            List<CreateDocumentationFileWithoutApp> documentationsFileWithoutApp) {
        super(name, description, startDate, lastUpdate, status, userTotal);
        this.categoryId = categoryId;
        this.departementId = departementId;
        this.costWithoutApp = costWithoutApp;
        this.techBusinessValueWithoutApp = techBusinessValueWithoutApp;
        this.documentationsFileWithoutApp = documentationsFileWithoutApp;
    }

    public CreateApplicationServiceInput(List<CreateDocumentationFileWithoutApp> doc, CreateApplicationRest app) {
        super(app.getName(), app.getDescription(), app.getStartDate(), app.getLastUpdate(), app.getStatus(), app.getUserTotal());
        this.categoryId = app.getCategoryId();
        this.departementId = app.getDepartementId();
        this.costWithoutApp = app.getCostWithoutApp();
        this.techBusinessValueWithoutApp = app.getTechBusinessValueWithoutApp();
        this.documentationsFileWithoutApp = doc;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getDepartementId() {
        return departementId;
    }

    public void setDepartementId(Long departementId) {
        this.departementId = departementId;
    }

    public CreateCostWithoutApp getCostWithoutApp() {
        return costWithoutApp;
    }

    public void setCostWithoutApp(CreateCostWithoutApp costWithoutApp) {
        this.costWithoutApp = costWithoutApp;
    }

    public CreateTechBusinessValueWithoutApp getTechBusinessValueWithoutApp() {
        return techBusinessValueWithoutApp;
    }

    public void setTechBusinessValueWithoutApp(CreateTechBusinessValueWithoutApp techBusinessValueWithoutApp) {
        this.techBusinessValueWithoutApp = techBusinessValueWithoutApp;
    }

    public List<CreateDocumentationFileWithoutApp> getDocumentationsFileWithoutApp() {
        return documentationsFileWithoutApp;
    }

    public void setDocumentationsFileWithoutApp(List<CreateDocumentationFileWithoutApp> documentationsFileWithoutApp) {
        this.documentationsFileWithoutApp = documentationsFileWithoutApp;
    }

  
}
