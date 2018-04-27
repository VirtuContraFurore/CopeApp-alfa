package com.copeapp.tomcat9Misc;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class StartupOperations implements ServletContextListener {
	
	public StartupOperations() {}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//execute commands before shutdown
		EntityManagerFactoryGlobal.destroyEMFactory();
		System.out.println("Server shut down");
	}
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Server started up");
		EntityManagerFactoryGlobal.createEMFactory();
		//execute commands after startup
	}
	
}
