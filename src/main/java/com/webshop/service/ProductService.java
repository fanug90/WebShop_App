package com.webshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webshop.domain.Customer;
import com.webshop.domain.Product;
import com.webshop.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	public Optional<Product> findProductById(String productId) {
		return productRepository.findById(productId);
	}

	public void deleteProduct(String productId) {
		productRepository.deleteById(productId);
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product updateProduct(String productId, Product productDetails) {
		Optional<Product> prodOptional = productRepository.findById(productId);
		if (prodOptional.isPresent()) {
			productDetails.setProductNumber(productId);
			return productRepository.save(productDetails);
		} else {
			throw new IllegalArgumentException("product with this id is not available");

		}
	}
}
