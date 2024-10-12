package com.webshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webshop.domain.Customer;
import com.webshop.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public Optional<Customer> findCustomerById(String customerId) {
		return customerRepository.findById(customerId);
	}

	public void deleteCustomer(String customerId) {
		customerRepository.deleteById(customerId);
	}

	public Customer updateCustomer(String customerId, Customer customerDetails) {
		Optional<Customer> customerOp = customerRepository.findById(customerId);
		if (customerOp.isPresent()) {
			customerDetails.setCustomerNumber(customerId);
			return customerRepository.save(customerDetails);
		} else {
			throw new IllegalArgumentException("customer with this id is not available");

		}
	}

}
