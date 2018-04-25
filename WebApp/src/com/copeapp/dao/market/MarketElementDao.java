package com.copeapp.dao.market;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.copeapp.entities.market.MarketElement;
import com.copeapp.tomcat9Misc.StartupOperations;

public class MarketElementDao {
	
	public static List<MarketElement> getElementsListByIdsException(List<Integer> elementsIds) {
		
		String queryElements = "SELECT e FROM MarketElement e WHERE e.marketElementId in (";  
		for(int id : elementsIds) {
			queryElements.concat("'"+id +"',");
		}
		queryElements = queryElements.substring(0, queryElements.length()-2) + ")";
		
		EntityManager entitymanager = StartupOperations.emfactory.createEntityManager();
		TypedQuery<MarketElement> query = entitymanager.createQuery(queryElements, MarketElement.class);
		
		return query.getResultList();
	}
}
