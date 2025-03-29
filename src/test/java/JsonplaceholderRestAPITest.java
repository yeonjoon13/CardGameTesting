import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class JsonplaceholderRestAPITest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void testAlbumTitleExists() {
        given()
                .when()
                .get("/albums")
                .then()
                .statusCode(200)
                .body("title", hasItem("omnis laborum odio"));
    }

    @Test
    public void testCommentsCountGreaterThan200() {
        Response response = given()
                .when()
                .get("/comments");

        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON);

        int commentCount = response.jsonPath().getList("").size();
        assertTrue(commentCount >= 200, "Expected at least 200 comments");
    }

    @Test
    public void testUserWithSpecificDetails() {
        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("findAll { it.name == 'Ervin Howell' }", hasSize(1))
                .body("findAll { it.name == 'Ervin Howell' }[0].username", equalTo("Antonette"))
                .body("findAll { it.name == 'Ervin Howell' }[0].address.zipcode", equalTo("90566-7771"));
    }

    @Test
    public void testCommentsWithBizEmail() {
        given()
                .when()
                .get("/comments")
                .then()
                .statusCode(200)
                .body("email", hasItem(matchesPattern(".*\\.biz$")));
    }


    @Test
    void testCreateNewPost() {
        // Prepare request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", 11);
        requestBody.put("id", 101);
        requestBody.put("title", "sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        requestBody.put("body", "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");

        // Perform POST request
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .extract()
                .response();

        // Assertions
        assertEquals(201, response.getStatusCode()); // Verify status code is 201 (Created)

        //You might not be able to assert specific values since the API might alter them.
        //But you can verify the response body contains the keys you sent in the request.

        assertNotNull(response.jsonPath().get("id"));  //The API typically auto-generates the ID

        //Optionally, you can print the response for inspection:
        System.out.println("Response Body: " + response.getBody().asString());
    }
}