package com.IES.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IES.DAO.UserRolesDAOImpl;
import com.IES.models.UserRoles;


@Service
@Transactional
public class UserRolesServiceImpl implements UserRolesService{

	@Autowired
	private UserRolesDAOImpl userRolesDAOImpl;
	
	@Transactional
	public List<UserRoles> getUserRoles() {
		// TODO Auto-generated method stub
		return userRolesDAOImpl.getUserRoles();
	}

	public UserRolesDAOImpl getUserRolesDAOImpl() {
		return userRolesDAOImpl;
	}

	public void setUserRolesDAOImpl(UserRolesDAOImpl userRolesDAOImpl) {
		this.userRolesDAOImpl = userRolesDAOImpl;
	}

	

}
