package org.acme.departement;

import org.acme.application.infra.database.ApplicationEntity;
import org.acme.application.infra.database.ApplicationHistoryEntity;
import org.acme.category.infra.out.Entity.CategoryODAChildEntity;
import org.acme.category.infra.out.Entity.CategoryODAParentEntity;
import org.acme.classe.infra.database.ClasseEntity;
import org.acme.cost.infra.database.CostEntity;
import org.acme.departement.infra.database.DepartementEntity;
import org.acme.departement.infra.database.DepartementEntityRepository;
import org.acme.documentation.adapter.out.DocumentationEntity;
import org.acme.question.infra.database.QuestionEntity;
import org.acme.question.infra.database.QuestionGroupEntity;
import org.acme.techBusinessValue.infra.database.TechBusinessValueEntity;
import org.acme.user.UserData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@QuarkusTest
public class DepartementControllerTest {
    @Inject
    EntityManager em;

    @Inject
    DepartementEntityRepository departementRepository;

    @Inject
    UserData userData;

    @BeforeEach
    @Transactional
    void setup() {
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
    public void testListDepartementEndpoint() {
        var accessToken = userData.getUserAdminToken().getAccessToken();
        DepartementEntity d1 = new DepartementEntity();
        d1.name = "RH";
        d1.persist();

        DepartementEntity d2 = new DepartementEntity();
        d2.name = "IT";
        d2.persist();

        given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/departement")
                .then()
                .statusCode(200)
                .body("$.size()", is(2))
                .body("name", hasItems("RH", "IT"));

    }

    @Test
    @TestTransaction
    public void testFindById() {
        var accessToken = userData.getUserAdminToken().getAccessToken();
        DepartementEntity d = new DepartementEntity();
        d.name = "Finance";
        d.persist();

        given()
                .header("Authorization", "Bearer " + accessToken)
                .pathParam("id", d.id)
                .when()
                .get("/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(d.id.intValue()))
                .body("name", equalTo("Finance"));

    }

    @Test
    @TestTransaction
    public void testFindByIdNotFound() {
        var accessToken = userData.getUserAdminToken().getAccessToken();
        RestAssured
                .given()
                .header("Authorization", "Bearer " + accessToken)
                .pathParam("id", 9999)
                .when()
                .get("/{id}")
                .then()
                .statusCode(404);
    }
}
