package com.copeapp.dao.market;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.copeapp.entities.market.MarketElement;

public class MarketElementDao {
	
	public static List<MarketElement> getElementsListByIdsException(List<Integer> elementsIds) {
		
		EntityManager entitymanager = Persistence.createEntityManagerFactory("CopeApp").createEntityManager();
		TypedQuery<MarketElement> query = entitymanager.createQuery("SELECT e FROM MarketElement e WHERE e.marketElementId in (:ids)", MarketElement.class);
		query.setParameter("ids", elementsIds);
		return query.getResultList();
	}
}
