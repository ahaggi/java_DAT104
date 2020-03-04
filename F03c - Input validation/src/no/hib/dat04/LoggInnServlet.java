package no.hib.dat04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logginn")
public class LoggInnServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		ValidatorRegExp validator = new ValidatorRegExp();
		
		String studentid = req.getParameter("studentid");
		
		String responsmelding;
		if (validator.isValidStudentId(studentid)) {
			responsmelding = "Johooo!";
		} else {
			responsmelding = "Ugyldig student-id";
		}
		
	    resp.setContentType("text/plain; charset=ISO-8859-1");
        PrintWriter out = resp.getWriter();
        out.println(responsmelding);
		
	}

}
