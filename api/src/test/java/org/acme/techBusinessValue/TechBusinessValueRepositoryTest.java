package org.acme.techBusinessValue;

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

import org.acme.cost.infra.database.CostEntity;
import org.acme.departement.infra.database.DepartementEntity;
import org.acme.documentation.adapter.out.DocumentationEntity;
import org.acme.question.infra.database.QuestionEntity;
import org.acme.question.infra.database.QuestionGroupEntity;
import org.acme.techBusinessValue.domain.model.input.CreateTechBusinessValue;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueMonth;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueOutput;
import org.acme.techBusinessValue.infra.database.TechBusinessValueEntity;
import org.acme.techBusinessValue.infra.database.TechBusinessValueEntityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@QuarkusTest
public class TechBusinessValueRepositoryTest {
    @Inject
    EntityManager em;

    TechBusinessValueEntityRepository repository;

    @BeforeEach
    @Transactional
    void setup() {
        repository = new TechBusinessValueEntityRepository();
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
    void testCreateAndFindTechBusinessValueByAppId() {
        ApplicationEntity app = new ApplicationEntity();
        app.persist();

        CreateTechBusinessValue input = new CreateTechBusinessValue(1, 2, app.id);
        TechBusinessValueOutput created = repository.createTechBusinessValue(input);

        assertNotNull(created);
        assertEquals(1, created.getBusinessValue());
        assertEquals(2, created.getTechnicalDebt());
        assertEquals(app.id, created.getApplicationId());

        List<TechBusinessValueOutput> techBusinessValues = repository
                .findTechBusinessValueByAppId(app.id);
        assertFalse(techBusinessValues.isEmpty());
        assertEquals(1, techBusinessValues.size());
        assertEquals(1, techBusinessValues.get(0).getBusinessValue());
        assertEquals(2, techBusinessValues.get(0).getTechnicalDebt());
    }

    @Test
    @Transactional
    void testUpdateTechbusinessValue() {
        ApplicationEntity app1 = new ApplicationEntity();
        app1.persist();

        ApplicationEntity app2 = new ApplicationEntity();
        app2.persist();

        CreateTechBusinessValue input = new CreateTechBusinessValue(1.5, 2, app1.id);
        TechBusinessValueOutput created = repository.createTechBusinessValue(input);

        TechBusinessValueOutput updated = repository.update(created.getId(), app2.id);

        assertNotNull(updated);
        assertEquals(app2.id, updated.getApplicationId());
    }

    @Test
    @Transactional
    void testFindTechbusinessvalueLatestPerMonthByAppId() {
        // Créer une application
        ApplicationEntity app = new ApplicationEntity();
        app.persist();

        // Ajouter plusieurs coûts à différentes dates
        TechBusinessValueEntity techBusinessValueJan = new TechBusinessValueEntity(1.0, 2.0,
                LocalDateTime.of(2024, 1, 15, 10, 0));
        techBusinessValueJan.setApplication(app);
        techBusinessValueJan.persist();

        TechBusinessValueEntity techBusinessValueFeb = new TechBusinessValueEntity(1.5, 3,
                LocalDateTime.of(2024, 2, 20, 12, 0));
        techBusinessValueFeb.setApplication(app);
        techBusinessValueFeb.persist();

        TechBusinessValueEntity techBusinessValueFebLater = new TechBusinessValueEntity(4, 2.5,
                LocalDateTime.of(2024, 2, 28, 15, 0));
        techBusinessValueFebLater.setApplication(app);
        techBusinessValueFebLater.persist();

        List<TechBusinessValueMonth> latestPerMonth = repository.findTechBusinessValueLatestPerMonthByAppId(app.id);

        assertNotNull(latestPerMonth);
        assertEquals(12, latestPerMonth.size()); // 12 mois dans l'année

        TechBusinessValueMonth janData = latestPerMonth.get(0);
        if (janData.getData() != null) {
            assertEquals(1.0, janData.getData().getBusinessValue());
            assertEquals(2.0, janData.getData().getTechnicalDebt());
        }

        TechBusinessValueMonth febData = latestPerMonth.get(1);
        if (febData.getData() != null) {
            assertEquals(1.5, febData.getData().getBusinessValue()); // Février (dernier coût ajouté)
            assertEquals(3.0, febData.getData().getTechnicalDebt());
        }

        for (int i = 2; i < 12; i++) {
            assertNull(latestPerMonth.get(i).getData(), "Les autres mois doivent être vides");
        }
    }

}
