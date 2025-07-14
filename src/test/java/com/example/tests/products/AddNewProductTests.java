package com.example.tests.products;

import com.example.Product;
import com.example.base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddNewProductTests extends BaseTest {
    static String requestBody = """
                {
                  "title": "Wireless Headphones",
                  "price": 60.99,
                  "description": "High-quality headphones with noise cancellation",
                  "category": "electronics",
                  "image": "http://example.com/images/headphones.jpg"
                }
                """;
    @Test
    public void validateStatusCode() {
        Response response = given()
                .body(requestBody)
                .when()
                .post("/products");

        Assert.assertNotEquals(response.statusCode(), 201);
    }

    @Test
    public void validateResponseBody(){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(AddNewProductTests.requestBody)
                .when()
                .post("/products");

        Product product = response.as(Product.class);

        Assert.assertEquals(product.toString(), AddNewProductTests.requestBody);
    }

    @Test
    public void validateJSONSchema(){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(AddNewProductTests.requestBody)
                .when()
                .post("/products");

        Assert.assertTrue(response.asPrettyString().startsWith("{"));
        Assert.assertTrue(response.asPrettyString().endsWith("}"));
    }

    @Test
    public void validateContentTypeHeader(){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(AddNewProductTests.requestBody)
                .when()
                .post("/products");

        Assert.assertTrue(response.getHeader("Content-Type").contains("application/json"));
    }
}