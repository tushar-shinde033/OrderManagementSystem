package com.spring.orderMgmnt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.orderMgmnt.entities.Communication;
import com.spring.orderMgmnt.entities.Customer;
import com.spring.orderMgmnt.entities.Order;
import com.spring.orderMgmnt.repository.CommunicationRepository;
import com.spring.orderMgmnt.repository.CustomerRepository;

@Service
public class CommunicationServiceImpl implements CommunicationService {
	@Autowired
	public CommunicationRepository communicationRepository;

	@Autowired
	public CustomerRepository customerRepository;
	
	public Communication addCommunication(String email,Communication communication) {
		Customer customer=customerRepository.findByEmail(email);
		communication.setCustomer(customer);
		return communicationRepository.save(communication);
	}
	
	public Communication updateCommunication(long communicationId,Communication updatedCommunication) {
		Communication existing=communicationRepository.findById(communicationId).orElseThrow();
		existing.setIntractionDate(updatedCommunication.getIntractionDate());
		existing.setNotes(updatedCommunication.getNotes());
		existing.setCommunicationType(updatedCommunication.getCommunicationType());
		return communicationRepository.save(existing);
	}
	
	@Override
	public String updateCommunication(String date, Communication updatedCommunication) {
		Communication existingCommunication=communicationRepository.findByIntractionDate(date);
		System.out.println(existingCommunication);
		if(existingCommunication!=null) {
			System.out.println(updatedCommunication.getCommunicationType());
			System.out.println(updatedCommunication.getIntractionDate());
			System.out.println(updatedCommunication.getNotes());
			existingCommunication.setIntractionDate(updatedCommunication.getIntractionDate());
			existingCommunication.setNotes(updatedCommunication.getNotes());
			existingCommunication.setCommunicationType(updatedCommunication.getCommunicationType());
			communicationRepository.save(existingCommunication);
			return "FIND";
		}else {
			return "NOTFIND";
		}
	}
	

	@Override
	public String deleteCommunication(long cid) {
		if(communicationRepository.existsByCommunicationId(cid)) {
			communicationRepository.deleteById((long) cid);
			return "EXIST";
		}
		return "NOTEXIST";
	}
	
	@Override
	public List<Communication> getCommunicationByCustomerEmail(String email) {
		return communicationRepository.findByCustomer_Email(email);
	}


}
