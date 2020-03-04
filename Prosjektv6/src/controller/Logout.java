package controller;

import static utility.URLMappings.LOGOUT_SERVLET;
import static utility.URLMappings.LOGIN_SERVLET;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utility.Authenticator;

@WebServlet("/" + LOGOUT_SERVLET)
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Authenticator.logout(request);
		response.sendRedirect(LOGIN_SERVLET);
	}
}
