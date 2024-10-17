package com.webshop.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Document
public class Customer {

	@Id
	private String customerNumber;
	private String fristName;
	private String lastName;
	private String email;
	private String phone;
	private Address address;
	private Address billingAddress;
	private Address shippingAddress;
	private Account account;
	private List<CreditCard> listOfCreditCards =  new ArrayList<>();
	private List<Order> orders = new ArrayList<>();
	private ShippingOption shippingOption;

	public Customer() {
		super();
	}

	public Customer(String customerNumber, String fristName, String lastName, String email, String phone,
			Address address, Account account, List<Order> orders) {
		this.customerNumber = customerNumber;
		this.fristName = fristName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.account = account;
		//listOfCreditCards.add(creditcard);
		this.orders = orders;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public ShippingOption getShippingOption() {
		return shippingOption;
	}

	public void setShippingOption(ShippingOption shippingOption) {
		this.shippingOption = shippingOption;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getFristName() {
		return fristName;
	}

	public void setFristName(String fristName) {
		this.fristName = fristName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<CreditCard> getListOfCreditCards() {
		return listOfCreditCards;
	}

	public void setListOfCreditCards(List<CreditCard> listOfCreditCards) {
		this.listOfCreditCards = listOfCreditCards;
//		listOfCreditCards.add(creditCard);
	}
//	public void addOrder(Order order) {
//		orders.add(order);
//	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> order) {
		this.orders = orders;
//		orders.add(order);
	}

}
