package com.spring.orderMgmnt.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.orderMgmnt.entities.Product;
import com.spring.orderMgmnt.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	public ProductRepository productRepository;
	
	@Override
	public Product saveProduct(Product product) {
		Product result=productRepository.save(product);
		return result;
	}
	
	@Override
	public List<Product>getAllProducts(){
		List<Product>list=this.productRepository.findAll();
		return list;
	}
}
