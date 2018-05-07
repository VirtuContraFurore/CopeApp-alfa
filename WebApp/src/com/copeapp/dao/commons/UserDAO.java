package com.copeapp.dao.commons;

import java.util.Base64;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.copeapp.entities.common.User;
import com.copeapp.exception.InvalidAuthTokenException;
import com.copeapp.exception.LoginFailedException;
import com.copeapp.tomcat9Misc.EntityManagerFactoryGlobal;
import com.copeapp.utilities.HttpStatusUtility;

public class UserDAO {
	
	/*
	public static User selectById (Integer userId) {
		
		EntityManager entitymanager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();
		return entitymanager.find(User.class, userId);
	}
	
	
	
	public static User selectByIdException (Integer userId) {
		
		EntityManager entitymanager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();
		Query query = entitymanager.createQuery("SELECT u FROM User u WHERE u.userId = :userId", User.class);
		query.setParameter("userId", userId);
		return (User) query.getSingleResult();
	}
	
	public static User selectByUsernameException (String username) {
		
		EntityManager entitymanager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();
		Query query = entitymanager.createQuery("SELECT u FROM User u WHERE u.username = :username OR u.mail = :username", User.class);
		query.setParameter("username", username);
		return (User) query.getSingleResult();
	}*/
	
	public static User selectByUsername (String username, String password) throws LoginFailedException{
		
		EntityManager entitymanager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();
		Query query = entitymanager.createQuery("FROM User WHERE (username = :username OR mail = :username)", User.class);
		query.setParameter("username", username);
		User selectedUser;
		try {
			selectedUser = (User) query.getSingleResult();
			if (selectedUser.getPassword().equals(password)) {
				return selectedUser;
			}else {
				throw new LoginFailedException(HttpStatusUtility.UNAUTHORIZED, "Password non corretta");
			}
		}catch (NoResultException nrex) {
			throw new LoginFailedException(HttpStatusUtility.UNAUTHORIZED, "Utente non trovato", nrex);
		}
	}

	public static User selectByBasicAuthTokenException (String authHeader) throws InvalidAuthTokenException {
		
		if (authHeader == null) {
			throw new InvalidAuthTokenException(HttpStatusUtility.UNAUTHORIZED, "Invalid header");
		}
		authHeader = authHeader.substring(6); //To select the correct part of the header
		String token = new String(Base64.getDecoder().decode(authHeader));
		String[] tokens = token.split(":"); //TODO: username and password cannot contain :
		
		EntityManager entitymanager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();
		Query query = entitymanager.createQuery("SELECT u FROM User u WHERE u.username = :username OR u.mail = :username", User.class);
		query.setParameter("username", tokens[0]);
		try {
			User response = (User) query.getSingleResult();
			if (!response.getPassword().equals(tokens[1])) {
				throw new InvalidAuthTokenException(HttpStatusUtility.UNAUTHORIZED, "Password non corretta");
			}
			return response;
		} catch (NoResultException nre){
			throw new InvalidAuthTokenException(HttpStatusUtility.UNAUTHORIZED, "User not found", nre);
		}
	}
	
}
