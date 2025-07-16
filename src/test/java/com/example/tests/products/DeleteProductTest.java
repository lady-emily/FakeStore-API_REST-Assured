package com.example.tests.products;

import com.example.base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteProductTest extends BaseTest {

    @Test
    public void validateStatusCode() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/products/21");

        Assert.assertEquals(response.statusCode(), 200);
    }
}