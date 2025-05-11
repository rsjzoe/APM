package org.acme.question.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

import org.acme.application.infra.database.ApplicationEntity;
import org.acme.application.infra.database.ApplicationHistoryEntity;
import org.acme.category.infra.database.entity.CategoryODAChildEntity;
import org.acme.category.infra.database.entity.CategoryODAParentEntity;
import org.acme.classe.infra.database.ClasseEntity;
import org.acme.cost.infra.database.CostEntity;
import org.acme.departement.infra.database.DepartementEntity;
import org.acme.documentation.adapter.out.DocumentationEntity;
import org.acme.question.QuestionData;
import org.acme.question.domain.input.UpdateQuestion;
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
public class QuestionControllerTest {
    String path = "/question/";

    @Inject
    EntityManager em;

    @Inject
    UserData userData;

    @Inject
    QuestionData questionData;

    @BeforeEach
    @Transactional
    public void setup() {
        questionData.setup();
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
    public void testSaveQuestion() {
        var accessToken = userData.getUserAdminToken().getAccessToken();
        var createInput = questionData.createQuestion();
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(createInput)
                .when()
                .post(path)
                .then()
                .statusCode(200)
                .body("text", equalTo(createInput.getText()))
                .body("id", notNullValue());
    }

    // @Test
    // @TestTransaction
    public void testFindAllQuestion() {
        var accessToken = userData.getUserAdminToken().getAccessToken();
        given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get(path)
                .then()
                .statusCode(200)
                .body("$.size()", greaterThan(0));
    }

    @Test
    @TestTransaction
    public void testFindCategoryChildById() {
        var createdChild = questionData.getQuestion();
        var accessToken = userData.getUserAdminToken().getAccessToken();
        given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get(path + createdChild.id)
                .then()
                .statusCode(200)
                .body("text", equalTo(createdChild.getText()))
                .body("id", equalTo(Math.toIntExact(createdChild.id)));
    }

    @Test
    @TestTransaction
    public void testFindByIdThrowsException() {
        var accessToken = userData.getUserAdminToken().getAccessToken();
        given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get(path + 999999)
                .then()
                .statusCode(404);
    }

    @Test
    @TestTransaction
    public void testUpdateQuestion() throws Exception {
        var createdChild = questionData.getQuestion();
        var updateInput = questionData.updateQuestion();
        var accessToken = userData.getUserAdminToken().getAccessToken();

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(updateInput)
                .when()
                .put(path + createdChild.id)
                .then()
                .statusCode(200)
                .body("text", equalTo(updateInput.getText()))
                .body("id", equalTo(Math.toIntExact(createdChild.id)));
    }

    @Test
    @TestTransaction
    public void testUpdatePartialQuestion() throws Exception {
        var accessToken = userData.getUserAdminToken().getAccessToken();
        var createdChild = questionData.getQuestion();
        var updateInput = new UpdateQuestion();
        updateInput.setText("text only");

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(updateInput)
                .when()
                .put(path + createdChild.id)
                .then()
                .statusCode(200)
                .body("text", equalTo(updateInput.getText()))
                .body("id", equalTo(Math.toIntExact(createdChild.id)));
    }

    @Test
    @TestTransaction
    public void testUpdateThrowsException() {
        var accessToken = userData.getUserAdminToken().getAccessToken();
        var updateInput = questionData.updateQuestion();
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(updateInput)
                .when()
                .put(path + 999999)
                .then()
                .statusCode(404);
    }

    @Test
    @TestTransaction
    public void testDeleteQuestion() throws Exception {
        var createdChild = questionData.getQuestion();
        var accessToken = userData.getUserAdminToken().getAccessToken();
        given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .delete(path + createdChild.id)
                .then()
                .statusCode(200)
                .body("id", equalTo(Math.toIntExact(createdChild.id)));
    }

    @Test
    @TestTransaction
    public void testDeleteThrowsException() {
        var accessToken = userData.getUserAdminToken().getAccessToken();
        given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .delete(path + 9999)
                .then()
                .statusCode(404);
    }

}
