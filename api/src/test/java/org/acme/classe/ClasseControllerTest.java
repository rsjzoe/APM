package org.acme.classe;

import static io.restassured.RestAssured.given;

import org.acme.application.infra.database.ApplicationEntity;
import org.acme.application.infra.database.ApplicationHistoryEntity;
import org.acme.category.infra.out.Entity.CategoryODAChildEntity;
import org.acme.category.infra.out.Entity.CategoryODAParentEntity;
import org.acme.classe.app.ClasseService;
import org.acme.classe.domain.input.CreateClasseInput;
import org.acme.classe.domain.input.UpdateClasse;
import org.acme.classe.domain.output.ClasseOutput;
import org.acme.classe.infra.database.ClasseEntity;
import org.acme.classe.infra.database.ClasseEntityRepository;
import org.acme.cost.infra.database.CostEntity;
import org.acme.departement.infra.out.DepartementEntity;
import org.acme.documentation.adapter.out.DocumentationEntity;
import org.acme.question.infra.database.QuestionEntity;
import org.acme.question.infra.database.QuestionGroupEntity;
import org.acme.techBusinessValue.infra.database.TechBusinessValueEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@QuarkusTest
public class ClasseControllerTest {
    @Inject
    EntityManager em;

    private ClasseService classeService;
    private ClasseEntityRepository classeRepository;
    private ClasseControllerTest classeController;

    @BeforeEach
    void setup() {
        classeRepository = new ClasseEntityRepository();
        classeService = new ClasseService(classeRepository);
        classeController = new ClasseControllerTest();
        classeController.classeService = classeService;
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
    void testCreate() {
        CreateClasseInput input = new CreateClasseInput("Classe A", "Description");

        given()
                .contentType(ContentType.JSON)
                .body(input)
                .when()
                .post("/classe")
                .then()
                .statusCode(200)
                .body("name", equalTo("Classe A"))
                .body("description", equalTo("Description"))
                .body("id", notNullValue());
    }

    @Test
    @TestTransaction
    void testGetListAll() {
        CreateClasseInput input1 = new CreateClasseInput("Classe A", "Description");
        CreateClasseInput input2 = new CreateClasseInput("Classe B", "Description B");

        classeService.create(input1);
        classeService.create(input2);

        given()
                .when()
                .get("/classe")
                .then()
                .statusCode(200)
                .body("$.size()", equalTo(2));
    }

    @Test
    void testFindById() {
        CreateClasseInput input = new CreateClasseInput("Classe C", "Description C");
        ClasseOutput created = classeService.create(input);

        Panache.getEntityManager().flush();
        Panache.getEntityManager().clear();

        given()
                .when()
                .get("/classe/" + created.getId())
                .then()
                .statusCode(200)
                .body("name", equalTo("Classe C"))
                .body("description", equalTo("Description C"));
    }

    @Test
    @TestTransaction
    void testFindByIdNotFound() {
        given()
                .when()
                .get("/classe/999")
                .then()
                .statusCode(404);
    }

    // @Test
    // @TestTransaction
    // void testUpdate() {
    // CreateClasseInput input = new CreateClasseInput("Classe D", "Description D");
    // ClasseOutput created = classeService.create(input);

    // UpdateClasse updateInput = new UpdateClasse("Classe D - Updated",
    // "Description D - Updated");

    // given()
    // .contentType(ContentType.JSON)
    // .body(updateInput)
    // .when()
    // .put("/classe/" + created.getId())
    // .then()
    // .statusCode(200)
    // .body("name", equalTo("Classe D - Updated"))
    // .body("description", equalTo("Description D - Updated"));
    // }

    // @Test
    // @TestTransaction
    // void testUpdateNotFound() {
    // UpdateClasse updateInput = new UpdateClasse("Classe E - Updated",
    // "Description E - Updated");

    // given()
    // .contentType(ContentType.JSON)
    // .body(updateInput)
    // .when()
    // .put("/classe/999")
    // .then()
    // .statusCode(404);
    // }

    // @Test
    // @TestTransaction
    // void testDeleteById() {
    // CreateClasseInput input = new CreateClasseInput("Classe E", "Description E");
    // ClasseOutput created = classeService.create(input);

    // given()
    // .when()
    // .delete("/classe/" + created.getId())
    // .then()
    // .statusCode(200);
    // // Vérifie que la classe est bien supprimée
    // given()
    // .when()
    // .get("/classe/" + created.getId())
    // .then()
    // .statusCode(404);
    // }

    // @Test
    // @TestTransaction
    // void testDeleteByIdNotFound() {
    // given()
    // .when()
    // .delete("/classe/999")
    // .then()
    // .statusCode(404);
    // }
}
