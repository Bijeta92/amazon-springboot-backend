package com.example.mongodb.models;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CustomerRepo extends MongoRepository<Customer, String> {

    public Customer findCustomerByEmail(String email);
}
