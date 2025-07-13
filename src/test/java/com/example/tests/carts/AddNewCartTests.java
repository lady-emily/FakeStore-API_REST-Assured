package com.example.tests.carts;

import com.example.Cart;
import com.example.Product;
import com.example.base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddNewCartTests extends BaseTest {
    static String requestBody = """
            {
               "id": 1,
               "userId": 1,
               "products": [
                 {
                   "id": 1,
                   "title": "T-Shirts",
                   "price": 54.90,
                   "description": "shirts for all weather",
                   "category": "tops",
                   "image": "http://example.com"
                 }
               ]
            }
            """;

    @Test
    public void validateStatusCode() {
        Response response = given()
                .body(requestBody)
                .when()
                .post("/carts");

        Assert.assertEquals(response.statusCode(), 201);
    }

    @Test
    public void validateResponseBody(){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(AddNewCartTests.requestBody)
                .when()
                .post("/carts");

        Cart cart = response.as(Cart.class);
        System.out.println("Response is "+cart.toString() );
//        Assert.assertEquals(cart.toString(), AddNewCartTests.requestBody);
    }

    @Test
    public void validateJSONSchema(){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(AddNewCartTests.requestBody)
                .when()
                .post("/carts");

        Assert.assertTrue(response.asPrettyString().startsWith("{"));
        Assert.assertTrue(response.asPrettyString().endsWith("}"));
    }

    @Test
    public void validateContentTypeHeader(){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(AddNewCartTests.requestBody)
                .when()
                .post("/carts");

        Assert.assertTrue(response.getHeader("Content-Type").contains("application/json"));
    }
}
