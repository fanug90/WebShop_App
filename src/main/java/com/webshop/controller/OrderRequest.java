package com.webshop.controller;

import com.webshop.domain.Address;
import com.webshop.domain.CreditCard;
import com.webshop.domain.Customer;
import com.webshop.domain.ShippingOption;

public class OrderRequest {
	
    private Address shippingAddress;
    private Address billingAddress;
    private CreditCard creditCard;
    private ShippingOption shippingOption;

    // Getters and Setters for all fields

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

    public ShippingOption getShippingOption() {
        return shippingOption;
    }

    public void setShippingOption(ShippingOption shippingOption) {
        this.shippingOption = shippingOption;
    }

}
