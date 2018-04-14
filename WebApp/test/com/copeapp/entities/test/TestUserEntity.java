package com.copeapp.entities.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.copeapp.entities.Role;
import com.copeapp.entities.User;

public class TestUserEntity {

	public static void main(String[] args) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CopeApp");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Query query = entitymanager.createQuery("Select r from Role r", Role.class);
		
		@SuppressWarnings("unchecked")
		List<Role> roles = query.getResultList();
		
		User user = new User(0, "cerammerda@gioli.it", "Luca", "Ceragioli", 
				"Cerammerda", "5", "F", "VincioGay", roles,
				"", "default", false);

		entitymanager.persist(user);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();
	}

}
