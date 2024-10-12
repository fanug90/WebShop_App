package com.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.domain.Order;
import com.webshop.domain.Product;
import com.webshop.domain.ShoppingCart;
import com.webshop.service.ShoppingCartService;

@RestController
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@PostMapping("/addtocart/{id}")
	public ResponseEntity<ShoppingCart> addToCart(@PathVariable String id,@RequestParam int quantity, @RequestBody Product product) {
		ShoppingCart cart = shoppingCartService.addProductToCart(id, product, quantity);
		return new ResponseEntity<ShoppingCart>(cart, HttpStatus.CREATED);
	}

	@PostMapping("/checkout/{id}")
	public ResponseEntity<Order> checkout(@PathVariable String id, @RequestBody OrderRequest orderRequest) {
		Order order = shoppingCartService.checkoutCart(id, orderRequest.getCustomer(),
				orderRequest.getShippingAddress(), orderRequest.getBillingAddress(), orderRequest.getCreditCard(),
				orderRequest.getShippingOption());
		return new ResponseEntity<Order>(order, HttpStatus.CREATED);

	}
}
