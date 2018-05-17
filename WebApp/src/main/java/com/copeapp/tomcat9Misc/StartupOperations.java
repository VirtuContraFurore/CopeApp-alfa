package com.copeapp.tomcat9Misc;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class StartupOperations implements ServletContextListener {
	
	public StartupOperations() {}
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//execute commands after startup
		System.out.println("Server started up");
		EntityManagerFactoryGlobal.getInstance().createEMFactory();
		//TODO provare a fargli capire da solo se ha appena fatto un refresh della base dati
//		PopulateDatabase.doPopulate(); //Rimuovere se hibernate.hbm2ddl.auto su persistence.xml e' settato su update
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//execute commands before shutdown
		EntityManagerFactoryGlobal.getInstance().destroyEMFactory();
		System.out.println("Server shut down");
	}
}
