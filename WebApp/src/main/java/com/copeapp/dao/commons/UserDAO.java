package com.copeapp.dao.commons;

import java.util.Base64;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.copeapp.entities.common.User;
import com.copeapp.exception.CopeAppGenericException;
import com.copeapp.exception.InvalidAuthTokenException;
import com.copeapp.exception.LoginException;
import com.copeapp.tomcat9Misc.EntityManagerFactoryGlobal;
import com.copeapp.utilities.HttpStatusUtility;
import com.copeapp.utilities.MessageUtility;

public class UserDAO {
	
	public static User selectByUsername (String username, String password) throws LoginException{
		
		EntityManager entitymanager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();
		TypedQuery<User> query = entitymanager.createQuery("FROM User WHERE (username = :username OR mail = :username)", User.class);
		query.setParameter("username", username);
		User selectedUser;
		try {
			selectedUser = (User) query.getSingleResult();
			if (selectedUser.getPassword().equals(password)) {
				return selectedUser;
			}else {
				throw new LoginException(HttpStatusUtility.UNAUTHORIZED, MessageUtility.WRONG_PASSWORD);
			}
		}catch (NoResultException nre) {
			throw new LoginException(HttpStatusUtility.UNAUTHORIZED, MessageUtility.WRONG_USERNAME, nre);
		}
	}

	public static User selectByBasicAuthTokenException (String authHeader) throws InvalidAuthTokenException {
		
		if (authHeader == null) {
			throw new InvalidAuthTokenException(HttpStatusUtility.UNAUTHORIZED, MessageUtility.INVALID_HEADER);
		}
		String token = new String(Base64.getDecoder().decode(authHeader));
		String[] tokens = token.split(":");
		if (tokens.length>2) {
			throw new CopeAppGenericException(HttpStatusUtility.INTERNAL_SERVER_ERROR, " ':' presenti in username o password");
		}
		
		return selectByUsername(tokens[0],tokens[1]);
	}	
}
