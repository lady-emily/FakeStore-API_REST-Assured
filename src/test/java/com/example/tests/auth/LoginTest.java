package com.example.tests.login;

import com.example.base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginTest extends BaseTest {
    private static final String requestBody = """
            {
              "username": "johnd",
              "password": "m38rmF$"
            }
            """;

    @Test
    public void testLogin() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/auth/login")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.getBody().asPrettyString().contains("token"));
    }
}