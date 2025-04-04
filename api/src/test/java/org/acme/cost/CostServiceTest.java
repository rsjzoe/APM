package org.acme.cost;

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
import org.acme.cost.app.CostService;
import org.acme.cost.domain.exception.InvalidCostException;
import org.acme.cost.domain.model.input.CreateCostInput;
import org.acme.cost.domain.model.output.CostOutput;
import org.acme.cost.domain.model.output.CostOutputMonth;
import org.acme.cost.infra.database.CostEntity;
import org.acme.departement.infra.database.DepartementEntity;
import org.acme.documentation.adapter.out.DocumentationEntity;
import org.acme.question.infra.database.QuestionEntity;
import org.acme.question.infra.database.QuestionGroupEntity;
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
public class CostServiceTest {
    @Inject
    EntityManager em;

    @Inject
    CostService costService;

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
    @TestTransaction
    void testCreateCostAndFindByAppId() throws InvalidCostException, ApplicationNotFoundException {

        CreateCostInput costInput = new CreateCostInput(200.0, 100.0, applicationData.getApplication1().id);
        CostOutput createdCost = costService.createCost(costInput);

        assertNotNull(createdCost);
        assertEquals(200.0, createdCost.getCostBuild());
        assertEquals(100.0, createdCost.getCostRun());

        List<CostOutput> costs = costService.findCostByAppId(applicationData.getApplication1().id);
        assertNotNull(costs);
        assertFalse(costs.isEmpty());
        assertEquals(1, costs.size());
        assertEquals(200.0, costs.get(0).getCostBuild());
    }

    // @Test
    // @TestTransaction
    void testCreateCostWithNullAppId() throws InvalidCostException, ApplicationNotFoundException {
        CreateCostInput costInput = new CreateCostInput(200.0, 100.0, null);
        CostOutput createdCost = costService.createCost(costInput);

        assertNotNull(createdCost);
        assertEquals(200.0, createdCost.getCostBuild());
        assertEquals(100.0, createdCost.getCostRun());
    }

    // @Test
    // @TestTransaction
    void testCreateCostThrowsAppNotFoundException() {
        CreateCostInput costInput = new CreateCostInput(200.0, 100.0, 999L);

        assertThrows(ApplicationNotFoundException.class, () -> {
            costService.createCost(costInput);
        });

    }

    // @Test
    // @TestTransaction
    void testCreateCostInvalid() {
        CreateCostInput costInput = new CreateCostInput(-200.0, 100.0, null);

        assertThrows(InvalidCostException.class, () -> {
            costService.createCost(costInput);
        });
    }

    // @Test
    // @Transactional
    void testUpdateCost() throws InvalidCostException, ApplicationNotFoundException {
        ApplicationEntity app = new ApplicationEntity();
        app.persist();

        CreateCostInput input = new CreateCostInput(200.0, 80.0, null);
        CostOutput created = costService.createCost(input);

        CostOutput updated = costService.updateCost(created.getId(), app.id);

        assertNotNull(updated);
        assertEquals(app.id, updated.getApplicationId());
    }

    // @Test
    // @TestTransaction
    void testUpdateCostThrowsAppNotFoundException() throws InvalidCostException, ApplicationNotFoundException {
        ApplicationEntity app = new ApplicationEntity();
        app.persist();

        CreateCostInput input = new CreateCostInput(200.0, 80.0, null);
        CostOutput created = costService.createCost(input);

        assertThrows(ApplicationNotFoundException.class, () -> {
            costService.updateCost(created.getId(), 999L);
        });
    }

    // @Test
    // @TestTransaction
    void testFindCostLatestPerMonthByAppId() {
        ApplicationEntity app = new ApplicationEntity();

        app.setName("Test App");
        app.persist();

        CostEntity cost1 = new CostEntity(100.0, 50.0, LocalDateTime.now().minusMonths(2));
        cost1.setApplication(app);
        cost1.persist();

        CostEntity cost2 = new CostEntity(200.0, 80.0, LocalDateTime.now().minusMonths(1));
        cost2.setApplication(app);
        cost2.persist();

        List<CostOutputMonth> latestPerMonth = costService.findCostLatestPerMonthByAppId(app.id);

        assertFalse(latestPerMonth.isEmpty());
        assertNotNull(latestPerMonth.get(0).getData());
    }

}
