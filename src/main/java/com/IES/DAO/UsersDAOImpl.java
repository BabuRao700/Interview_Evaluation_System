package com.IES.DAO;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.IES.models.User;

@Repository
public class UsersDAOImpl{

	@Autowired
    private SessionFactory sessionFactory;
	
	
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return (User)sessionFactory.getCurrentSession().get(User.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers(){
		return sessionFactory.getCurrentSession().createQuery(" from User").list();
	}
	
	public void addNewUser(User user)
	{
		sessionFactory.getCurrentSession().save(user);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAllUsersByRole(){
		
		return sessionFactory.getCurrentSession().createQuery(" from User where RoleId not in (1)").list();
	}
	

}
