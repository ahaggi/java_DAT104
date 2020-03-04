package controller.dashboard;
import static utility.URLMappings.DASHBOARD_SERVLET;
import static utility.URLMappings.DELETE_SURVEY_SERVLET;
import static utility.URLMappings.LOGIN_SERVLET;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EAO.EAO;
import utility.Authenticator;

/**
 * Deletes a survey from DB.
 * Servlet implementation class Delete
 */
@WebServlet("/"+ DELETE_SURVEY_SERVLET)
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private EAO EAO;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession(false) != null && Authenticator.isAuthenticated(request, EAO.findLecturer((String) request.getSession(false).getAttribute("username")))) {
			
			String username = (String) request.getSession().getAttribute("username");
			String sid = request.getParameter("surveyId");
			
			int surveyId = Integer.parseInt(sid);
//			if (surveyId < 0) {
//				// ID is not a valid number!!!
//			} else {
				EAO.deleteSurvey(surveyId, username);
//			}
			
			response.sendRedirect(DASHBOARD_SERVLET);
		} else {
			response.sendRedirect(LOGIN_SERVLET);
		}
	}

}
