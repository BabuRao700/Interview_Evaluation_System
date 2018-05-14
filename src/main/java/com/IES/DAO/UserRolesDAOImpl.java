package com.IES.DAO;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.IES.models.UserRoles;

@Repository
public class UserRolesDAOImpl{

	
    @Autowired
    private SessionFactory sessionFactory;
    
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<UserRoles> getUserRoles() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from UserRoles")
        .list();
	}
	

}
