package com.example.tests.carts;

import com.example.Product;
import com.example.base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateProductTests extends BaseTest {
    static String requestBody = """
                {
                  "id": 21,
                  "title": "Ripped Jeans",
                  "price": 60.99,
                  "description": "High-quality headphones with noise cancellation",
                  "category": "electronics",
                  "image": "http://example.com/images/headphones.jpg"
                }
                """;

    @Test
    public void validateStatusCode() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/products/21");

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void validateResponseBody(){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(AddNewProductTests.requestBody)
                .when()
                .put("/products/21");

        Product product = response.as(Product.class);

        Assert.assertEquals(product.toString(), AddNewProductTests.requestBody);
    }

    @Test
    public void validateJSONSchema(){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(AddNewProductTests.requestBody)
                .when()
                .put("/products/21");

        System.out.println(response.asPrettyString());
        Assert.assertTrue(response.asPrettyString().startsWith("{"));
        Assert.assertTrue(response.asPrettyString().endsWith("}"));
    }

    @Test
    public void validateContentTypeHeader(){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(AddNewProductTests.requestBody)
                .when()
                .put("/products/21");

        Assert.assertTrue(response.getHeader("Content-Type").contains("application/json"));
    }
}
