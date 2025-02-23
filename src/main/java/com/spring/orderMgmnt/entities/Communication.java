package com.spring.orderMgmnt.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Communication {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long communicationId;
	@ManyToOne
	@JoinColumn(name="cid",nullable = false)
	private Customer customer;
	private String intractionDate;
	private String notes;
	private String communicationType;
	public long getCommunicationId() {
		return communicationId;
	}
	public void setCommunicationId(long communicationId) {
		this.communicationId = communicationId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getIntractionDate() {
		return intractionDate;
	}
	public void setIntractionDate(String intractionDate) {
		this.intractionDate = intractionDate;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getCommunicationType() {
		return communicationType;
	}
	public void setCommunicationType(String communicationType) {
		this.communicationType = communicationType;
	}
	@Override
	public String toString() {
		return "Communication [communicationId=" + communicationId + ", customer=" + customer + ", intractionDate="
				+ intractionDate + ", notes=" + notes + ", communicationType=" + communicationType + "]";
	}
	
	
}
