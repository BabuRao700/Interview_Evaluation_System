package com.IES.services;

import java.util.List;

import com.IES.models.User;

public interface UsersService {
	
	public User getUser(int id);
	public List<User> getAllUsers();
	public void addNewUser(User user);
	public List<User> getAllUsersByRole();
}
