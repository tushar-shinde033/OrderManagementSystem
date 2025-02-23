package com.spring.orderMgmnt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.orderMgmnt.entities.Communication;
@Repository
public interface CommunicationRepository extends JpaRepository<Communication,Long> {
	List<Communication> findByCustomer_Cid(int cid);
	List<Communication> findByCustomer_Email(String email);
	Communication findByIntractionDate(String intractionDate);
	boolean existsByCommunicationId(Long communicationId);
}
