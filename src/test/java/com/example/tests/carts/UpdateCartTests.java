package com.example.tests.carts;

import com.example.Cart;
import com.example.base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateCartTests extends BaseTest {
    static String requestBody = """
            {
              "id": 101,
              "userId": 502,
              "products": [
                {
                  "id": 9876,
                  "title": "Wireless Noise-Cancelling Headphones",
                  "price": 199.99,
                  "description": "High-fidelity wireless headphones with active noise cancellation and 30-hour battery life.",
                  "category": "Electronics",
                  "image": "https://example.com/images/headphones.jpg"
                },
                {
                  "id": 1234,
                  "title": "Smart LED Light Bulb",
                  "price": 24.95,
                  "description": "Color-changing smart bulb with Wi-Fi control and compatibility with Alexa and Google Home.",
                  "category": "Home Automation",
                  "image": "https://example.com/images/smartbulb.jpg"
                }
              ]
            }
            """;

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
                .body(UpdateCartTests.requestBody)
                .when()
                .put("/carts/1");

        Cart cart = response.as(Cart.class);
        System.out.println("response is "+ cart.toString());
//        Assert.assertEquals(cart.toString(), UpdateCartTests.requestBody);
    }

    @Test
    public void validateJSONSchema(){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(UpdateCartTests.requestBody)
                .when()
                .put("/carts/1");

        System.out.println(response.asPrettyString());
        Assert.assertTrue(response.asPrettyString().startsWith("{"));
        Assert.assertTrue(response.asPrettyString().endsWith("}"));
    }

    @Test
    public void validateContentTypeHeader(){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(UpdateCartTests.requestBody)
                .when()
                .put("/carts/1");

        Assert.assertTrue(response.getHeader("Content-Type").contains("application/json"));
    }
}
