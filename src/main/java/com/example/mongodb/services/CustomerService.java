package com.example.mongodb.services;


import com.example.mongodb.models.Customer;
import com.example.mongodb.models.CustomerRepo;
import com.example.mongodb.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements UserDetailsService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public Customer insertIntoCustomer(Customer customer) {
        String encodedPassword = bCryptPasswordEncoder.encode(customer.getPassword());
        String encodedConfirmedPassword = bCryptPasswordEncoder.encode(customer.getConfirmPassword());
        customer.setPassword(encodedPassword);
        customer.setConfirmPassword(encodedConfirmedPassword);

        Customer insertedUser = null;
        if(!customer.getFullName().isBlank() && !customer.getEmail().isBlank() && !customer.getPassword().isBlank() && !customer.getConfirmPassword().isBlank()){
                insertedUser = customerRepo.insert(customer);
        }
        return insertedUser;

    }

    public List<Customer> getAllCustomers(){
        return customerRepo.findAll();
    }

    public Optional<Customer> getCustomerById(String id){
        return customerRepo.findById(id);
    }

    public boolean deleteCustomer(String id){
        var flag = false;
        if(customerRepo.existsById(id)){
            customerRepo.deleteById(id);
            flag = true;
        }
        return flag;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer foundUser = customerRepo.findCustomerByEmail(email);
        String userEmail = foundUser.getEmail();
        String userPassword = foundUser.getPassword();
        return new User(userEmail, userPassword, new ArrayList<>());
    }
}
