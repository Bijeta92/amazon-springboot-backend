package com.example.mongodb.models;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepo extends MongoRepository<ProductCategory, String> {



}
