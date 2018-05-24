package com.copeapp.utilities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.copeapp.entities.common.Role;
import com.copeapp.entities.common.User;
import com.copeapp.entities.survey.Answer;
import com.copeapp.entities.survey.Survey;

public class PopulateDatabase {
	
	public static void doPopulate() {
		
		EntityManager entityManager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();
		entityManager.getTransaction().begin();
		try {
			List<Role> roles = new ArrayList<Role>();
			roles.add(new Role("studente", "Studente"));
			roles.add(new Role("prof", "Professore"));
			roles.add(new Role("moderatore", "Moderatore"));
			roles.add(new Role("admin", "Amministratore"));
			roles.add(new Role("redattore", "Redattore"));
			roles.add(new Role("rappresentante", "Rappresentante"));
			
			for(Role r : roles) entityManager.persist(r);
			
			User user0 = new User("FabioTex", "VincioGay", "Fabio", "Tessaro", "5", "F", roles, false);
			user0.setMail("fabio.tessaro.porta@gmail.com");
			user0.setImageUrl("");
			user0.setWallpaper("default");
			User user1 = new User("Gallo", "VincioGay", "Gianluca", "Galletti", "5", "C", roles, false);
			user1.setMail("gianlucagalletti@ymail.it");
			user1.setImageUrl("");
			user1.setWallpaper("default");
			User user2 = new User("Cerammerda", "VincioGay", "Luca", "Ceragioli", "5", "F", roles, false);
			user2.setMail("cerammerda@gioli.it");
			user2.setImageUrl("");
			user2.setWallpaper("default");
			
			entityManager.persist(user0);
			entityManager.persist(user1);
			entityManager.persist(user2);
		} finally {
			entityManager.getTransaction().commit();
			entityManager.close();
		}
	}

}
