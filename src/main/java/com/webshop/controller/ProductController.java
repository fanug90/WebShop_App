package com.webshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.webshop.domain.Product;
import com.webshop.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.addProduct(product);
		return new ResponseEntity<Product>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("/{productNumber}")
    public ResponseEntity<Product> getProductById(@PathVariable String productNumber) {
        Optional<Product> productOp = productService.findProductById(productNumber);
        if (!productOp.isPresent()) {
			throw new IllegalArgumentException("product with this id is not available");
		}
		Product product = productOp.get();
		return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PutMapping("/{productNumber}")
    public ResponseEntity<Product> updateProduct(@PathVariable String productNumber, @RequestBody Product productDetails) {
        Product updatedProduct = productService.updateProduct(productNumber, productDetails);
		return new ResponseEntity<Product>(updatedProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productNumber}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String productNumber) {
    	productService.deleteProduct(productNumber);
		return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }
}
