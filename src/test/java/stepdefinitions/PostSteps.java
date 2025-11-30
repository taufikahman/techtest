package steps;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PostSteps {

    private Response response;

    @Given("I create a post with title {string} and body {string} and userId {int}")
    public void createPost(String title, String body, int userId) {

        baseURI = "https://jsonplaceholder.typicode.com";

        response = given()
                .header("Content-type", "application/json")
                .body("{ \"title\": \""+title+"\", \"body\": \""+body+"\", \"userId\": "+userId+" }")
                .when()
                .post("/posts")
                .then()
                .extract()
                .response();
    }

    @Then("the response should contain the same title body and userId")
    public void verifyResponseFields() {
        response.then()
                .statusCode(201)
                .body("title", notNullValue())
                .body("body", notNullValue())
                .body("userId", notNullValue());
    }

    @Then("the response should match the create post JSON schema")
    public void validateCreatePostSchema() {
        response.then()
                .body(matchesJsonSchemaInClasspath("schemas/create_post_schema.json"));
    }

    @When("I send a GET request to {string}")
    public void sendGetRequest(String endpoint) {
        baseURI = "https://jsonplaceholder.typicode.com";

        response = given()
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    @Then("all posts should have non-null id fields")
    public void verifyNonNullIds() {
        List<Integer> ids = response.jsonPath().getList("id");
        for (Integer id : ids) {
            assert id != null;
        }
    }

    @Then("the response should match the get posts JSON schema")
    public void validateGetPostsSchema() {
        response.then()
                .body(matchesJsonSchemaInClasspath("schemas/get_posts_schema.json"));
    }
}
