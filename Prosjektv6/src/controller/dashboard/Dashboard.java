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
import model.Lecturer;
import utility.Authenticator;

/**
 * Dashboard Servlet class. 
 * Redirects to the dashboard JSP
 */
@WebServlet("/" + DASHBOARD_SERVLET)
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// TODO Invalider eventuelle Surveys i session.
	@EJB
	private EAO EAO;

	/**
	 * Validates login state and redirects to the correct JSP
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession(false) != null && Authenticator.isAuthenticated(request, EAO.findLecturer((String) request.getSession(false).getAttribute("username")))) {

		String interviewHomeUrl = request.getRequestURL().substring(0, request.getRequestURL().length() - DASHBOARD_SERVLET.length()) + INTERVIEW_START_SERVLET + "?surveyId=";
		String username = (String) request.getSession().getAttribute("username");
		Lecturer l = EAO.findLecturer(username);
		
		request.getSession().invalidate(); //Dette er for � fjerne sesjonsobjekt som vi ikke vil ha
		
		HttpSession sesjon = request.getSession(true);
		
			sesjon.setAttribute("username", username);
			sesjon.setAttribute("surveys", l.getSurveys());
			sesjon.setAttribute("interviewHomeUrl", interviewHomeUrl);
			request.getRequestDispatcher(DASHBOARD_JSP).forward(request, response);
		} else {
			response.sendRedirect(LOGIN_SERVLET);
		}
		
		
	}

}


