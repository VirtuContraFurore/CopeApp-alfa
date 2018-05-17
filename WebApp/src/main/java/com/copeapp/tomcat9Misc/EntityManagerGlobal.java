package com.copeapp.tomcat9Misc;

import javax.persistence.EntityManager;

import lombok.Getter;

public class EntityManagerGlobal {

	private static EntityManagerGlobal instance = new EntityManagerGlobal();
	
	@Getter
	private EntityManager entityManager;
	
	private EntityManagerGlobal() {}
	
	public static EntityManagerGlobal getInstance() {
		return instance;
	}
	public void createEntityManager() {
		entityManager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();
		entityManager.getTransaction().begin();
	}
	public void commitEntityManager() {
		entityManager.getTransaction().commit();
		entityManager.getTransaction().begin();
	}
	public void destroyEntityManager() {
		if (entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().commit();
		}
		entityManager.close();
		entityManager = null;
	}
	
}
