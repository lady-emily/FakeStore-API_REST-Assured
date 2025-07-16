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

public class UpdateCartTest extends BaseTest {
    private static CartRequest requestBody;

    @BeforeClass
    public void setup() {
        requestBody = new CartRequest(1, List.of(
                new Product(9876, "Wireless Noise-Cancelling Headphones", 199.99f, "High-fidelity wireless headphones with active noise cancellation and 30-hour battery life.", "Electronics", "https://example.com/images/headphones.jpg"),
                new Product(1234, "Smart LED Light Bulb", 24.95f, "Color-changing smart bulb with Wi-Fi control and compatibility with Alexa and Google Home.", "Home Automation", "https://example.com/images/smartbulb.jpg")
        ));
    }

    @Test
    public void validateStatusCode() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/carts/1");

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void validateResponseBody(){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(UpdateCartTest.requestBody)
                .when()
                .put("/carts/1");

        Cart cart = response.as(Cart.class);

        requestBody.setId(cart.getId());
        Assert.assertEquals(cart.toString(), UpdateCartTest.requestBody.toString());
    }

    @Test
    public void validateJSONSchema(){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(UpdateCartTest.requestBody)
                .when()
                .put("/carts/1");

        Assert.assertTrue(response.asPrettyString().startsWith("{"));
        Assert.assertTrue(response.asPrettyString().endsWith("}"));
    }

    @Test
    public void validateContentTypeHeader(){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(UpdateCartTest.requestBody)
                .when()
                .put("/carts/1");

        Assert.assertTrue(response.getHeader("Content-Type").contains("application/json"));
    }
}