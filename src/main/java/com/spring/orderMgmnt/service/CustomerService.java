package com.spring.orderMgmnt.service;

import java.util.List;

import com.spring.orderMgmnt.entities.Customer;
import com.spring.orderMgmnt.entities.Order;

public interface CustomerService {
	public Customer saveCustomer(Customer customer);
	public String updateCustomer(String email,Customer updatedCustomer);
	public String deleteCustomer(String email);
	public Customer getCustomers(String email);
	public String registerCustomer(String name, String email);
	public List<Customer>getAdminCustomers();
	
}
