package com.pmc.ecommerce.dto;

import com.pmc.ecommerce.entity.Address;
import com.pmc.ecommerce.entity.Customer;
import com.pmc.ecommerce.entity.Order;
import com.pmc.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
//@data automaticall generates getters and setters for given objects
public class Purchase {
    //from frontend checkout form we are sending these 5 fields.
    //customer information,addresses order info and list of orderf info
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    //we can use set for array entity or json array
    private Set<OrderItem> orderItems;

}
