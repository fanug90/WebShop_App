package com.webshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webshop.domain.Address;
import com.webshop.domain.CartLine;
import com.webshop.domain.CreditCard;
import com.webshop.domain.Customer;
import com.webshop.domain.Order;
import com.webshop.domain.OrderLine;
import com.webshop.domain.ShippingOption;
import com.webshop.domain.ShoppingCart;
import com.webshop.integration.EmailSender;
import com.webshop.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private EmailSender emailSender;

	public Order createOrder(ShoppingCart cart, Customer customer, Address shippingAddress, Address billingAddress,
			CreditCard creditCard, ShippingOption shippingOption) {

		if (cart.getCartLines().isEmpty()) {
			throw new RuntimeException("Cannot create order from an empty cart");
		}

		List<OrderLine> orderLines = new ArrayList<>();
		for (CartLine cartLine : cart.getCartLines()) {
			orderLines.add(new OrderLine(cartLine.getProduct(), cartLine.getQuantity()));
		}

		Order order = new Order(customer, shippingAddress, billingAddress, creditCard, orderLines, shippingOption);

		Order savedOrder = orderRepository.save(order);
		emailSender.sendOrderConfirmation( "  Thank you,your order is successful  ",customer.getEmail());

		return savedOrder;
	}

}
