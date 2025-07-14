package com.example.tests.users;

import com.example.Product;
import com.example.User;
import com.example.base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddNewUserTest extends BaseTest {
    static String requestBody = """
            {
                "username": "user1",
                "email": "user1@gmail.com",
                "password": "user1_password"
            }
            """;
    @Test
    public void validateStatusCode() {
        Response response = given()
                .body(requestBody)
                .when()
                .post("/users");

        Assert.assertEquals(response.statusCode(), 201);
    }

    @Test
    public void validateResponseBody(){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(AddNewUserTest.requestBody)
                .when()
                .post("/users")
                .then()
                .extract().response();

        User user = response.as(User.class);
        Assert.assertNotEquals(user.getId(), 0);
    }

    @Test
    public void validateJSONSchema(){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(AddNewUserTest.requestBody)
                .when()
                .post("/users");

        Assert.assertTrue(response.asPrettyString().startsWith("{"));
        Assert.assertTrue(response.asPrettyString().endsWith("}"));
    }

    @Test
    public void validateContentTypeHeader(){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(AddNewUserTest.requestBody)
                .when()
                .post("/users");

        Assert.assertTrue(response.getHeader("Content-Type").contains("application/json"));
    }
}