package com.webshop.domain;

public class Stock {
	
	private final int noInStock;
	private final String locationNumber;

	public Stock(Integer noInStock, String locationNumber) {
		
		this.noInStock = noInStock;
		this.locationNumber = locationNumber;
	}

	public int getNoInStock() {
		return noInStock;
	}

	public String getLocationNumber() {
		return locationNumber;
	}
	

}
