package com.copeapp.dao.commons;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.copeapp.entities.common.User;
import com.copeapp.tomcat9Misc.EntityManagerFactoryGlobal;
public class UserDAO {
	
	public static User selectById (Integer userId) {
		
		EntityManager entitymanager = EntityManagerFactoryGlobal.getEmfactory().createEntityManager();
		Query query = entitymanager.createQuery("SELECT u FROM User u WHERE u.userId = :userId", User.class);
		query.setParameter("userId", userId);
		try {
			return (User) query.getSingleResult();
		}catch (NoResultException nrex) {
			return null;
		}
	}
	
	public static User selectByIdException (Integer userId) {
		
		EntityManager entitymanager = EntityManagerFactoryGlobal.getEmfactory().createEntityManager();
		Query query = entitymanager.createQuery("SELECT u FROM User u WHERE u.userId = :userId", User.class);
		query.setParameter("userId", userId);
		return (User) query.getSingleResult();
	}
	
	public static User selectByUsernameException (String username) {
		
		EntityManager entitymanager = EntityManagerFactoryGlobal.getEmfactory().createEntityManager();
		Query query = entitymanager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
		query.setParameter("username", username);
		return (User) query.getSingleResult();
	}
}
