package org.acme.techBusinessValue;

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
import org.acme.cost.infra.database.CostEntity;
import org.acme.departement.infra.database.DepartementEntity;
import org.acme.documentation.adapter.out.DocumentationEntity;
import org.acme.question.infra.database.entity.QuestionEntity;
import org.acme.question.infra.database.entity.QuestionGroupEntity;
import org.acme.techBusinessValue.app.TechBusinessValueService;
import org.acme.techBusinessValue.domain.exception.InvalidTechBusinessValueException;
import org.acme.techBusinessValue.domain.model.input.CreateTechBusinessValue;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueOutput;
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
public class TechBusinessValueControllerTest {
    @Inject
    EntityManager em;

    @Inject
    TechBusinessValueService techBusinessValueService;

    @Inject
    UserData userData;

    TechBusinessValueOutput created1;

    @Inject
    ApplicationData applicationData;

    @BeforeEach
    @Transactional
    void setup() throws InvalidTechBusinessValueException, ApplicationNotFoundException {
        applicationData.setup();
        CreateTechBusinessValue input = new CreateTechBusinessValue(1, 2.5, applicationData.getApplication1().id);
        created1 = techBusinessValueService.createTechBusinessValueOutput(input);

        LocalDateTime now = LocalDateTime.now();
        TechBusinessValueEntity janTechBusinessValue = new TechBusinessValueEntity();
        janTechBusinessValue.setApplication(applicationData.getApplication1());
        janTechBusinessValue.setBusinessValue(2.3);
        janTechBusinessValue.setTechnicalDebt(4);
        janTechBusinessValue.setCreatedAt(now.withMonth(1));
        janTechBusinessValue.persist();

        TechBusinessValueEntity febTechBusinessValue = new TechBusinessValueEntity();
        febTechBusinessValue.setApplication(applicationData.getApplication1());
        febTechBusinessValue.setBusinessValue(3);
        febTechBusinessValue.setTechnicalDebt(1);
        febTechBusinessValue.setCreatedAt(now.withMonth(2));
        febTechBusinessValue.persist();

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
    void testCreateTechBusinessValue() {
        CreateTechBusinessValue input = new CreateTechBusinessValue(2, 1.5, applicationData.getApplication1().id);
        var accessToken = userData.getUserAdminToken().getAccessToken();

        given()
                .contentType(ContentType.JSON)
                .body(input)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .post("/techBusinessvalue")
                .then()
                .statusCode(200)
                .body("applicationId", equalTo(applicationData.getApplication1().id.intValue()))
                .body("businessValue", equalTo(2.0F))
                .body("technicalDebt", equalTo(1.5F));
    }

    @Test
    @TestTransaction
    public void testCreateTechBusinessValueInvalid() {
        CreateTechBusinessValue input = new CreateTechBusinessValue(-2, 1.5, applicationData.getApplication1().id);
        var accessToken = userData.getUserAdminToken().getAccessToken();

        given()
                .contentType(ContentType.JSON)
                .body(input)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .post("/techBusinessvalue")
                .then()
                .statusCode(400);
    }

    @Test
    @TestTransaction
    public void testFindTechBusinessValueByAppId() {
        var accessToken = userData.getUserAdminToken().getAccessToken();

        given()
                .contentType(ContentType.JSON)
                .body(created1)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/techBusinessvalue/" + applicationData.getApplication1().id)
                .then()
                .statusCode(200)
                .body("$.size()", is(3))
                .body("[0].applicationId", equalTo(applicationData.getApplication1().id.intValue()))
                .body("[0].businessValue", equalTo(1.0F))
                .body("[0].technicalDebt", equalTo(2.5F));
    }

    @Test
    @TestTransaction
    public void testFindTechBusinessValueByAppIdNotFound() {
        var accessToken = userData.getUserAdminToken().getAccessToken();

        given()
                .contentType(ContentType.JSON)
                .body(created1)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/techBusinessvalue/999999")
                .then()
                .statusCode(200)
                .body("$.size()", is(0));
    }

    @Test
    @TestTransaction
    public void testLatestTechBusinessValuePerMonth() {
        var accessToken = userData.getUserAdminToken().getAccessToken();
        int currentYear = LocalDateTime.now().getYear();

        given()
                .pathParam("appId", applicationData.getApplication1().id)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/techBusinessvalue/latest-per-month/{appId}?year=" + currentYear)
                .then()
                .statusCode(200)
                .body("size()", is(12)) // un par mois
                .body("findAll { it.monthValue == 1 }[0].data.businessValue", equalTo(2.3F))
                .body("findAll { it.monthValue == 2 }[0].data.technicalDebt", equalTo(1F));
    }
}
