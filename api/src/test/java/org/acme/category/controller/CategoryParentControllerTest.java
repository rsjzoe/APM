package org.acme.category.controller;

import static io.restassured.RestAssured.given;

import org.acme.application.infra.database.ApplicationEntity;
import org.acme.application.infra.database.ApplicationHistoryEntity;
import org.acme.category.CategoryData;
import org.acme.category.domain.input.UpdateCategoryODAParent;
import org.acme.category.infra.database.entity.CategoryODAChildEntity;
import org.acme.category.infra.database.entity.CategoryODAParentEntity;
import org.acme.classe.infra.database.ClasseEntity;
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
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
public class CategoryParentControllerTest {
    String path = "/category-oda-parent/";

    @Inject
    EntityManager em;

    @Inject
    CategoryData categoryData;

    @Inject
    UserData userData;

    @BeforeEach
    @Transactional
    public void setup() {
        categoryData.setup();
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
    public void testSaveCategoryParent() {
        var accessToken = userData.getUserAdminToken().getAccessToken();
        var createInput = categoryData.createCategoryODAParent();
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(createInput)
                .when()
                .post(path)
                .then()
                .statusCode(200)
                .body("name", equalTo(createInput.getName()))
                .body("bgColor", equalTo(createInput.getBgColor()))
                .body("id", notNullValue());
    }

    @Test
    @TestTransaction
    public void testFindAllCategoryParent() {
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
    public void testFindCategoryParentById() {
        var createdParent = categoryData.getCategoryParent();
        var accessToken = userData.getUserAdminToken().getAccessToken();
        given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get(path + createdParent.id)
                .then()
                .statusCode(200)
                .body("name", equalTo(createdParent.getName()))
                .body("bgColor", equalTo(createdParent.getBgColor()))
                .body("id", equalTo(Math.toIntExact(createdParent.id)));
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
    public void testUpdateCategoryParent() throws Exception {
        var createdParent = categoryData.getCategoryParent();
        var updateInput = categoryData.updateCategoryODAParent();
        var accessToken = userData.getUserAdminToken().getAccessToken();

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(updateInput)
                .when()
                .put(path + createdParent.id)
                .then()
                .statusCode(200)
                .body("name", equalTo(updateInput.getName()))
                .body("bgColor", equalTo(updateInput.getBgColor()))
                .body("id", equalTo(Math.toIntExact(createdParent.id)));
    }

    @Test
    @TestTransaction
    public void testUpdatePartialCategoryParent() throws Exception {
        var accessToken = userData.getUserAdminToken().getAccessToken();
        var createdParent = categoryData.getCategoryParent();
        var updateInput = new UpdateCategoryODAParent();
        updateInput.setName("name only");

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(updateInput)
                .when()
                .put(path + createdParent.id)
                .then()
                .statusCode(200)
                .body("name", equalTo(updateInput.getName()))
                .body("id", equalTo(Math.toIntExact(createdParent.id)));
    }

    @Test
    @TestTransaction
    public void testUpdateThrowsException() {
        var accessToken = userData.getUserAdminToken().getAccessToken();
        var updateInput = categoryData.updateCategoryODAParent();
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
    public void testDeleteCategoryParent() throws Exception {
        var createdParent = categoryData.getCategoryParent();
        var accessToken = userData.getUserAdminToken().getAccessToken();
        given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .delete(path + createdParent.id)
                .then()
                .statusCode(200)
                .body("id", equalTo(Math.toIntExact(createdParent.id)))
                .body("isDelete", equalTo(true));
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
