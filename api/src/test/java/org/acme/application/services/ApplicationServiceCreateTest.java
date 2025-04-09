package org.acme.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.acme.application.ApplicationData;
import org.acme.application.app.service.ApplicationService;
import org.acme.application.domain.exception.InvalidApplicationException;
import org.acme.application.domain.input.CreateApplicationServiceInput;
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
import org.acme.question.infra.database.QuestionEntity;
import org.acme.question.infra.database.QuestionGroupEntity;
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
public class ApplicationServiceCreateTest {
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
    public void testCreateApplicationSuccess() throws Exception {
        String token = userData.getUserAdminToken().getAccessToken();
        CreateApplicationServiceInput input = applicationData.createApplicationServiceInput();
        ApplicationOutput output = applicationService.create(input, token);

        assertNotNull(output);
        assertEquals(input.getName(), output.getName());
        assertEquals(input.getDescription(), output.getDescription());
    }

    @Test
    @TestTransaction
    public void testCreateApplicationInvalidDates() {
        String token = userData.getUserAdminToken().getAccessToken();
        CreateApplicationServiceInput input = applicationData.createApplicationServiceInput();
        input.setStartDate(LocalDateTime.now().plusDays(1));

        assertThrows(InvalidApplicationException.class, () -> applicationService.create(input, token));
    }

    @Test
    @TestTransaction
    public void testCreateApplicationInvalidCost() {
        String token = userData.getUserAdminToken().getAccessToken();
        CreateApplicationServiceInput input = applicationData.createApplicationServiceInput();
        input.setCostWithoutApp(new CreateCostWithoutApp(-1000.0, 500.0));

        assertThrows(InvalidCostException.class, () -> applicationService.create(input, token));
    }

    @Test
    @TestTransaction
    public void testCreateApplicationInvalidTechBusinessValue() {
        String token = userData.getUserAdminToken().getAccessToken();
        CreateApplicationServiceInput input = applicationData.createApplicationServiceInput();
        input.setTechBusinessValueWithoutApp(new CreateTechBusinessValueWithoutApp(5.5, 2.0));

        assertThrows(InvalidTechBusinessValueException.class, () -> applicationService.create(input, token));
    }

    @Test
    @TestTransaction
    public void testCreateApplicationCategoryNotFound() {
        String token = userData.getUserAdminToken().getAccessToken();
        CreateApplicationServiceInput input = applicationData.createApplicationServiceInput();
        input.setCategoryId(9999L);

        assertThrows(CategoryODAChildNotFoundException.class, () -> applicationService.create(input, token));
    }

    @Test
    @TestTransaction
    public void testCreateApplicationDepartementNotFound() {
        String token = userData.getUserAdminToken().getAccessToken();
        CreateApplicationServiceInput input = applicationData.createApplicationServiceInput();
        input.setDepartementId(9999L);

        assertThrows(DepartementNotFoundException.class, () -> applicationService.create(input, token));
    }

    @Test
    @TestTransaction
    public void testCreateApplicationClasseNotFound() {
        String token = userData.getUserAdminToken().getAccessToken();
        CreateApplicationServiceInput input = applicationData.createApplicationServiceInput();
        input.setClasseId(9999L);

        assertThrows(ClasseNotFoundException.class, () -> applicationService.create(input, token));
    }
}
