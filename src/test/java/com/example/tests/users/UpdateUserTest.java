package com.example.tests.users;

import com.example.User;
import com.example.base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateUserTest extends BaseTest {
    static String requestBody = """
                {
                  "id": 1,
                  "username": "user1",
                  "email": "user1@gmail.com",
                  "password": "user1_password"
                }
                """;

    @Test
    public void validateStatusCode() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/users/1");

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void validateResponseBody(){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(UpdateUserTest.requestBody)
                .when()
                .put("/users/1");

        User user = response.as(User.class);
        Assert.assertEquals(user.toString(), UpdateUserTest.requestBody);
    }

    @Test
    public void validateJSONSchema(){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(UpdateUserTest.requestBody)
                .when()
                .put("/users/1");

        Assert.assertTrue(response.asPrettyString().startsWith("{"));
        Assert.assertTrue(response.asPrettyString().endsWith("}"));
    }

    @Test
    public void validateContentTypeHeader(){
        Response response = given()
                .log().uri()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(UpdateUserTest.requestBody)
                .when()
                .put("/products/1");

        Assert.assertTrue(response.getHeader("Content-Type").contains("application/json"));
    }
}