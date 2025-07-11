package com.example.tests.products;

import com.example.base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetSingleProductTests extends BaseTest {
    @Test
    public void validateStatusCode(){
        Response response = given()
                .when().get("/products/1")
                .then()
                .extract().response();

        Assert.assertEquals(response.statusCode(),200);
    }

    @Test
    public void validateContentTypeHeader(){
            Response response = given()
                    .when().get("/products/1")
                    .then()
                    .extract().response();

        Assert.assertTrue(response.getHeader("Content-Type").contains("application/json"));

    }
}
