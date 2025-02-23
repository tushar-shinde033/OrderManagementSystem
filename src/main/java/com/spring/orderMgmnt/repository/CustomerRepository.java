package com.spring.orderMgmnt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.orderMgmnt.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>{
	
	@Transactional
    @Modifying
    @Query("DELETE FROM Customer c WHERE c.email = :email")
    void deleteByEmail(String email);
	
	boolean existsByEmail(String email);
	Customer findByEmail(String email);
	
}
