package com.IES.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IES.DAO.UsersDAOImpl;
import com.IES.models.User;

@Service
public class UsersServiceImpl implements UsersService{
	
	@Autowired
	private UsersDAOImpl usersDAOImpl;
	
	@Transactional
	public User getUser(int id) {
		
		return usersDAOImpl.getUser(id);
	}
	
	@Transactional
	public List<User> getAllUsers(){
		return usersDAOImpl.getAllUsers();
	}

	@Transactional
	public void addNewUser(User user) {
		// TODO Auto-generated method stub
		  usersDAOImpl.addNewUser(user);
	}

	@Override
	@Transactional
	public List<User> getAllUsersByRole() {
		// TODO Auto-generated method stub
		
		return usersDAOImpl.getAllUsersByRole();
	}

}
