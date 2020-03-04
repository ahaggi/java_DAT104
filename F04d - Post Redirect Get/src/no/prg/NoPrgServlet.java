package no.prg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/paameldingUtenPrg")
public class NoPrgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String navn = request.getParameter("navn");
		
		response.setContentType("text/plain");
		
		PrintWriter out = response.getWriter();
		
		out.println("Du er herved påmeldt, " + navn);
	}
	
       
}
