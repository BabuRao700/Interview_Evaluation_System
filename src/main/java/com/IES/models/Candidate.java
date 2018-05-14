package com.IES.models;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="candidate")
public class Candidate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="FirstName", nullable=false)
	private String firstName;
	
	@Column(name="LastName", nullable=false)
	private String lastName;
	
	@Column(name="YearsOfExp")
	private int yearsOfExp;
	
	@Column(name="TeckSkills")
	private String techSkills;
	
	@Column(name="EmailId",nullable=false)
	private String emailId;
	
	@Column(name="PhoneNumber")
	private String phoneNumber;
	
	@Column(name="CreatedDateTime")
	private Date createdDateTime;
	
	@Column(name="UpdatedDateTime")
	private Date updatedDateTime;
	
	@Column(name="interviewerId")
	private int interviewerId;
	
	@Column(name="IsEvaluated")
	private boolean evaluated;
	
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	

	public boolean isEvaluated() {
		return evaluated;
	}

	public void setEvaluated(boolean evaluated) {
		this.evaluated = evaluated;
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getInterviewerId() {
		return interviewerId;
	}

	public void setInterviewerId(int interviewerId) {
		this.interviewerId = interviewerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getYearsOfExp() {
		return yearsOfExp;
	}

	public void setYearsOfExp(int yearsOfExp) {
		this.yearsOfExp = yearsOfExp;
	}

	public String getTechSkills() {
		return techSkills;
	}

	public void setTechSkills(String techSkills) {
		this.techSkills = techSkills;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Date getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	
}
