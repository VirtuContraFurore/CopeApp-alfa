package com.copeapp.entities.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestUserEntity {

	public static void main(String[] args) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CopeApp");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		//Query query = entitymanager.createQuery("Select r from Role r", Role.class);
		
		//@SuppressWarnings("unchecked")
		//List<Role> roles = query.getResultList();
		
		//User user = new User("Luca", "Ceragioli", "Cerammerda", "5", "F", "VincioGay", roles, false);
		//user.setMail("cerammerda@gioli.it");
		//user.setImageUrl("");
		//user.setImageUrl("default");

		//entitymanager.persist(user);
		//entitymanager.getTransaction().commit();

		//entitymanager.close();
		//emfactory.close();
	}

}
