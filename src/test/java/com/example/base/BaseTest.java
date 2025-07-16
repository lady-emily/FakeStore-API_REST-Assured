package com.example.base;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected RequestSpecification requestSpec;

    @BeforeClass
    public void setUp() {
        // Set base URI for all tests
        RestAssured.baseURI = "https://fakestoreapi.com";

        // Reusable request specification
        requestSpec = given()
                .log().ifValidationFails(LogDetail.ALL)
                .header("Accept", "application/json")
                .contentType("application/json");
    }
}