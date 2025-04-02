package org.acme.cost;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;
import java.util.List;

import org.acme.application.infra.database.ApplicationEntity;
import org.acme.application.infra.database.ApplicationHistoryEntity;
import org.acme.category.infra.out.Entity.CategoryODAChildEntity;
import org.acme.category.infra.out.Entity.CategoryODAParentEntity;
import org.acme.classe.infra.database.ClasseEntity;
import org.acme.cost.domain.model.input.CreateCostInput;
import org.acme.cost.domain.model.output.CostOutput;
import org.acme.cost.domain.model.output.CostOutputMonth;
import org.acme.cost.infra.database.CostEntity;
import org.acme.cost.infra.database.CostEntityRepository;
import org.acme.departement.infra.database.DepartementEntity;
import org.acme.documentation.adapter.out.DocumentationEntity;
import org.acme.question.infra.database.QuestionEntity;
import org.acme.question.infra.database.QuestionGroupEntity;
import org.acme.techBusinessValue.infra.database.TechBusinessValueEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@QuarkusTest
public class costRepositoryTest {
    @Inject
    EntityManager em;

    CostEntityRepository costRepository;

    @BeforeEach
    @Transactional
    void setup() {
        costRepository = new CostEntityRepository();
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
    void testCreateAndFindCostByAppId() {
        ApplicationEntity app = new ApplicationEntity();
        app.persist();

        CreateCostInput input = new CreateCostInput(100.0, 50.0, app.id);
        CostOutput created = costRepository.createCost(input);

        assertNotNull(created);
        assertEquals(100.0, created.getCostBuild());
        assertEquals(50.0, created.getCostRun());
        assertEquals(app.id, created.getApplicationId());

        List<CostOutput> costs = costRepository.findCostByAppId(app.id);
        assertFalse(costs.isEmpty());
        assertEquals(1, costs.size());
        assertEquals(100.0, costs.get(0).getCostBuild());
    }

    @Test
    @Transactional
    void testUpdateCost() {
        ApplicationEntity app1 = new ApplicationEntity();
        app1.persist();

        ApplicationEntity app2 = new ApplicationEntity();
        app2.persist();

        CreateCostInput input = new CreateCostInput(200.0, 80.0, app1.id);
        CostOutput created = costRepository.createCost(input);

        CostOutput updated = costRepository.update(created.getId(), app2.id);

        assertNotNull(updated);
        assertEquals(app2.id, updated.getApplicationId());
    }

    @Test
    @Transactional
    void testFindCostLatestPerMonthByAppId() {
        // Créer une application
        ApplicationEntity app = new ApplicationEntity();
        app.persist();

        // Ajouter plusieurs coûts à différentes dates
        CostEntity costJan = new CostEntity(150.0, 75.0, LocalDateTime.of(2024, 1, 15, 10, 0));
        costJan.setApplication(app);
        costJan.persist();

        CostEntity costFeb = new CostEntity(180.0, 90.0, LocalDateTime.of(2024, 2, 20, 12, 0));
        costFeb.setApplication(app);
        costFeb.persist();

        CostEntity costFebLater = new CostEntity(200.0, 100.0, LocalDateTime.of(2024, 2, 28, 15, 0));
        costFebLater.setApplication(app);
        costFebLater.persist();

        List<CostOutputMonth> latestPerMonth = costRepository.findCostLatestPerMonthByAppId(app.id);

        assertNotNull(latestPerMonth);
        assertEquals(12, latestPerMonth.size()); // 12 mois dans l'année

        CostOutputMonth janData = latestPerMonth.get(0);
        if (janData.getData() != null) {
            assertEquals(150.0, janData.getData().getCostBuild());
        }

        CostOutputMonth febData = latestPerMonth.get(1);
        if (febData.getData() != null) {
            assertEquals(200.0, febData.getData().getCostBuild()); // Février (dernier coût ajouté)
        }

        for (int i = 2; i < 12; i++) {
            assertNull(latestPerMonth.get(i).getData(), "Les autres mois doivent être vides");
        }
    }
}
