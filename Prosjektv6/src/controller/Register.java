package controller;

import static utility.URLMappings.LOGIN_SERVLET;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EAO.EAO;
import model.Lecturer;
import utility.Authenticator;
import utility.PassordUtil;
import utility.Validator;

/**
 * Servlet implementation class Register Handles registering for the survey
 * system. doGet serves the log in page. doPost checks the requests and
 * redirects.
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private EAO EAO;

	/**
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession(false) != null && Authenticator.isAuthenticated(request, EAO.findLecturer((String) request.getSession(false).getAttribute("username")))) {
			request.getRequestDispatcher("WEB-INF/Register.jsp").forward(request, response);
			request.getSession().removeAttribute("errorRegister");
			request.getSession().removeAttribute("errorRegisterColor");
		} else {
			response.sendRedirect(LOGIN_SERVLET);
		}
	}

	/**
	 * Tries to register the user. Validates the data, checks if it already
	 * exists and gives feedback if the query is successful.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession(false) != null && Authenticator.isAuthenticated(request, EAO.findLecturer((String) request.getSession(false).getAttribute("username")))) {
			String username = request.getParameter("registerUsername");
			String password = request.getParameter("registerPassword");
			PassordUtil pu = new PassordUtil();

			if (Validator.isValidUsername(username) && Validator.isValidPassword(password)) {
				Lecturer l = new Lecturer(username, pu.krypterPassord(password));

				if (EAO.findLecturer(username) == null) {
					EAO.addLecturer(l);
					request.getSession().setAttribute("errorRegister", "User successfully registered!");
					request.getSession().setAttribute("errorRegisterColor", "green");
				} else {
					request.getSession().setAttribute("errorRegister", "User already exists!");
					request.getSession().setAttribute("errorRegisterColor", "red");
				}

			} else {
				request.getSession().setAttribute("errorRegister", "Invalid username or password!");
				request.getSession().setAttribute("errorRegisterColor", "red");
			}

			response.sendRedirect("register");
		} else {
			response.sendRedirect(LOGIN_SERVLET);
		}

	}

}
