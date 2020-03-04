package no.hib.dat104.f03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/processInput")
public class ProcessInputServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    response.setContentType("text/plain; charset=ISO-8859-1");

        PrintWriter out = response.getWriter();
        
	    out.println("Mottatte input-parametre:");
	    Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
		    String pName = parameterNames.nextElement();
		    String[] pValues = request.getParameterValues(pName);
		    out.println("  " + pName + " = " + Arrays.toString(pValues));
		}
	}

}
