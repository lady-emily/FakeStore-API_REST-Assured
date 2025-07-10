package com.example.tests;

import com.example.base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class ProductTests extends BaseTest {

    //Get all products
    @Test
    public void getAllProducts(){
        Response response = given()
                .when().get("/products")
                .then()
                .extract().response();

        // validate response status code is 200
        Assert.assertEquals(response.statusCode(), 200);

        // validate Content-Type header is application/json
        Assert.assertTrue(response.getHeader("Content-Type").contains("application/json"));

        //validate Schema
        Object responseBody = response.jsonPath().get();
        Assert.assertNotNull(responseBody, "****** Response body must not be  null");
        Assert.assertTrue(responseBody instanceof ArrayList, "****** Response body must be an array");
    }

    @Test
    public void addNewProduct(){
        String requestBody = """
                {
                  "title": "Wireless Headphones",
                  "price": 59.99,
                  "description": "High-quality wireless headphones with noise cancellation",
                  "category": "electronics",
                  "image": "http://example.com/images/headphones.jpg"
                }
               
                """;

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/products");

        System.out.println("Response is " + response.asPrettyString());

        System.out.println("Status code is " + response.statusCode());
        JSONObject.newHashMap(6)
//        Assert.assertEquals(response.statusCode(), 201);
//        response.body().
    }
}
