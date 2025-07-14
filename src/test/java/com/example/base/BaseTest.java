package com.example.base;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

public class BaseTest {

    @BeforeClass
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "https://fakestoreapi.com"; // fallback if not set
        }
        RestAssured.baseURI = baseUrl;
        System.out.println("Base URI set to: " + RestAssured.baseURI);
    }
}
