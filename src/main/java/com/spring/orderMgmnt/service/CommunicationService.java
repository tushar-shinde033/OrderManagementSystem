package com.spring.orderMgmnt.service;

import java.util.List;

import com.spring.orderMgmnt.entities.Communication;
import com.spring.orderMgmnt.entities.Order;

public interface CommunicationService {
	public Communication addCommunication(String email,Communication communication);
	public List<Communication> getCommunicationByCustomerEmail(String email);
	public String updateCommunication(String date,Communication updatedCommunication);
	public String deleteCommunication(long cid);
}
