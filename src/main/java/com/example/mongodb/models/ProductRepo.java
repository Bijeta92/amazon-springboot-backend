package com.example.mongodb.models;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends MongoRepository <Product, String> {

    public List<Product> findProductByCategoryId(String categoryId);
    public List<Product> findProductByBestSeller(char bestSeller);
}
