package org.acme.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.acme.application.ApplicationData;
import org.acme.application.app.service.ApplicationService;
import org.acme.application.domain.exception.ApplicationNotFoundException;
import org.acme.application.domain.input.UpdateApplicationServiceInput;
import org.acme.application.domain.output.ApplicationOutput;
import org.acme.application.infra.database.ApplicationEntity;
import org.acme.application.infra.database.ApplicationHistoryEntity;
import org.acme.category.domain.exception.CategoryODAChildNotFoundException;
import org.acme.category.infra.database.entity.CategoryODAChildEntity;
import org.acme.category.infra.database.entity.CategoryODAParentEntity;
import org.acme.classe.domain.exception.ClasseNotFoundException;
import org.acme.classe.infra.database.ClasseEntity;
import org.acme.cost.domain.exception.InvalidCostException;
import org.acme.cost.domain.model.input.CreateCostWithoutApp;
import org.acme.cost.infra.database.CostEntity;
import org.acme.departement.domain.exception.DepartementNotFoundException;
import org.acme.departement.infra.database.DepartementEntity;
import org.acme.documentation.adapter.out.DocumentationEntity;
import org.acme.question.infra.database.entity.QuestionEntity;
import org.acme.question.infra.database.entity.QuestionGroupEntity;
import org.acme.techBusinessValue.domain.exception.InvalidTechBusinessValueException;
import org.acme.techBusinessValue.domain.model.input.CreateTechBusinessValueWithoutApp;
import org.acme.techBusinessValue.infra.database.TechBusinessValueEntity;
import org.acme.user.UserData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@QuarkusTest
public class ApplicationServiceUpdateTest {

    @Inject
    EntityManager em;

    @Inject
    ApplicationData applicationData;

    @Inject
    ApplicationService applicationService;

    @Inject
    UserData userData;

    @BeforeEach
    @Transactional
    public void setup() {
        applicationData.setup();
        userData.setup();
    }

    @AfterEach
    @Transactional
    public void clear() {
        em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
        ApplicationEntity.deleteAll();
        CategoryODAChildEntity.deleteAll();
        CategoryODAParentEntity.deleteAll();
        ClasseEntity.deleteAll();
        DepartementEntity.deleteAll();
        DocumentationEntity.deleteAll();
        CostEntity.deleteAll();
        TechBusinessValueEntity.deleteAll();
        ApplicationHistoryEntity.deleteAll();
        QuestionGroupEntity.deleteAll();
        QuestionEntity.deleteAll();
        em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
        userData.clear();
    }

    @Test
    @TestTransaction
    public void testUpdateApplicationSuccess() throws Exception {
        String token = userData.getUserAdminToken().getAccessToken();
        UpdateApplicationServiceInput input = applicationData.updateApplicationServiceInput();
        ApplicationOutput output = applicationService.update(applicationData.getApplication1().id, input, token);

        assertNotNull(output);
        assertEquals(input.getName(), output.getName());
        assertEquals(input.getDescription(), output.getDescription());
    }

    @Test
    @TestTransaction
    public void testPartialUpdateApplicationSuccess() throws Exception {
        String token = userData.getUserAdminToken().getAccessToken();
        UpdateApplicationServiceInput input = new UpdateApplicationServiceInput();
        input.setName("name only");
        ApplicationOutput output = applicationService.update(applicationData.getApplication1().id, input, token);

        assertNotNull(output);
        assertEquals(input.getName(), output.getName());
    }

    @Test
    @TestTransaction
    public void testUpdateThrowsException() {
        String token = userData.getUserAdminToken().getAccessToken();
        UpdateApplicationServiceInput input = new UpdateApplicationServiceInput();
        assertThrows(ApplicationNotFoundException.class, () -> applicationService.update(999L, input, token));
    }

    @Test
    @TestTransaction
    public void testUpdateApplicationInvalidCost() {
        String token = userData.getUserAdminToken().getAccessToken();
        UpdateApplicationServiceInput input = new UpdateApplicationServiceInput();
        input.setCostWithoutApp(new CreateCostWithoutApp(-1000.0, 500.0));

        assertThrows(InvalidCostException.class,
                () -> applicationService.update(applicationData.getApplication1().id, input, token));
    }

    @Test
    @TestTransaction
    public void testUpdatepplicationInvalidTechBusinessValue() {
        String token = userData.getUserAdminToken().getAccessToken();
        UpdateApplicationServiceInput input = new UpdateApplicationServiceInput();
        input.setTechBusinessValueWithoutApp(new CreateTechBusinessValueWithoutApp(5.5, 2.0));

        assertThrows(InvalidTechBusinessValueException.class,
                () -> applicationService.update(applicationData.getApplication1().id, input, token));
    }

    @Test
    @TestTransaction
    public void testCreateApplicationCategoryNotFound() {
        String token = userData.getUserAdminToken().getAccessToken();
        UpdateApplicationServiceInput input = new UpdateApplicationServiceInput();
        input.setCategoryId(9999L);

        assertThrows(CategoryODAChildNotFoundException.class,
                () -> applicationService.update(applicationData.getApplication1().id, input, token));
    }

    @Test
    @TestTransaction
    public void testCreateApplicationDepartementNotFound() {
        String token = userData.getUserAdminToken().getAccessToken();
        UpdateApplicationServiceInput input = new UpdateApplicationServiceInput();
        input.setDepartementIds(List.of(9999L));

        assertThrows(DepartementNotFoundException.class,
                () -> applicationService.update(applicationData.getApplication1().id, input, token));
    }

    @Test
    @TestTransaction
    public void testCreateApplicationClasseNotFound() {
        String token = userData.getUserAdminToken().getAccessToken();
        UpdateApplicationServiceInput input = new UpdateApplicationServiceInput();
        input.setClasseId(9999L);

        assertThrows(ClasseNotFoundException.class,
                () -> applicationService.update(applicationData.getApplication1().id, input, token));
    }

}
