package com.copeapp.entities.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.copeapp.entities.common.Role;
import com.copeapp.entities.common.User;

public class TestUserEntity {

	public static void main(String[] args) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CopeApp");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Query query = entitymanager.createQuery("Select r from Role r", Role.class);
		
		@SuppressWarnings("unchecked")
		List<Role> roles = query.getResultList();
		
		User user = new User("Gianluca", "Galletti", "Gallo", "5", "C", "VincioGay", roles, false);
		user.setMail("gianlucagalletti@ymail.com");
		user.setImageUrl("");
		user.setWallpaper("default");

		entitymanager.persist(user);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();
	}

}
