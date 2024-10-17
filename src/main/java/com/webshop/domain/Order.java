package com.webshop.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Order {

	@Id
	private String orderNumber;
	private Date date;
	private Address shippingAddress;
	private Address billingAddress;
	private CreditCard creditCard;
	private List<OrderLine> orderLines;
	private String customerNumber;
	private ShippingOption shippingOption;

	public Order() {
		super();
		this.date = new Date();
	}

	public Order(String customerNumber, Address shippingAddress, Address billingAddress, CreditCard creditCard, List<OrderLine> orderlines,
			ShippingOption shippingOption) {

		this.customerNumber = customerNumber;
		this.date = new Date();
		this.shippingAddress = shippingAddress;
		this.billingAddress = billingAddress;
		this.creditCard = creditCard;
		this.orderLines = orderLines;

		this.shippingOption = shippingOption;
	}

	public void confirm() {

	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public ShippingOption getShippingOption() {
		return shippingOption;
	}

	public void setShippingOption(ShippingOption shippingOption) {
		this.shippingOption = shippingOption;
	}

}
