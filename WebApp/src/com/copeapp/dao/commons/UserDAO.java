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
	
	public static User selectById (Integer userId) {
		
		EntityManager entitymanager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();
		return entitymanager.find(User.class, userId); // tiè vincio del cazzo
	}
	
	public static User selectByUsername (String username) throws LoginFailedException{
		
		EntityManager entitymanager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();
		Query query = entitymanager.createQuery("SELECT u FROM User u WHERE u.username = :username OR u.mail = :username", User.class);
		query.setParameter("username", username);
		User ret = (User) query.getSingleResult();
		if (ret == null) {
			throw new LoginFailedException(HttpStatusUtility.UNAUTHORIZED, "Utente non trovato");
		}
		return ret;
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
	}
	
	public static User selectByBasicAuthTokenException (String authHeader) throws InvalidAuthTokenException {
		
		if (authHeader == null) {
			throw new InvalidAuthTokenException(HttpStatusUtility.UNAUTHORIZED, "Invalid header");
		}
		authHeader = authHeader.substring(6);
		String token = new String(Base64.getDecoder().decode(authHeader));
		String[] tokens = token.split(":");
		
		EntityManager entitymanager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();
		Query query = entitymanager.createQuery("SELECT u FROM User u WHERE u.username = :username OR u.mail = :username", User.class);
		query.setParameter("username", tokens[0]);
		try {
			User ret = (User)query.getSingleResult();
			if (!ret.getPassword().equals(tokens[1])) {
				throw new InvalidAuthTokenException(HttpStatusUtility.UNAUTHORIZED, "Invalid password");
			}
			return ret;
		} catch (NoResultException nre){
			throw new InvalidAuthTokenException(HttpStatusUtility.UNAUTHORIZED, "User not found", nre);
		}
	}
	
}
