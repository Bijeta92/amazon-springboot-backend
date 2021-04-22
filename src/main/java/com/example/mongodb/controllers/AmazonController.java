package com.example.mongodb.controllers;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.mongodb.CustomizedResponse;
import com.example.mongodb.models.Customer;
import com.example.mongodb.models.Product;
import com.example.mongodb.models.ProductCategory;
import com.example.mongodb.services.CustomerService;
import com.example.mongodb.services.ProductCategoryService;
import com.example.mongodb.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AmazonController {


    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AuthenticationManager authenticationManager;



    @GetMapping("/")
    public ResponseEntity getHomePage(){
        var customizedResponse = new CustomizedResponse("Welcome to Amazon Clone Backend", null);
        return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
    }


    @GetMapping("/products")
    public ResponseEntity getProducts(){

        var products = productService.getProducts();


        if(products.size()==0){
            var customizedResponse = new CustomizedResponse("The list of products not found.", products);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        } else {
            var customizedResponse = new CustomizedResponse("A list of products found.", products);
            return new ResponseEntity(customizedResponse, HttpStatus.OK);
        }
    }


    @PostMapping(value="/products", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity addProduct(@RequestBody Product product){
        productService.insertIntoProduct(product);
        return new ResponseEntity(product, HttpStatus.OK);
    }


    @GetMapping("/products/{id}")
    public ResponseEntity getAProduct(@PathVariable ("id") String id){
        Optional<Product> product = productService.getProductById(id);

        if(product.isEmpty()){

//            System.out.println("Entered into Null products");
//            System.out.println(product);
            var customizedResponse = new CustomizedResponse("The product not found.", Collections.singletonList(product));
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        } else {
//            System.out.println("Entered into products");
//            System.out.println(product);
            var customizedResponse = new CustomizedResponse("The product found.", Collections.singletonList(product));
            return new ResponseEntity(customizedResponse, HttpStatus.OK);
        }
    }

    @GetMapping("/productCategory/{id}/products")
    public ResponseEntity getProductsByCategory(@PathVariable ("id") String id){
        System.out.println(id);
        //System.out.println(productService.getProductsByCategory(id));
        return new ResponseEntity(productService.getProductsByCategory(id), HttpStatus.OK);

    }

    @GetMapping("/bestsellers")
    public ResponseEntity getProductsByBestSeller(){

        return new ResponseEntity(productService.getProductsByBestSeller('Y'), HttpStatus.OK);

    }


    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteAProduct(@PathVariable("id") String id){
        boolean productFound = productService.deleteProduct(id);
        if(productFound == true){
            var customizedResponse = new CustomizedResponse("The product has been successfully deleted",null);
            return new ResponseEntity(customizedResponse, HttpStatus.OK);
        } else{
            var customizedResponse = new CustomizedResponse("The product was not found",null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping(value = "/products/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity editProduct(@PathVariable ("id") String id, @RequestBody Product updateProduct){
        Product updatedProduct = productService.editProduct(id,updateProduct);
        if(updatedProduct != null){
            var customizedResponse = new CustomizedResponse("The product has been successfully updated",updatedProduct);
            return new ResponseEntity(customizedResponse, HttpStatus.OK);
        } else{
            var customizedResponse = new CustomizedResponse("The product was not found",updatedProduct);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
    }



    //PRODUCT_CATEGORY

    @GetMapping("/product_category")
    public ResponseEntity getProductCategory(){
        return new ResponseEntity(productCategoryService.getProductCategory(), HttpStatus.OK);
    }


    @PostMapping(value="/product_category", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity addProductCategory(@RequestBody ProductCategory productCategory){
        productCategoryService.insertIntoProductCategory(productCategory);
        return new ResponseEntity(productCategory, HttpStatus.OK);
    }

    @GetMapping("/product_category/{id}")
    public ResponseEntity getAProductCategory(@PathVariable ("id") String id){

        return new ResponseEntity(productCategoryService.getProductCategoryById(id), HttpStatus.OK);
    }




    //CUSTOMER

    @GetMapping("/customers")
    public ResponseEntity getAllCustomers(){

        var customers = customerService.getAllCustomers()   ;
        if(customers.size() == 0){
            var customizedResponse = new CustomizedResponse<>("Users Not found", customers);
            return new ResponseEntity(customizedResponse,HttpStatus.NOT_FOUND);
        } else{
            var customizedResponse = new CustomizedResponse<>("A list of users found", customers);
            return new ResponseEntity(customizedResponse,HttpStatus.OK);
        }
//        return new ResponseEntity(customerService.getAllCustomers(),HttpStatus.OK);
    }

    @PostMapping(value = "/registration", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity addCustomer(@RequestBody Customer customer){
        var insertedCustomer = customerService.insertIntoCustomer(customer);
        System.out.println(insertedCustomer);
        if(insertedCustomer != null){
            var customizedResponse = new CustomizedResponse<>("User Successfully added to database", insertedCustomer);
            return new ResponseEntity(customizedResponse, HttpStatus.OK);
        }else{
            var customizedResponse = new CustomizedResponse("Database throws some error", insertedCustomer);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity deleteACustomer(@PathVariable("id") String id){
        boolean customerFound = customerService.deleteCustomer(id);
        if(customerFound == true){
            var customizedResponse = new CustomizedResponse("The customer has been successfully deleted",null);
            return new ResponseEntity(customizedResponse, HttpStatus.OK);
        } else{
            var customizedResponse = new CustomizedResponse("The customer was not found",null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping(value="/login", consumes={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity login (@RequestBody Customer customer){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customer.getEmail(), customer.getPassword()));
            var response = new CustomizedResponse<>("You logged in successfully.", null );
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (BadCredentialsException ex){
            var response = new CustomizedResponse<>("Your email and/or password were entered incorrectly", null );
            return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
        }
    }


    @GetMapping("/customers/{id}")
    public ResponseEntity getACustomer(@PathVariable ("id") String id){
        Optional<Customer> customer = customerService.getCustomerById(id);

        if(customer.isEmpty()){

//            System.out.println("Entered into Null products");
//            System.out.println(product);
            var customizedResponse = new CustomizedResponse("The customer not found.", Collections.singletonList(customer));
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        } else {
//            System.out.println("Entered into products");
//            System.out.println(product);
            var customizedResponse = new CustomizedResponse("The customer found.", Collections.singletonList(customer));
            return new ResponseEntity(customizedResponse, HttpStatus.OK);
        }
    }

}
