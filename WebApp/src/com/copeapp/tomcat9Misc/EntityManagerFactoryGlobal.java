package com.copeapp.tomcat9Misc;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import lombok.Getter;

public class EntityManagerFactoryGlobal {

	public static EntityManagerFactoryGlobal instance = new EntityManagerFactoryGlobal();
	@Getter
	private static EntityManagerFactory emfactory;
	
	protected EntityManagerFactoryGlobal() {}
	
	public static EntityManagerFactoryGlobal getInstance() {
		return instance;
	}
	public static void createEMFactory() {
		emfactory = Persistence.createEntityManagerFactory("CopeApp");
	}
	public static void destroyEMFactory() {
		emfactory.close();
		emfactory = null;
	}
	
}
