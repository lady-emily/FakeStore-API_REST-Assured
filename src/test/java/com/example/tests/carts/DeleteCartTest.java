package com.example.tests.carts;

import com.example.base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteCartTest extends BaseTest {

    @Test
    public void validateStatusCode() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/carts/1");

        Assert.assertEquals(response.statusCode(), 200);
    }
}