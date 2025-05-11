package org.acme.category.controller;

import org.acme.application.infra.database.ApplicationEntity;
import org.acme.application.infra.database.ApplicationHistoryEntity;
import org.acme.category.CategoryData;
import org.acme.category.domain.input.UpdateCategoryODAChild;
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

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
public class CategoryChildControllerTest {
    String path = "/category-oda-child/";

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
    public void testSaveCategoryChild() {
        var accessToken = userData.getUserAdminToken().getAccessToken();
        var createInput = categoryData.createCategoryODAChild();
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(createInput)
                .when()
                .post(path)
                .then()
                .statusCode(200)
                .body("name", equalTo(createInput.getName()))
                .body("parentId", equalTo(Math.toIntExact(createInput.getParentId())))
                .body("id", notNullValue());
    }

    @Test
    @TestTransaction
    public void testFindAllCategoryChild() {
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
        var createdChild = categoryData.getCategoryChild();
        var accessToken = userData.getUserAdminToken().getAccessToken();
        given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get(path + createdChild.id)
                .then()
                .statusCode(200)
                .body("name", equalTo(createdChild.getName()))
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
    public void testUpdateCategoryChild() throws Exception {
        var createdChild = categoryData.getCategoryChild();
        var updateInput = categoryData.updateCategoryODAChild();
        var accessToken = userData.getUserAdminToken().getAccessToken();

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(updateInput)
                .when()
                .put(path + createdChild.id)
                .then()
                .statusCode(200)
                .body("name", equalTo(updateInput.getName()))
                .body("parentId", equalTo(Math.toIntExact(updateInput.getParentId())))
                .body("id", equalTo(Math.toIntExact(createdChild.id)));
    }

    @Test
    @TestTransaction
    public void testUpdatePartialCategoryChild() throws Exception {
        var accessToken = userData.getUserAdminToken().getAccessToken();
        var createdChild = categoryData.getCategoryChild();
        var updateInput = new UpdateCategoryODAChild();
        updateInput.setName("name only");

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(updateInput)
                .when()
                .put(path + createdChild.id)
                .then()
                .statusCode(200)
                .body("name", equalTo(updateInput.getName()))
                .body("id", equalTo(Math.toIntExact(createdChild.id)));
    }

    @Test
    @TestTransaction
    public void testUpdateThrowsException() {
        var accessToken = userData.getUserAdminToken().getAccessToken();
        var updateInput = categoryData.updateCategoryODAChild();
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
    public void testDeleteCategoryChild() throws Exception {
        var createdChild = categoryData.getCategoryChild();
        var accessToken = userData.getUserAdminToken().getAccessToken();
        given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .delete(path + createdChild.id)
                .then()
                .statusCode(200)
                .body("id", equalTo(Math.toIntExact(createdChild.id)))
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
