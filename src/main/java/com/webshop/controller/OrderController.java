package com.webshop.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.domain.Order;
import com.webshop.repository.OrderRepository;
import com.webshop.service.OrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("orders")
public class OrderController{
	
	@Autowired
	private OrderService orderService;
  
	@GetMapping("/{customerNumber}/{orderNumber}")
	public ResponseEntity<Order> getOrder(@PathVariable String customerNumber,  @PathVariable String orderNumber) {
		Order order = orderService.getOrder(customerNumber,orderNumber);
		return new ResponseEntity<Order>(order,HttpStatus.OK);
	}
	
	@GetMapping("/{customerNumber}")
	public ResponseEntity<List<Order>> getOrder(@PathVariable String customerNumber) {
		List<Order> orders = orderService.getAllOrders(customerNumber);
		return new ResponseEntity<List<Order>>(orders,HttpStatus.OK);
	}
	
}
