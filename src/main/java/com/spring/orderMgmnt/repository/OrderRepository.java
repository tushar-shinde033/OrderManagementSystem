package com.spring.orderMgmnt.repository;
import org.springframework.stereotype.Repository;
import com.spring.orderMgmnt.entities.Order;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    Optional<Order> findByProductNameAndCustomer_Email(String product_name, String email);
    List<Order> findByCustomer_Email(String email);
}
