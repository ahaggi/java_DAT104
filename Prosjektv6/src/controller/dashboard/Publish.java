package controller.dashboard;

import static utility.URLMappings.*;


import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EAO.EAO;
import model.Survey;
import utility.Authenticator;

/**
 * Servlet implementation class Publish
 */
@WebServlet("/" + PUBLISH_SURVEY_SERVLET)
public class Publish extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private EAO EAO;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession(false) != null && Authenticator.isAuthenticated(request, EAO.findLecturer((String) request.getSession(false).getAttribute("username")))) {
			Integer surveyID = Integer.parseInt(request.getParameter("surveyId"));
			Survey currentSurvey = EAO.findSurvey(surveyID);
			currentSurvey.publish();
			EAO.updateSurvey(currentSurvey);
			response.sendRedirect(DASHBOARD_SERVLET);
		}
		else{
			response.sendRedirect(LOGIN_SERVLET);
		}
		
	}

}
