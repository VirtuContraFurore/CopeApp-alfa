package com.copeapp.dao.commons;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.copeapp.entities.common.User;
import com.copeapp.tomcat9Misc.StartupOperations;

public class UserDAO {
	
	public static User selectById (Integer userId) {
		
		EntityManager entitymanager = StartupOperations.emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT u FROM User u WHERE u.userId = :userId", User.class);
		query.setParameter("userId", userId);
		try {
			return (User) query.getSingleResult();
		}catch (NoResultException nrex) {
			return null;
		}finally{
			entitymanager.getTransaction().commit();
			entitymanager.close();
		}
	}
}
