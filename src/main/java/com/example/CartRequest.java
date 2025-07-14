package com.example;

import java.util.List;

public class CartRequest {
    private int id;
    private int userId;
    private List<Product> products;

    public CartRequest(int userId, List<Product> products) {
        this.userId = userId;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products =products;
    }

    @Override
    public String toString() {

        return String.format( """
        {
        "id": %d,
        "userId": %d,
        "products": "%s"
        }
        """, getId(), getUserId(), getProducts());
    }
}