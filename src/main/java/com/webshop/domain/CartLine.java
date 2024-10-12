package com.webshop.domain;

public class CartLine {

	private final Product product;
	private final int quantity;

	public CartLine(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	// Method to update quantity, returns a new CartLine instance
	public CartLine withUpdatedQuantity(int newQuantity) {
		return new CartLine(this.product, newQuantity);
	}

}
