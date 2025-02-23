package com.spring.orderMgmnt.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spring.orderMgmnt.entities.Customer;
import com.spring.orderMgmnt.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	public CustomerRepository customerRepository;
	
	@Override
	public Customer saveCustomer(Customer customer) {
		Customer result=customerRepository.save(customer);
		return result;
	}


	@Override
	public String updateCustomer(String email, Customer updatedCustomer) {
		Customer existingCustomer=customerRepository.findByEmail(email);
		if(existingCustomer!=null) {
			existingCustomer.setName(updatedCustomer.getName());
			existingCustomer.setContact(updatedCustomer.getContact());
			existingCustomer.setEmail(updatedCustomer.getEmail());
			existingCustomer.setAddress(updatedCustomer.getAddress());
			customerRepository.save(existingCustomer);
		    return "FIND";
	     }else {
		    return "NOTFIND";
	  }
   }

	@Override
	@Transactional
	public String deleteCustomer(String email) {
		if(customerRepository.existsByEmail(email)) {
			customerRepository.deleteByEmail(email);
			return "EXIST";
		}
		return "NOTEXIST";	
	}

	@Override
	public Customer getCustomers(String email) {
	    Customer list=this.customerRepository.findByEmail(email);
		return list;
	}
	
	public List<Customer>getAdminCustomers(){
		List<Customer>list=this.customerRepository.findAll();
		return list;
	}
	
	
	 public String registerCustomer(String name, String email) {
	        if (customerRepository.existsByEmail(email)) {
	            return "EXIST";
	        }
	       return "NOTEXIST";
	    }

}
