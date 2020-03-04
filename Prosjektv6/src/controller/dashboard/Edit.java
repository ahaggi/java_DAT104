package controller.dashboard;

import static utility.URLMappings.*;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import EAO.EAO;
import model.Survey;
import utility.Authenticator;

/**
 * Edit Servlet. 
 * Aquires the survey to be edited and redirects to the correct JSP
 * 
 * 
 */
@WebServlet("/" + EDIT_SURVEY_SERVLET)
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	@EJB
	private EAO EAO;
	
	/**
	
	/**
	 * Uppdates the database survey.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session= request.getSession(false);
		
		if (request.getSession(false) != null && Authenticator.isAuthenticated(request, EAO.findLecturer((String) request.getSession(false).getAttribute("username")))) {
			Integer surveyId = Integer.parseInt(request.getParameter("surveyId"));
			Survey currentSurvey = EAO.findSurvey(surveyId);
			session.setAttribute("survey", currentSurvey);
			response.sendRedirect(EDITOR_SURVEY_SERVLET);
		}
		else{
			response.sendRedirect(LOGIN_SERVLET);
		}

		
		
	}
}
