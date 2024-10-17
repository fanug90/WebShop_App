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
import com.webshop.repository.CustomerRepository;
import com.webshop.repository.OrderRepository;
import com.webshop.repository.ProductRepository;
import com.webshop.repository.ShoppingCartRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	ShoppingCartRepository shoppingCartRepository;

	@Autowired
	private EmailSender emailSender;

	// assuming the customer is login and want to change the attributes that was
	// already set in customer attribute
	public Order createOrder(ShoppingCart cart, Address shippingAddress, Address billingAddress, CreditCard creditCard,
			ShippingOption shippingOption) {

		if (cart.getCartLines().isEmpty()) {
			throw new RuntimeException("Cannot create order from an empty cart");
		}

		Optional<Customer> customerOpt = customerRepository.findById(cart.getCustomerNumber());
		if (customerOpt.isEmpty()) {
			throw new RuntimeException("you are not login,in order to placeorder you need to signin frist");
		}
		Customer customer = customerOpt.get();

		List<OrderLine> orderLines = new ArrayList<>();
		for (CartLine cartLine : cart.getCartLines()) {
			orderLines.add(new OrderLine(cartLine.getProduct(), cartLine.getQuantity()));
		}

		Order order = new Order(customer.getCustomerNumber(), shippingAddress, billingAddress, creditCard,orderLines,
				shippingOption);
		customer.getOrders().add(order);

		Order savedOrder = orderRepository.save(order);
		emailSender.sendOrderConfirmation("  Thank you,your order is successful  ", customer.getEmail());

		return savedOrder;
	}

	// create an order based on the attributes that is set in the customer
	// class,means assuming
	// the customer is login and using default settings
	public Order createOrderFromCart(ShoppingCart cart) {
		Order order = new Order();
		Optional<Customer> customerOptional = customerRepository.findById(cart.getCustomerNumber());
		if (customerOptional.isEmpty()) {
			throw new RuntimeException("you are not signin, you need to sign in frist");
		}
		Customer customer = customerOptional.get();
		order.setCustomerNumber(customer.getCustomerNumber());
		// order.setCustomer(cart.getCustomer());

		// converting cartline to orderline
		if (cart.getCartLines().isEmpty()) {
			throw new RuntimeException("Cannot create order from an empty cart");
		}

		List<OrderLine> orderLines = new ArrayList<>();
		for (CartLine cartLine : cart.getCartLines()) {
			orderLines.add(new OrderLine(cartLine.getProduct(), cartLine.getQuantity()));
		}
		order.setOrderLines(orderLines);
		order.setBillingAddress(customer.getBillingAddress());
		order.setShippingAddress(customer.getShippingAddress());
		order.setCreditCard(customer.getListOfCreditCards().get(0));
		order.setShippingOption(customer.getShippingOption());
		// order.setOrderLines(new ArrayList<>(cart.getCartLines()));
//		customer.addOrder(order);
		customer.getOrders().add(order);
		Order savedOrder = orderRepository.save(order);
		emailSender.sendOrderConfirmation("  Thank you,your order is successful  ", customerOptional.get().getEmail());
		return savedOrder;

	}

	public Order getOrder(String customerNumber, String orderNumber) {

		Optional<Customer> customerOpt = customerRepository.findById(customerNumber);
		if (customerOpt.isEmpty()) {
			throw new IllegalArgumentException("Cannot not get customer with this id");
		}
		Customer customer = customerOpt.get();
		for (Order order : customer.getOrders()) {
			if (order.getOrderNumber().equals(orderNumber)) {
				return order;
			}
		}
		throw new IllegalArgumentException("Cannot not get order with this id");

	}

	public List<Order> getAllOrders(String customerNumber) {

		Optional<Customer> customerOpt = customerRepository.findById(customerNumber);
		if (customerOpt.isEmpty()) {
			throw new IllegalArgumentException("Cannot not get customer with this id");
		}
		Customer customer = customerOpt.get();
		return customer.getOrders();
	}

}
