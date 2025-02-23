package com.spring.orderMgmnt.service;

import com.spring.orderMgmnt.entities.Order;
import java.util.*;

public interface OrderService {
	public Order saveOrder(String email,Order order);
	public String findByProductNameAndCustomer_Email(String product_name,String email);
	public List<Order> getOrdersByCustomerEmail(String email);
	public String updateOrderByProductAndEmail(String product, String email, Order updatedOrder);
}
