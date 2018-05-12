package com.copeapp.tomcat9Misc;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import lombok.Getter;

public class EntityManagerFactoryGlobal {

	private static EntityManagerFactoryGlobal instance = new EntityManagerFactoryGlobal();
	
	@Getter
	private EntityManagerFactory emfactory;
	
	private EntityManagerFactoryGlobal() {}
	
	public static EntityManagerFactoryGlobal getInstance() {
		return instance;
	}
	public void createEMFactory() {
		emfactory = Persistence.createEntityManagerFactory("CopeApp");
	}
	public void destroyEMFactory() {
		emfactory.close();
		emfactory = null;
	}
	
}
