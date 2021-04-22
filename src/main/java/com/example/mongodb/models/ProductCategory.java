package com.example.mongodb.models;

import org.springframework.data.annotation.Id;

public class ProductCategory {

    @Id
    private String id;
    private String categoryName;
    private String categoryImage;

    public ProductCategory() {
    }

    public ProductCategory(String id, String categoryName, String categoryImage) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }


    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", categoryImage='" + categoryImage + '\'' +
                '}';
    }
}
