package com.webshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.domain.Customer;
import com.webshop.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		Customer createdCustomer = customerService.createCustomer(customer);
		return new ResponseEntity<Customer>(createdCustomer, HttpStatus.CREATED);
	}

	@GetMapping("/{customerNumber}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable String customerNumber) {
		Optional<Customer> customer = customerService.findCustomerById(customerNumber);
		if (!customer.isPresent()) {
			throw new IllegalArgumentException("customer with this id is not available");
		}
		Customer customer2 = customer.get();
		return new ResponseEntity<Customer>(customer2, HttpStatus.OK);
	}

	@PutMapping("/{customerNumber}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable String customerNumber,
			@RequestBody Customer customerDetails) {
		Customer updatedCustomer = customerService.updateCustomer(customerNumber, customerDetails);
		return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.OK);
	}

	@DeleteMapping("/{customerNumber}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable String customerId) {
		customerService.deleteCustomer(customerId);
		return new ResponseEntity<>( HttpStatus.NO_CONTENT);
	}

}
