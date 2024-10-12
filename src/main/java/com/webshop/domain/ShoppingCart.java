package com.webshop.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ShoppingCart {
	@Id
	private String id;
	private List<CartLine> cartLines = new ArrayList<CartLine>();

	public ShoppingCart() {
		super();
	}

	public ShoppingCart(String id, List<CartLine> cartLines) {
		super();
		this.id = id;
		this.cartLines = cartLines;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<CartLine> getCartLines() {
		return cartLines;
	}

	public void setCartLines(List<CartLine> cartLines) {
		this.cartLines = cartLines;
	}
	// as longs we are working with rich domain driven design we need to encapsulate
	// the business logic of the domain with in the domain

	public void addToCart(Product product, int quantityChange) {
		CartLine existingLine = findCartLineByProduct(product);
		if (existingLine != null) {
			
			// b/c of the value object is immutable we can not modify quantity of the
			// cartline object after we created once so we create a new instance with an
			// updated quantity
			int newQuantity = existingLine.getQuantity() + quantityChange;
			
			if (newQuantity > 0) {
				// Update quantity by creating a new CartLine with the modified quantity
				cartLines.remove(existingLine);
				CartLine updatedLine = existingLine.withUpdatedQuantity(newQuantity);
				cartLines.add(updatedLine);
			} else {
				// Remove item from cart if the new quantity is zero or negative
				cartLines.remove(existingLine);
			}
		} else if ((quantityChange > 0)) {
			cartLines.add(new CartLine(product, quantityChange));
		}
//		return cartLines;
	}

	public void removeFromCart(Product product) {
		CartLine existingLine = findCartLineByProduct(product);
		if (existingLine != null) {
			cartLines.remove(existingLine);
		} else {
			throw new IllegalArgumentException("the product you wamt to remove is not available");
		}
	}

	private CartLine findCartLineByProduct(Product product) {
		for (CartLine line : cartLines) {
			if (line.getProduct().getProductNumber().equals(product.getProductNumber())) {
				return line;
			}
		}
		return null;
	}

}
