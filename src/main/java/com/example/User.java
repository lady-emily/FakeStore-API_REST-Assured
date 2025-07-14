package com.example;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;

public class User {
    private int id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String username;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String email;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String password;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("""
                {
                  "id": %d,
                  "username": "%s",
                  "email": "%s",
                  "password": "%s"
                }
                """, getId(), getUsername(), getEmail(), getPassword());
    }
}