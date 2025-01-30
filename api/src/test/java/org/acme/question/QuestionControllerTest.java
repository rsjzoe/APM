package org.acme.question;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.acme.question.domain.input.CreateQuestion;
import org.acme.question.domain.input.UpdateQuestion;

@QuarkusTest
public class QuestionControllerTest {

    static CreateQuestion createQuestion = new CreateQuestion("What is Quarkus ?", "red");

    @Test
    public void testCreateQuestion() {
        given()
                .contentType(ContentType.JSON)
                .body(createQuestion)
                .when()
                .post("/question")
                .then()
                .statusCode(200)
                .body("text", equalTo(createQuestion.getText()))
                .body("borderColor", equalTo(createQuestion.getBorderColor()));
    }

    @Test
    public void testFindAllQuestions() {
        given()
                .when().get("/question")
                .then()
                .statusCode(200)
                .body("$.size()", greaterThanOrEqualTo(0));
    }

    @Test
    public void testFindById() {
        // creer un question avant de le recuperer
        Integer questionId = given()
                .contentType(ContentType.JSON)
                .body(createQuestion)
                .when()
                .post("/question")
                .then()
                .statusCode(200)
                .extract()
                .path("id");

        // recuperer le question par son id
        given()
                .when().get("/question/" + questionId)
                .then()
                .statusCode(200)
                .body("text", equalTo(createQuestion.getText()))
                .body("borderColor", equalTo(createQuestion.getBorderColor()));
    }

    @Test
    public void testUpdateQuestion() {
        // creer un question avant de le mettre a jour
        Integer questionId = given()
                .contentType(ContentType.JSON)
                .body(createQuestion)
                .when()
                .post("/question")
                .then()
                .statusCode(200)
                .extract()
                .path("id");

        // mettre a jour le question
        given()
                .contentType(ContentType.JSON)
                .body(new UpdateQuestion("question update", "blue"))
                .when()
                .put("/question/" + questionId)
                .then()
                .statusCode(200)
                .body("text", equalTo("question update"))
                .body("borderColor", equalTo("blue"));
    }

    @Test
    public void testDeleteQuestion() {
        // creer un question avant de le supprimer
        Integer questionId = given()
                .contentType(ContentType.JSON)
                .body(createQuestion)
                .when()
                .post("/question")
                .then()
                .statusCode(200)
                .extract()
                .path("id");

        // supprimer le question
        given()
                .when().delete("/question/" + questionId)
                .then()
                .statusCode(200);

        // verifier que le question n'existe plus
        given()
                .when().get("/question/" + questionId)
                .then()
                .statusCode(204);
    }

}
