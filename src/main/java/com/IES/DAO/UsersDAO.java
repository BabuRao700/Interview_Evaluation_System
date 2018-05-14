package com.IES.DAO;

import java.util.List;

import com.IES.models.User;


public interface UsersDAO {	
	public User getUser(int id);
	public List<User> getAllUsers();
	public void insertUser(User user);
}
