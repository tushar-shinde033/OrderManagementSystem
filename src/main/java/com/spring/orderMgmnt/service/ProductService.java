package com.spring.orderMgmnt.service;

import java.util.List;

import com.spring.orderMgmnt.entities.Product;

public interface ProductService {
	public Product saveProduct(Product product);
	public List<Product>getAllProducts();
}
