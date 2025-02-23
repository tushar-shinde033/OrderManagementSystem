package com.spring.orderMgmnt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.orderMgmnt.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin,Integer>{
	Optional<Admin> findByEmailAndPassword(String email, String password);
}
