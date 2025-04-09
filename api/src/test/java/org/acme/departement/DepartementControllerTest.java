package org.acme.departement;

import org.acme.application.infra.database.ApplicationEntity;
import org.acme.application.infra.database.ApplicationHistoryEntity;
import org.acme.category.infra.database.entity.CategoryODAChildEntity;
import org.acme.category.infra.database.entity.CategoryODAParentEntity;
import org.acme.classe.infra.database.ClasseEntity;
import org.acme.cost.infra.database.CostEntity;
import org.acme.departement.app.DepartementService;
import org.acme.departement.infra.database.DepartementEntity;
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
    DepartementService departementService;

    DepartementEntity d1;
    DepartementEntity d2;

    @Inject
    UserData userData;

    @BeforeEach
    @Transactional
    void setup() {
        this.d1 = new DepartementEntity();
        this.d1.name = "RH";
        this.d1.persistAndFlush();

        this.d2 = new DepartementEntity();
        this.d2.name = "IT";
        this.d2.persistAndFlush();

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
    public void testListDepartement() {
        var accessToken = userData.getUserAdminToken().getAccessToken();

        given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/departements")
                .then()
                .statusCode(200)
                .body("$.size()", is(2))
                .body("name", hasItems("RH", "IT"));

    }

    @Test
    @TestTransaction
    public void testFindById() {
        var accessToken = userData.getUserAdminToken().getAccessToken();

        given()
                .header("Authorization", "Bearer " + accessToken)

                .when()
                .get("/departements/" + d1.id)
                .then()
                .statusCode(200)
                .body("id", equalTo(d1.id.intValue()))
                .body("name", equalTo("RH"));

    }

    @Test
    @TestTransaction
    public void testFindByIdNotFound() {
        var accessToken = userData.getUserAdminToken().getAccessToken();
        RestAssured
                .given()
                .header("Authorization", "Bearer " + accessToken)

                .when()
                .get("/departements/" + 9999)
                .then()
                .statusCode(404);
    }
}
