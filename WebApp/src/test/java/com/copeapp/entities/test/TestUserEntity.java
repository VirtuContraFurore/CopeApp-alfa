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
		
		User user = new User("FabioTex", "VincioGay", "Fabio", "Tessaro", "5", "F", roles, false);
		user.setMail("fabio.tessaro.porta@gmail.com");
		user.setImageUrl("");
		user.setWallpaper("default");
		User user1 = new User("Gallo", "VincioGay", "Gianluca", "Galletti", "5", "C", roles, false);
		user.setMail("gianlucagalletti@ymail.it");
		user.setImageUrl("");
		user.setWallpaper("default");
		User user2 = new User("Cerammerda", "VincioGay", "Luca", "Ceragioli", "5", "F", roles, false);
		user.setMail("cerammerda@gioli.it");
		user.setImageUrl("");
		user.setWallpaper("default");

		entitymanager.persist(user);
		entitymanager.persist(user1);
		entitymanager.persist(user2);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();
	}

}
