package com.example;

public class Product {
    private int id;
    private String title;
    private float price;
    private String description;
    private String category;
    private String image;

    public Product() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("""
                             {
                               "title": "%s",
                               "price": %.2f,
                               "description": "%s",
                               "category": "%s",
                               "image": "%s"
                             }
                             """,  this.getTitle(), this.getPrice(), this.getDescription(), this.getCategory(), this.getImage());
    }
}
