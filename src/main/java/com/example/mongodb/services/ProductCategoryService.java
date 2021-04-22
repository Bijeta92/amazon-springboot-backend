package com.example.mongodb.services;


import com.example.mongodb.models.ProductCategory;
import com.example.mongodb.models.ProductCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepo prodCatRepo;
    public List<ProductCategory> getProductCategory(){
        return prodCatRepo.findAll();
    }

    public Optional<ProductCategory> getProductCategoryById(String id){
        return prodCatRepo.findById(id);
    }

    public void insertIntoProductCategory(ProductCategory productCategory){
        prodCatRepo.insert(productCategory);
    }

    public void deleteProduct(String id){
        prodCatRepo.deleteById(id);
    }


}
