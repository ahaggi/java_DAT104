package no.hib.dat104;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		performTask(request, response);
	}

	private void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("TestServlet says hi<br/>");

		String action = request.getParameter("action");
		RequestDispatcher rd = request.getRequestDispatcher("Hello.jsp");
		if (action != null) {
			if ("include".equalsIgnoreCase(action)) {
				rd.include(request, response);
			} else if ("forward".equalsIgnoreCase(action)) {
				rd.forward(request, response);
			}
		}
//		rd.include(request, response);

		out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");

 
	}

}