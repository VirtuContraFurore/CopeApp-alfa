package com.copeapp.servlet.market;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.copeapp.dao.commons.UserDAO;
import com.copeapp.dto.commons.ExceptionDTO;
import com.copeapp.dto.market.CreateMarketRequestDTO;
import com.copeapp.entities.common.User;
import com.copeapp.entities.market.Market;
import com.copeapp.entities.market.MarketElement;
import com.copeapp.tomcat9Misc.StartupOperations;
import com.copeapp.utilities.HttpStatusUtility;
import com.copeapp.utilities.ObjectsValidationUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.BeanUtil;

@WebServlet("/rest/createmarket")
public class CreateMarket extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public CreateMarket() {
        super();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ObjectMapper objMap = new ObjectMapper();
		EntityManager entitymanager = StartupOperations.emfactory.createEntityManager();
		try {
			CreateMarketRequestDTO createMarketRequest = objMap.readValue(request.getInputStream(), CreateMarketRequestDTO.class);
			if (!ObjectsValidationUtility.validateNotNullParameters(createMarketRequest)) {
				response.setStatus(HttpStatusUtility.unauthorized);
				ExceptionDTO errorResponse = new ExceptionDTO(null, HttpStatusUtility.unauthorized, "Errore interno all'applicazione", "La richiesta è ben formatta ma presenta alcuni attributi nulli (che non devono esserlo)");
				objMap.writeValue(response.getOutputStream(), errorResponse);
			}else {
				entitymanager.getTransaction().begin();
				
				User user = UserDAO.selectById(createMarketRequest.getCreatorId());
				Market market = new Market();
				market.setName(createMarketRequest.getName());
				market.setDescription(createMarketRequest.getDescription());
				market.setCreationDate(createMarketRequest.getCreationDate());
				market.setEliminationDate(null);
				market.setOpenDate(createMarketRequest.getOpenDate());
				market.setExpireDate(createMarketRequest.getExpireDate());
				market.setVisibleDate(createMarketRequest.getVisibleDate());
				market.setHiddenDate(createMarketRequest.getHiddenDate());
				market.setCreator(user);
				market.setEliminator(null);
				market.setMarketElements(MarketElementDao.getElementsListByIdsException(createMarketRequest.getMarketElementsIds()));

				entitymanager.persist(market);
				entitymanager.getTransaction().commit();
				entitymanager.close();
			}
		} catch (NoResultException nre) {
			ExceptionDTO errorResponse = new ExceptionDTO(nre.getStackTrace(), HttpStatusUtility.unauthorized, "Errore nella creazione del Market", "Id utente creatore oppure id di uno dei market elements errati");
			response.setStatus(HttpStatusUtility.unauthorized);
			objMap.writeValue(response.getOutputStream(), errorResponse);
		
		} catch (Exception ex) {
			ExceptionDTO errorResponse = new ExceptionDTO(ex.getStackTrace(), HttpStatusUtility.internalServerError, "Errore interno all'applicazione", "Avvenuto errore non previsto");
			response.setStatus(HttpStatusUtility.internalServerError);
			objMap.writeValue(response.getOutputStream(), errorResponse);
		
		} finally {
			entitymanager.close();
		}
	}

}
