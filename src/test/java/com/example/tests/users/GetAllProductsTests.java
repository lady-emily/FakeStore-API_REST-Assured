package com.example.tests.users;

import com.example.base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class GetAllProductsTests extends BaseTest {

    @Test
    public void validateStatusCode() {
        Response response = given()
                .when().get("/products")
                .then()
                .extract().response();

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void validateContentTypeHeader() {
        Response response = given()
                .when().get("/products")
                .then()
                .extract().response();

        Assert.assertTrue(response.getHeader("Content-Type").contains("application/json"));
    }

    @Test
    public void validateJSONSchema(){
        Response response = given()
                .when().get("/products")
                .then()
                .extract().response();

        Object responseBody = response.jsonPath().get();
        Assert.assertNotNull(responseBody, "****** Response body must not be  null");
        Assert.assertTrue(responseBody instanceof ArrayList, "****** Response body must be an array");
    }
}
