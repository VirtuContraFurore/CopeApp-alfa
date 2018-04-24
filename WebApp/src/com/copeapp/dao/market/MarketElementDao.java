package com.copeapp.dao.market;

import com.copeapp.entities.common.User;
import com.copeapp.entities.market.MarketElement;
import com.copeapp.tomcat9Misc.StartupOperations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class MarketElementDao {
	public List<MarketElement> getElementsListByIdsException(List<Integer> elementsIds) {
		String queryElements = "SELECT e FROM MarketElement e WHERE e.marketElementId in (";  

		for(int id : elementsIds) {
			queryElements.concat("'"+id +"'"+",");
		}
		queryElements.concat(")");  //un unica query della forma 
									//SELECT e FROM MarketElement e WHERE e.marketElementId in (id1,id2,...)
		EntityManager entitymanager = StartupOperations.emfactory.createEntityManager();
		Query query = entitymanager.createQuery(queryElements, MarketElement.class);
		
		return(query.getResultList());  //cast incerto, probabilmente darà eccezione
	}
}
