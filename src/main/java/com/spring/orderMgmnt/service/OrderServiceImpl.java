package com.spring.orderMgmnt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.orderMgmnt.entities.Customer;
import com.spring.orderMgmnt.entities.Order;
import com.spring.orderMgmnt.repository.CustomerRepository;
import com.spring.orderMgmnt.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	public CustomerRepository customerRepository;
	
	@Autowired
	public OrderRepository orderRepository;
	
	public Order saveOrder(String email,Order order) {
		Customer customer=customerRepository.findByEmail(email);
		order.setCustomer(customer);
		Order result=orderRepository.save(order);
		return result;
	}

	@Override
	public String findByProductNameAndCustomer_Email(String product_name, String email) {
		Optional<Order> order = orderRepository.findByProductNameAndCustomer_Email(product_name, email);
		if (order.isPresent()) {
            orderRepository.delete(order.get());
            return "EXIST";
        } else {
        	return "NOTEXIST";
        }
	}

	@Override
	public List<Order> getOrdersByCustomerEmail(String email) {
		return orderRepository.findByCustomer_Email(email);
	}

	@Override
	public String updateOrderByProductAndEmail(String product, String email, Order updatedOrder) {
		 Optional<Order> existingOrder = orderRepository.findByProductNameAndCustomer_Email(product, email);
		 
		 if (existingOrder.isPresent()) {
	            Order orderToUpdate = existingOrder.get();
	            orderToUpdate.setProductName(updatedOrder.getProductName());
	            orderToUpdate.setQuantity(updatedOrder.getQuantity()); 
	            orderToUpdate.setPrice(updatedOrder.getPrice());
	            orderToUpdate.setDate(updatedOrder.getDate());
	            orderRepository.save(orderToUpdate);
//	            return "Order updated successfully!";
	            return "FOUND";
	        } else {
	        	return "NOTFOUND";
//	            return "Error: No order found for product '" + product + "' linked to email '" + email + "'.";
	        }
	}
}
