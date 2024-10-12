package com.webshop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.webshop.domain.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

}
