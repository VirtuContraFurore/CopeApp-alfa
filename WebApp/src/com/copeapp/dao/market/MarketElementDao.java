package com.copeapp.dao.market;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.copeapp.entities.market.MarketElement;
import com.copeapp.tomcat9Misc.StartupOperations;

public class MarketElementDao {
	
//	public static List<MarketElement> getElementsListByIdsException(List<Integer> elementsIds) {
//		
//		System.out.println("ciao: "+(StartupOperations.getEMFactory() == null));
//		EntityManager entitymanager = StartupOperations.
//				getEMFactory().
//				createEntityManager();
//		TypedQuery<MarketElement> query = entitymanager.createQuery("SELECT e FROM MarketElement e WHERE e.marketElementId in (:ids)", MarketElement.class);
//		query.setParameter("ids", elementsIds);
//		return query.getResultList();
//	}
}
