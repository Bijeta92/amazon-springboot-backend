package com.example.mongodb.models;


import org.springframework.data.annotation.Id;

public class Product {

    @Id
    private String id;
    private String categoryId;
    private String productURL;
    private String productName;
    private String brand;
    private String price;
    private char bestSeller;
    private String description;
    private String rating;


    public Product() {
    }

    public Product(String categoryId, String id, String productURL, String productName, String brand, String price, char bestSeller, String description, String rating) {
        this.categoryId = categoryId;
        this.id = id;
        this.productURL = productURL;
        this.productName = productName;
        this.brand = brand;
        this.price = price;
        this.bestSeller = bestSeller;
        this.description = description;
        this.rating = rating;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductURL() {
        return productURL;
    }

    public void setProductURL(String productURL) {
        this.productURL = productURL;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public char getBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(char bestSeller) {
        this.bestSeller = bestSeller;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Product{" +
                "categoryId=" + categoryId +
                ", id=" + id +
                ", productURL='" + productURL + '\'' +
                ", productName='" + productName + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", bestSeller=" + bestSeller +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                '}';
    }
}
