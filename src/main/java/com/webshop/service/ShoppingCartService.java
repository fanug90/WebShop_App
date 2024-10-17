package com.webshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webshop.domain.Address;
import com.webshop.domain.CreditCard;
import com.webshop.domain.Customer;
import com.webshop.domain.Order;
import com.webshop.domain.Product;
import com.webshop.domain.ShippingOption;
import com.webshop.domain.ShoppingCart;
import com.webshop.repository.ProductRepository;
import com.webshop.repository.ShoppingCartRepository;

@Service
public class ShoppingCartService {

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductRepository productRepository;

	public ShoppingCart findOrCreateCart(String customerNumber) {
		Optional<ShoppingCart> existingCart = shoppingCartRepository.findByCustomerNumber(customerNumber);

		if (existingCart.isPresent()) {
			return existingCart.get();
		} else {
			ShoppingCart newCart = new ShoppingCart();
			newCart.setCustomerNumber(customerNumber);
			return shoppingCartRepository.save(newCart);
		}
	}

	// Add a product to the customer's cart
	public ShoppingCart addToCart(String customerNumber, String productId) {
		ShoppingCart cart = findOrCreateCart(customerNumber);
		Optional<Product> productOptional = productRepository.findById(productId);
		if (productOptional.isEmpty()) {
			throw new RuntimeException("Product not found");
		}
		Product product = productOptional.get();
		cart.addToCart(product);
		return shoppingCartRepository.save(cart);
	}

	// Remove a product from the customer's cart
	public ShoppingCart removeFromCart(String customerNumber, String productId) {
		ShoppingCart cart = findOrCreateCart(customerNumber);
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));

		cart.removeFromCart(product);
		return shoppingCartRepository.save(cart);
	}

	// Change quantity of the product that is already in the cart
	public ShoppingCart changeQuantity(String customerNumber, String productId, Integer quantity) {
		ShoppingCart cart = findOrCreateCart(customerNumber);
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));

		cart.changeQuantityOfProduct(product, quantity);
		return shoppingCartRepository.save(cart);
	}

	// Checkout by passing only the ShoppingCart
	public Order checkout(String cartId) {
		Optional<ShoppingCart> cart = shoppingCartRepository.findById(cartId);
		if (!cart.isPresent()) {
			throw new IllegalArgumentException(
					"the cart with this id is not available,check if you have already created a cart");
		}
		ShoppingCart shoppingCart = cart.get();
		if (shoppingCart.getCartLines().isEmpty()) {
			throw new RuntimeException("Cannot checkout with an empty cart.");
		}

		Order order = orderService.createOrderFromCart(shoppingCart); // Pass cart to OrderService for order creation
		//shoppingCart.clear();
         shoppingCartRepository.save(shoppingCart); // Save the cleared cart
		return order;
	}

	// checkout if you want to add new billing,shipping,...other than you frist set in the
	// customer attribute
	public Order checkoutCart(String cartId, Address shippingAddress, Address billingAddress, CreditCard creditCard,
			ShippingOption shippingOption) {

		Optional<ShoppingCart> cart = shoppingCartRepository.findById(cartId);
		if (!cart.isPresent()) {
			throw new IllegalArgumentException(
					"the cart with this id is not available,check if you have already created a cart");
		}

		ShoppingCart shoppingCart = cart.get();
		if (shoppingCart.getCartLines().isEmpty()) {
			throw new RuntimeException("Cannot checkout with an empty cart.");
		}
		Order order = orderService.createOrder(shoppingCart, shippingAddress, billingAddress, creditCard, shippingOption);

		return order;
	}


	public ShoppingCart getShoppingCart(String cartId) {
		Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(cartId);
		if (!shoppingCartOptional.isPresent()) {
			throw new IllegalArgumentException(
					"the cart with this id is not available,check if you have already created a cart");
		}

		return shoppingCartOptional.get();
		
		
	}
	
	
	//methods that defined below helps as an  option if needed, when you want
	//to purchase product with out login to the web Shop App.
	
//
//    public ShoppingCart addProductToCart(String cartId, Product product) {
////        Optional<ShoppingCart> optionalCart = shoppingCartRepository.findById(cartId);
////        if (!optionalCart.isPresent()) {
////			throw new IllegalArgumentException("the cart with this id is not available");
////		}
//        ShoppingCart shoppingCart = getShoppingCart(cartId);
//        shoppingCart.addToCart(product);
//        return shoppingCartRepository.save(shoppingCart);
//    }
//
//    public Order checkoutCart(String cartId, Customer customer, Address shippingAddress, Address 
	// billingAddress, CreditCard creditCard, ShippingOption shippingOption) {
//        Optional<ShoppingCart> cart = shoppingCartRepository.findById(cartId);
//        if (!cart.isPresent()) {
//			throw new IllegalArgumentException("the cart with this id is not available,check if you have already created a cart");
//		}
//        ShoppingCart shoppingCart = cart.get();
//        Order order = orderService.createOrder(shoppingCart, customer, shippingAddress, billingAddress, creditCard, shippingOption);
//     
//        return order;
//    }
//
//	public ShoppingCart modifyQuantity(String id, int quantity, Product product) {
//	    ShoppingCart shoppingCart = getShoppingCart(id);
//        shoppingCart.modifyCart(product, quantity);
//        return shoppingCartRepository.save(shoppingCart);		
//	}

}
