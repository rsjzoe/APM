package org.acme.cost;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.time.LocalDateTime;

import org.acme.application.ApplicationData;
import org.acme.application.domain.exception.ApplicationNotFoundException;
import org.acme.application.infra.database.ApplicationEntity;
import org.acme.application.infra.database.ApplicationHistoryEntity;
import org.acme.category.infra.database.entity.CategoryODAChildEntity;
import org.acme.category.infra.database.entity.CategoryODAParentEntity;
import org.acme.classe.infra.database.ClasseEntity;
import org.acme.cost.app.CostService;
import org.acme.cost.domain.exception.InvalidCostException;
import org.acme.cost.domain.model.input.CreateCostInput;
import org.acme.cost.domain.model.output.CostOutput;
import org.acme.cost.infra.database.CostEntity;
import org.acme.departement.infra.database.DepartementEntity;
import org.acme.documentation.adapter.out.DocumentationEntity;
import org.acme.question.infra.database.entity.QuestionEntity;
import org.acme.question.infra.database.entity.QuestionGroupEntity;
import org.acme.techBusinessValue.infra.database.TechBusinessValueEntity;
import org.acme.user.UserData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@QuarkusTest
public class CostControllerTest {
    @Inject
    EntityManager em;

    @Inject
    CostService costService;

    @Inject
    UserData userData;

    CostOutput created1;

    @Inject
    ApplicationData applicationData;

    @BeforeEach
    @Transactional
    void setup() throws InvalidCostException, ApplicationNotFoundException {
        applicationData.setup();
        CreateCostInput input = new CreateCostInput(10, 30, applicationData.getApplication1().id);
        created1 = costService.createCost(input);

        LocalDateTime now = LocalDateTime.now();
        CostEntity janCost = new CostEntity();
        janCost.setApplication(applicationData.getApplication1());
        janCost.setCostBuild(120);
        janCost.setCostRun(70);
        janCost.setCreatedAt(now.withMonth(1));
        janCost.persist();

        CostEntity febCost = new CostEntity();
        febCost.setApplication(applicationData.getApplication1());
        febCost.setCostBuild(140);
        febCost.setCostRun(80);
        febCost.setCreatedAt(now.withMonth(2));
        febCost.persist();

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
    void testCreateCost() {
        CreateCostInput input = new CreateCostInput(10, 30, applicationData.getApplication1().id);
        var accessToken = userData.getUserAdminToken().getAccessToken();

        given()
                .contentType(ContentType.JSON)
                .body(input)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .post("/cost")
                .then()
                .statusCode(200)
                .body("applicationId", equalTo(applicationData.getApplication1().id.intValue()))
                .body("costBuild", equalTo(10.0F))
                .body("costRun", equalTo(30.0F));
    }

    @Test
    @TestTransaction
    public void testCreateCostInvalid() {
        CreateCostInput input = new CreateCostInput(-10, 30, applicationData.getApplication1().id);
        var accessToken = userData.getUserAdminToken().getAccessToken();

        given()
                .contentType(ContentType.JSON)
                .body(input)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .post("/cost")
                .then()
                .statusCode(400);
    }

    @Test
    @TestTransaction
    public void testFindCostByAppId() {
        var accessToken = userData.getUserAdminToken().getAccessToken();

        given()
                .contentType(ContentType.JSON)
                .body(created1)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/cost/" + applicationData.getApplication1().id)
                .then()
                .statusCode(200)
                .body("$.size()", is(3))
                .body("[0].applicationId", equalTo(applicationData.getApplication1().id.intValue()))
                .body("[0].costBuild", equalTo(10.0F))
                .body("[0].costRun", equalTo(30.0F));
    }

    @Test
    @TestTransaction
    public void testFindCostByAppIdNotFound() {
        var accessToken = userData.getUserAdminToken().getAccessToken();

        given()
                .contentType(ContentType.JSON)
                .body(created1)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/cost/999999")
                .then()
                .statusCode(200)
                .body("$.size()", is(0));
    }

    @Test
    @TestTransaction
    public void testLatestCostPerMonth() {
        var accessToken = userData.getUserAdminToken().getAccessToken();

        given()
                .pathParam("appId", applicationData.getApplication1().id)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/cost/latest-per-month/{appId}")
                .then()
                .statusCode(200)
                .body("size()", is(12)) // un par mois
                .body("findAll { it.monthValue == 1 }[0].data.costBuild", equalTo(120F))
                .body("findAll { it.monthValue == 2 }[0].data.costRun", equalTo(80F));
    }
}
