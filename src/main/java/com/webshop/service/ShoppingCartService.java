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
import com.webshop.repository.ShoppingCartRepository;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private OrderService orderService;

    public ShoppingCart getShoppingCart(String cartId) {
        return shoppingCartRepository.findById(cartId)
                .orElse(new ShoppingCart());
    }

    public ShoppingCart addProductToCart(String cartId, Product product, int quantity) {
//        Optional<ShoppingCart> optionalCart = shoppingCartRepository.findById(cartId);
//        if (!optionalCart.isPresent()) {
//			throw new IllegalArgumentException("the cart with this id is not available");
//		}
        ShoppingCart shoppingCart = getShoppingCart(cartId);
        shoppingCart.addToCart(product, quantity);
        return shoppingCartRepository.save(shoppingCart);
    }

    public Order checkoutCart(String cartId, Customer customer, Address shippingAddress, Address billingAddress, CreditCard creditCard, ShippingOption shippingOption) {
        Optional<ShoppingCart> cart = shoppingCartRepository.findById(cartId);
        if (!cart.isPresent()) {
			throw new IllegalArgumentException("the cart with this id is not available,check if you have already created a cart");
		}
        ShoppingCart shoppingCart = cart.get();
        Order order = orderService.createOrder(shoppingCart, customer, shippingAddress, billingAddress, creditCard, shippingOption);
     
        return order;
    }

}
