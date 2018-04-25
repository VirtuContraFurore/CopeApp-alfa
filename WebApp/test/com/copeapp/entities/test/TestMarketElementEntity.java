package com.copeapp.entities.test;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.copeapp.dao.market.MarketElementDao;
import com.copeapp.entities.market.MarketElement;

public class TestMarketElementEntity {
	
	public static void main(String[] args) {
		/*
		//User u = new User(firstname, lastname, username, classe, sezione, password, roles, firstEntry)
		EntityManager entitymanager = Persistence.createEntityManagerFactory("CopeApp").createEntityManager();
		entitymanager.getTransaction().begin();
		
		MarketElement m = new MarketElement();
		m.setDescription("maglietta rossa L");
		m.setImage("no-image");
		m.setName("DEDO");
		m.setPrice(1000.0);
		
		entitymanager.persist(m);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		System.out.println("edo");*/
		ArrayList<Integer> el = new ArrayList<>();
		el.add(1);
		el.add(1);
		
		ArrayList<MarketElement> m = (ArrayList<MarketElement>) MarketElementDao.getElementsListByIdsException(el);
		System.out.println(">>>>\n \n \n \n \n>>>>>>>>>>>>>"+m.get(0));
	}

}
