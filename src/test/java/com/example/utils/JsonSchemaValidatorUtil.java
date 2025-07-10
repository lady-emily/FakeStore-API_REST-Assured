package com.example.utils;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matcher;

public class JsonSchemaValidatorUtil {

    /**
     * Returns a matcher that validates JSON against the given schema file.
     * @param schemaFileName Name of the schema file in /resources/schemas/
     * @return Matcher for validation
     */
    public static Matcher<?> matchesJsonSchema(String schemaFileName) {
        return JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/" + schemaFileName);
    }
}
