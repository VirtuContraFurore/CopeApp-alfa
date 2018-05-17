package com.copeapp.tomcat9Misc;

import java.util.ArrayList;
import java.util.List;

import com.copeapp.entities.common.Role;
import com.copeapp.entities.common.User;

public class PopulateDatabase {
	
	public static void doPopulate() {
		
		EntityManagerGlobal.getInstance().createEntityManager();
		List<Role> roles = new ArrayList<Role>();
		roles.add(new Role("studente", "Studente"));
		roles.add(new Role("prof", "Professore"));
		roles.add(new Role("moderatore", "Moderatore"));
		roles.add(new Role("admin", "Amministratore"));
		roles.add(new Role("redattore", "Redattore"));
		roles.add(new Role("rappresentante", "Rappresentante"));
		
		for(Role r : roles) EntityManagerGlobal.getInstance().getEntityManager().persist(r);
		
		EntityManagerGlobal.getInstance().commitEntityManager();
		
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
		
		EntityManagerGlobal.getInstance().getEntityManager().persist(user);
		EntityManagerGlobal.getInstance().getEntityManager().persist(user1);
		EntityManagerGlobal.getInstance().getEntityManager().persist(user2);
		EntityManagerGlobal.getInstance().commitEntityManager();
		EntityManagerGlobal.getInstance().destroyEntityManager();
	}

}
