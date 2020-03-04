package prg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/kvittering")
public class KvitteringsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Alt.1 - Hente ut navnet som parameter fra redirect-URLen
//		String navn = request.getParameter("navn");
		
		//Alt.2 - Hente navnet fra sesjonsobjektet
		String navn = (String) request.getSession().getAttribute("navn");
		
		response.setContentType("text/plain");
		
		PrintWriter out = response.getWriter();
		
		out.println("Du er herved påmeldt, " + navn);

	}

}
