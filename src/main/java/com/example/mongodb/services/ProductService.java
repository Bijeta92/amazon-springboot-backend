package com.example.mongodb.services;


import com.example.mongodb.models.Product;
import com.example.mongodb.models.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo prodRepo;


    public List<Product> getProducts(){
        return prodRepo.findAll();
    }

    public boolean insertIntoProduct(Product product){
        if(product.getProductName().isBlank() || product.getProductURL().isBlank() || product.getBrand().isBlank() || product.getDescription().isBlank() || product.getCategoryId().isBlank() || product.getBestSeller() == ' ') {
            return false;
        } else {
            prodRepo.insert(product);
            return true;
        }

    }


    public Optional<Product> getProductById(String id){
        return prodRepo.findById(id);
    }

    public List<Product> getProductsByCategory(String categoryId){
       return prodRepo.findProductByCategoryId(categoryId);

    }

    public Product editProduct(String id, Product updateProduct){

        if(prodRepo.existsById(id)){
            Optional<Product> product = prodRepo.findById(id);
            product.get().setProductName(updateProduct.getProductName());
            product.get().setProductURL(updateProduct.getProductURL());
            product.get().setBestSeller(updateProduct.getBestSeller());
            product.get().setBrand(updateProduct.getBrand());
            product.get().setPrice(updateProduct.getPrice());
            product.get().setCategoryId(updateProduct.getCategoryId());
            product.get().setDescription(updateProduct.getDescription());
            product.get().setRating(updateProduct.getRating());
            prodRepo.save(product.get());
            return product.get();
        }
        return null;

    }



    public List<Product> getProductsByBestSeller(char bestSeller){
        return prodRepo.findProductByBestSeller(bestSeller);

    }

    public boolean deleteProduct(String id){
        var flag = false;
        if(prodRepo.existsById(id)){
            prodRepo.deleteById(id);
            flag = true;
        }
        return flag;

    }
}
