package com.example;

import java.util.List;

public class CartRequest {
    private int id;
    private int userId;
    private List<Object> products;

    public CartRequest() {
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

    public List<Object> getProducts() {
        return products;
    }

    public void setProducts(List<Object> products) {
        this.products = products;
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

