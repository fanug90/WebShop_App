package com.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.domain.Order;
import com.webshop.domain.Product;
import com.webshop.domain.ShoppingCart;
import com.webshop.repository.ProductRepository;
import com.webshop.service.ShoppingCartService;

@RestController
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@PostMapping("/{customerNumber}/{productNumber}")
	public ResponseEntity<ShoppingCart> addToCart(@PathVariable String customerNumber,
			@PathVariable String productNumber) {

		ShoppingCart cart = shoppingCartService.addToCart(customerNumber, productNumber);
		return new ResponseEntity<ShoppingCart>(cart, HttpStatus.CREATED);
	}

	@DeleteMapping("/{customerNumber}/{productNumber}")
	public ResponseEntity<Void> removeFromCart(@PathVariable String customerNumber, @PathVariable String productNumber) {

		shoppingCartService.removeFromCart(customerNumber, productNumber);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// Change the quantity of a product in the shopping cart
	@PostMapping("/changeQuantityOfProduct/{customerNumber}/{productNumber}")
	public ResponseEntity<ShoppingCart> changeProductQuantity(@PathVariable String customerNumber,
			@PathVariable String productNumber, @RequestParam int quantity) {

		ShoppingCart cart = shoppingCartService.changeQuantity(customerNumber, productNumber, quantity);
		return new ResponseEntity<ShoppingCart>(cart, HttpStatus.CREATED);
	}

	// Checkout the shopping cart
	@PostMapping("/placeAnOrder/{id}")
	public ResponseEntity<Order> checkout(@PathVariable String id) {
		// ShoppingCart cart = shoppingCartService.findOrCreateCart(id);
		Order order = shoppingCartService.checkout(id);
		return new ResponseEntity<Order>(order, HttpStatus.CREATED);
	}

	@PostMapping("/placeAnOrderWithNewInfo/{id}")
	public ResponseEntity<Order> checkout(@PathVariable String id, @RequestBody OrderRequest orderRequest) {
		Order order = shoppingCartService.checkoutCart(id, orderRequest.getShippingAddress(),
				orderRequest.getBillingAddress(), orderRequest.getCreditCard(), orderRequest.getShippingOption());
		return new ResponseEntity<Order>(order, HttpStatus.CREATED);

	}

	// methods that defined below helps as an option if needed, when you want
	// to purchase product with out login to the web Shop App.

//	@PostMapping("/{id}")
//	public ResponseEntity<ShoppingCart> addToCart(@PathVariable String id, @RequestBody Product product) {
//		ShoppingCart cart = shoppingCartService.addProductToCart(id, product);
//		return new ResponseEntity<ShoppingCart>(cart, HttpStatus.CREATED);
//	}
//	@PatchMapping("/{id}")
//	public ResponseEntity<ShoppingCart> addToCart(@PathVariable String id,@RequestParam int quantity, @RequestBody Product product) {
//		ShoppingCart cart = shoppingCartService.modifyQuantity(id, quantity, product);
//		return new ResponseEntity<ShoppingCart>(cart, HttpStatus.CREATED);
//	}
//
//	@PostMapping("/checkout/{id}")
//	public ResponseEntity<Order> checkout(@PathVariable String id, @RequestBody OrderRequest orderRequest) {
//		Order order = shoppingCartService.checkoutCart(id, orderRequest.getCustomer(),
//				orderRequest.getShippingAddress(), orderRequest.getBillingAddress(), orderRequest.getCreditCard(),
//				orderRequest.getShippingOption());
//		return new ResponseEntity<Order>(order, HttpStatus.CREATED);
//
//	}
}
