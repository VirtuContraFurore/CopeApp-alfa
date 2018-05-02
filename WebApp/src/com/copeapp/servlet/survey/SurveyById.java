
package com.copeapp.servlet.survey;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.copeapp.dao.commons.UserDAO;
import com.copeapp.dto.commons.ExceptionDTO;
import com.copeapp.dto.commons.RoleDTO;
import com.copeapp.dto.survey.AnswerDTO;
import com.copeapp.dto.survey.SurveyDTO;
import com.copeapp.dto.survey.SurveyRequestByIdDTO;
import com.copeapp.dto.survey.SurveyRequestListDTO;
import com.copeapp.dto.survey.SurveyResponseByIdDTO;
import com.copeapp.entities.common.Role;
import com.copeapp.entities.common.User;
import com.copeapp.entities.survey.Answer;
import com.copeapp.entities.survey.Survey;
import com.copeapp.tomcat9Misc.EntityManagerFactoryGlobal;
import com.copeapp.utilities.HttpStatusUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/rest/surveybyid")
public class SurveyById extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ObjectMapper om = new ObjectMapper();
		User currentUser = UserDAO.selectByBasicAuthTokenException(request.getHeader("Authorization"));
		SurveyRequestByIdDTO surveyRequestById = om.readValue(request.getInputStream(), SurveyRequestByIdDTO.class);		

		EntityManager entitymanager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT s FROM surveys s WHERE (s.surveyId = :surveyId) order by date(s.closeSurveyDate) desc ", Survey.class);
		query.setParameter("surveyId", surveyRequestById.getSurveyId());
		Survey s = (Survey) query.getSingleResult();
		try {
			SurveyDTO surveyDTO;
			ArrayList<RoleDTO> surveyViewersRoles = new ArrayList<RoleDTO>(); //create viewersRoles
			RoleDTO tmp1 = new RoleDTO();
			for (Role r : s.getSurveyViewersRoles()) {
				BeanUtils.copyProperties(tmp1, r);
				surveyViewersRoles.add(tmp1);
			}
			ArrayList<RoleDTO> commonRole = new ArrayList<RoleDTO>(surveyViewersRoles);
			commonRole.retainAll(currentUser.getRoles());
			if (!commonRole.isEmpty()) {
				ArrayList<RoleDTO> surveyVotersRoles = new ArrayList<RoleDTO>();	//create votersRoles		
				RoleDTO tmp = new RoleDTO();
				for (Role r : s.getSurveyVotersRoles()) {
					BeanUtils.copyProperties(tmp, r);
					surveyVotersRoles.add(tmp);
				}
				ArrayList<AnswerDTO> answers = new ArrayList<AnswerDTO>(); //create answer list
				AnswerDTO tmp2 = new AnswerDTO();
				for (Answer an : s.getAnswers()) {
					BeanUtils.copyProperties(tmp2, an);
					answers.add(tmp2);
				}
				User u = (User) s.getInsertUser();	//al posto che lo user mando il username
				String username = u.getUsername();
				surveyDTO = new SurveyDTO(s.getSurveyId(), s.getQuestion(), s.getCloseSurveyDate(), 10, surveyViewersRoles, surveyVotersRoles, username , s.getAnswersNumber(), answers);	
			} else {
				response.setStatus(HttpStatusUtility.UNAUTHORIZED);
				surveyDTO = new SurveyDTO();	//TODO gestione della non visibilità
			}
			om.writeValue(response.getOutputStream(), new SurveyResponseByIdDTO(surveyDTO));
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		entitymanager.getTransaction().commit();
		entitymanager.close();
	}
}

