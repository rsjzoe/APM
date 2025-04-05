package org.acme.techBusinessValue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;

import org.acme.application.ApplicationData;
import org.acme.application.domain.exception.ApplicationNotFoundException;
import org.acme.application.infra.database.ApplicationEntity;
import org.acme.application.infra.database.ApplicationHistoryEntity;
import org.acme.category.infra.out.Entity.CategoryODAChildEntity;
import org.acme.category.infra.out.Entity.CategoryODAParentEntity;
import org.acme.classe.infra.database.ClasseEntity;
import org.acme.cost.infra.database.CostEntity;
import org.acme.departement.infra.database.DepartementEntity;
import org.acme.documentation.adapter.out.DocumentationEntity;
import org.acme.question.infra.database.QuestionEntity;
import org.acme.question.infra.database.QuestionGroupEntity;
import org.acme.techBusinessValue.app.TechBusinessValueService;
import org.acme.techBusinessValue.domain.exception.InvalidTechBusinessValueException;
import org.acme.techBusinessValue.domain.model.input.CreateTechBusinessValue;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueMonth;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueOutput;
import org.acme.techBusinessValue.infra.database.TechBusinessValueEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@QuarkusTest
public class TechBusinessValueSericeTest {
    @Inject
    EntityManager em;

    @Inject
    TechBusinessValueService techBusinessValueService;

    @Inject
    ApplicationData applicationData;

    @BeforeEach
    @Transactional
    public void setup() {
        applicationData.setup();
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
    }

    @Test
    @Transactional
    void testCreateAndFindTechBusinessValueByAppId()
            throws InvalidTechBusinessValueException, ApplicationNotFoundException {

        CreateTechBusinessValue input = new CreateTechBusinessValue(1, 2, applicationData.getApplication1().id);
        TechBusinessValueOutput created = techBusinessValueService.createTechBusinessValueOutput(input);

        assertNotNull(created);
        assertEquals(1, created.getBusinessValue());
        assertEquals(2, created.getTechnicalDebt());
        assertEquals(applicationData.getApplication1().id, created.getApplicationId());

        List<TechBusinessValueOutput> techBusinessValues = techBusinessValueService
                .findTechBusinessValueOutputByAppId(applicationData.getApplication1().id);
        assertFalse(techBusinessValues.isEmpty());
        assertEquals(1, techBusinessValues.size());
        assertEquals(1, techBusinessValues.get(0).getBusinessValue());
        assertEquals(2, techBusinessValues.get(0).getTechnicalDebt());
    }

    @Test
    @TestTransaction
    void testCreateTechbusinessValueWithNullAppId()
            throws InvalidTechBusinessValueException, ApplicationNotFoundException {
        CreateTechBusinessValue input = new CreateTechBusinessValue(1.0, 2.3, null);
        TechBusinessValueOutput created = techBusinessValueService.createTechBusinessValueOutput(input);

        assertNotNull(created);
        assertEquals(1.0, created.getBusinessValue());
        assertEquals(2.3, created.getTechnicalDebt());
    }

    @Test
    @TestTransaction
    void testCreateTechBusinessValueThrowsAppNotFoundException() {
        CreateTechBusinessValue input = new CreateTechBusinessValue(3.0, 1.6, 999L);

        assertThrows(ApplicationNotFoundException.class, () -> {
            techBusinessValueService.createTechBusinessValueOutput(input);
        });
    }

    @Test
    @TestTransaction
    void testCreateTechBusinessValueInvalid() {
        CreateTechBusinessValue input = new CreateTechBusinessValue(-2.0, 4.2, null);

        assertThrows(InvalidTechBusinessValueException.class, () -> {
            techBusinessValueService.createTechBusinessValueOutput(input);
        });
    }

    @Test
    @Transactional
    void testUpdateTechBusinessValue() throws InvalidTechBusinessValueException, ApplicationNotFoundException {

        CreateTechBusinessValue input = new CreateTechBusinessValue(2.2, 3.0, null);
        TechBusinessValueOutput created = techBusinessValueService.createTechBusinessValueOutput(input);

        TechBusinessValueOutput updated = techBusinessValueService.updateTechBusinessValueOutput(created.getId(),
                applicationData.getApplication1().id);

        assertNotNull(updated);
        assertEquals(applicationData.getApplication1().id, updated.getApplicationId());
    }

    @Test
    @TestTransaction
    void testUpdateTechBusinessValueThrowsAppNotFoundException()
            throws InvalidTechBusinessValueException, ApplicationNotFoundException {

        CreateTechBusinessValue input = new CreateTechBusinessValue(1.0, 2.0, null);
        TechBusinessValueOutput created = techBusinessValueService.createTechBusinessValueOutput(input);

        assertThrows(ApplicationNotFoundException.class, () -> {
            techBusinessValueService.updateTechBusinessValueOutput(created.getId(), 999L);
        });
    }

    @Test
    @TestTransaction
    void testFindTechBusinessValueLatestPerMonthByAppId() {

        TechBusinessValueEntity techBusinessValue1 = new TechBusinessValueEntity(1.0, 2.0,
                LocalDateTime.now().minusMonths(2));
        techBusinessValue1.setApplication(applicationData.getApplication1());
        techBusinessValue1.persist();

        TechBusinessValueEntity techBusinessValue2 = new TechBusinessValueEntity(3.1, 4.0,
                LocalDateTime.now().minusMonths(1));
        techBusinessValue2.setApplication(applicationData.getApplication1());
        techBusinessValue2.persist();

        List<TechBusinessValueMonth> latestPerMonth = techBusinessValueService
                .findTechBusinessValueLatestPerMonthByAppId(applicationData.getApplication1().id);

        assertFalse(latestPerMonth.isEmpty());
    }

}
