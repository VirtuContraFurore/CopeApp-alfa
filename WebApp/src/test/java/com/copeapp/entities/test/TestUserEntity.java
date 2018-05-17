package com.copeapp.entities.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.copeapp.dao.commons.RoleDAO;
import com.copeapp.entities.common.Role;
import com.copeapp.entities.common.User;

public class TestUserEntity {

	public static void main(String[] args) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CopeApp");

		EntityManager entitymanager = emfactory.createEntityManager();
		
		List<Role> roles = RoleDAO.getRolesList();
		
		User user = new User("FabioTex", "VincioGay", "Fabio", "Tessaro", "5", "F", roles, false);
		user.setMail("fabio.tessaro.porta@gmail.com");
		user.setImageUrl("");
		user.setWallpaper("default");
		User user1 = new User("Gallo", "VincioGay", "Gianluca", "Galletti", "5", "C", roles, false);
		user1.setMail("gianlucagalletti@ymail.it");
		user1.setImageUrl("");
		user1.setWallpaper("default");
		User user2 = new User("Cerammerda", "VincioGay", "Luca", "Ceragioli", "5", "F", roles, false);
		user2.setMail("cerammerda@gioli.it");
		user2.setImageUrl("");
		user2.setWallpaper("default");

		entitymanager.persist(user);
		entitymanager.persist(user1);
		entitymanager.persist(user2);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();
	}

}
