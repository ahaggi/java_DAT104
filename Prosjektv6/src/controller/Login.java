package controller;


import static utility.URLMappings.DASHBOARD_SERVLET;
import static utility.URLMappings.LOGIN_JSP;
import static utility.URLMappings.LOGIN_SERVLET;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import EAO.EAO;
import utility.Authenticator;

/**
 * Servlet implementation class Login
 * Handles the login requests. 
 * doGet serves the log in page.
 * doPost checks the requests and redirects.
 */
@WebServlet("/"+ LOGIN_SERVLET)
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private EAO EAO;

	/**
	 * Responsible for serving the log in page to a user
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession(false) != null && Authenticator.isAuthenticated(request, EAO.findLecturer((String) request.getSession(false).getAttribute("username")))) {
			response.sendRedirect(DASHBOARD_SERVLET);
		} else {
			request.getRequestDispatcher(LOGIN_JSP).forward(request, response);
			request.getSession().removeAttribute("errorMsg");
		}

	}

	/**
	 * Checks if the login was a success or not.
	 * Redirects back to the log in page if it was not.
	 * Logs user in and redirects to dashboard if it was a success. 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Sett opp n�dvendige ellementer
		HttpSession session = request.getSession(true);
		String username = request.getParameter("username");
			
		if (Authenticator.login(request, EAO.findLecturer(username))){
			response.sendRedirect(DASHBOARD_SERVLET);
			
		} else {// hvis brukenavn eller passord ikke fins i databasen
			session.setAttribute("errorMsg", "Wrong username or password.");// Eller noe fornuftige beskjed
			response.sendRedirect(LOGIN_SERVLET);
		}

}
}
