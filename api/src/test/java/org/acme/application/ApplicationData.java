package org.acme.application;

import java.time.LocalDateTime;
import java.util.List;

import org.acme.application.domain.input.CreateApplicationRepositoryInput;
import org.acme.application.domain.input.CreateApplicationServiceInput;
import org.acme.application.domain.input.UpdateApplicationRepositoryInput;
import org.acme.application.domain.input.UpdateApplicationServiceInput;
import org.acme.application.domain.model.Status;
import org.acme.application.domain.model.Time;
import org.acme.application.infra.database.ApplicationEntity;
import org.acme.category.infra.out.Entity.CategoryODAChildEntity;
import org.acme.category.infra.out.Entity.CategoryODAParentEntity;
import org.acme.classe.infra.database.ClasseEntity;
import org.acme.cost.domain.model.input.CreateCostWithoutApp;
import org.acme.departement.infra.database.DepartementEntity;
import org.acme.techBusinessValue.domain.model.input.CreateTechBusinessValueWithoutApp;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;

@ApplicationScoped
@Getter
public class ApplicationData {
    private CategoryODAParentEntity categoryParent;
    private CategoryODAChildEntity categoryChild;
    private DepartementEntity departement;
    private ClasseEntity classe;
    private ApplicationEntity application1;
    private ApplicationEntity applicationDeleted;

    public void setup() {
        categoryParent = new CategoryODAParentEntity("ParentCategory", "#FFFFFF");
        categoryParent.persistAndFlush();

        categoryChild = new CategoryODAChildEntity("ChildCategory");
        categoryChild.setCategoryODAParentEntity(categoryParent);
        categoryChild.persistAndFlush();

        departement = new DepartementEntity();
        departement.name = "IT Department";
        departement.persistAndFlush();

        classe = new ClasseEntity();
        classe.setName("Class A");
        classe.setDescription("Description of Class A");
        classe.persistAndFlush();

        application1 = new ApplicationEntity(new CreateApplicationRepositoryInput("App1", "Description1",
                LocalDateTime.now(), LocalDateTime.now(), Status.development, Time.invest, 10, categoryChild.id,
                departement.id, classe.id));
        application1.setClasse(classe);
        application1.persistAndFlush();

        applicationDeleted = new ApplicationEntity(
                new CreateApplicationRepositoryInput("applicationDeleted", "Description1",
                        LocalDateTime.now(), LocalDateTime.now(), Status.development, Time.invest, 10, categoryChild.id,
                        departement.id, classe.id));
        applicationDeleted.setClasse(classe);
        applicationDeleted.setDeleted(true);
        applicationDeleted.persistAndFlush();
    }

    public CreateApplicationRepositoryInput createApplicationInput() {
        return createApplicationInput("App1", "Description1");
    }

    public CreateApplicationRepositoryInput createApplicationInput(String name, String description) {
        CreateApplicationRepositoryInput input = new CreateApplicationRepositoryInput();
        input.setName(name);
        input.setDescription(description);
        input.setStartDate(LocalDateTime.now());
        input.setStatus(Status.development);
        input.setTime(Time.invest);
        input.setCategoryId(categoryChild.id);
        input.setDepartementId(departement.id);
        input.setClasseId(classe.id);
        return input;
    }

    public UpdateApplicationRepositoryInput updateApplicationInput() {
        UpdateApplicationRepositoryInput input = new UpdateApplicationRepositoryInput();
        input.setName("UpdatedApp");
        input.setDescription("UpdatedDescription");
        input.setStatus(Status.production);
        input.setTime(Time.migrate);
        return input;
    }

    public CreateApplicationServiceInput createApplicationServiceInput() {
        return new CreateApplicationServiceInput(
                "App1",
                "Description1",
                LocalDateTime.now(),
                LocalDateTime.now(),
                Status.development,
                10,
                categoryChild.id,
                departement.id,
                classe.id,
                new CreateCostWithoutApp(1000.0, 500.0),
                new CreateTechBusinessValueWithoutApp(4.5, 2.0),
                List.of(
                // new CreateDocumentationFileWithoutApp(
                // new FileInput(Path.of("/docs/functional_doc.pdf"), "Functional
                // Documentation"),
                // DocumentationType.fonctionnelle),
                // new CreateDocumentationFileWithoutApp(
                // new FileInput(Path.of("/docs/technical_doc.pdf"), "Technical Documentation"),
                // DocumentationType.technique)
                ));
    }

    public UpdateApplicationServiceInput updateApplicationServiceInput() {
        return new UpdateApplicationServiceInput("App1",
                "Description1",
                LocalDateTime.now(),
                LocalDateTime.now(),
                Status.development,
                10,
                3.5,
                1.5,
                categoryChild.id,
                departement.id,
                classe.id,
                new CreateCostWithoutApp(1000.0, 500.0),
                new CreateTechBusinessValueWithoutApp(4.5, 2.0));
    }
}