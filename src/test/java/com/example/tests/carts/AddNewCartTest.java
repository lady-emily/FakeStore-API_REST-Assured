package com.example.tests.carts;

import com.example.Cart;
import com.example.CartRequest;
import com.example.Product;
import com.example.base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class AddNewCartTest extends BaseTest {
    private static CartRequest requestBody;

    @BeforeClass
    public void setup() {
        Product product = new Product(1, "T-Shirts", 54.90f, "shirts for all weather", "tops", "http://example.com");
        requestBody = new CartRequest(1, List.of(product));
    }

    @Test
    public void validateStatusCode() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/carts");

        Assert.assertEquals(response.statusCode(), 201, "response status code is wrong");
    }

    @Test
    public void validateResponseBody(){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(AddNewCartTest.requestBody)
                .when()
                .post("/carts");

        Cart cart = response.as(Cart.class);
        requestBody.setId(cart.getId());

        Assert.assertEquals(requestBody.toString(), cart.toString());
    }

    @Test
    public void validateJSONSchema(){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(AddNewCartTest.requestBody)
                .when()
                .post("/carts");

        Assert.assertTrue(response.asPrettyString().startsWith("{"));
        Assert.assertTrue(response.asPrettyString().endsWith("}"));
    }

    @Test
    public void validateContentTypeHeader(){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(AddNewCartTest.requestBody)
                .when()
                .post("/carts");

        Assert.assertTrue(response.getHeader("Content-Type").contains("application/json"));
    }
}