package com.IES.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name= "userroles")
public class UserRoles {
	
	@Id
	private int Id;
	
	@Column(name="RoleName")
	private String roleName;
	
	@Column(name="Description")
	private String description;
	
	public int getId() {
		return Id;
	}

	public String getRoleName() {
		return roleName;
	}

	public String getDescription() {
		return description;
	}



}
