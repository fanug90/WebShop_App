package com.webshop.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.webshop.domain.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {

	Optional<ShoppingCart> findByCustomerNumber(String customerNumber);

	//public Optional<ShoppingCart> findByCartId(String cartId);
}
