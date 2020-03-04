package no.hib.dat104;

import static no.hib.dat104.UrlMappings.*;
import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class Favorittfrukt
 */
@WebServlet("/"+FAVORITTFRUKT_SERVLET)
public class Favorittfrukt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static HashMap<String, Integer> fruktMap=Stemmeskjema.fruktMap;




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String valg = request.getParameter("valg");
		
		synchronized (valg) {
			Integer i=fruktMap.get(valg)+1;
			fruktMap.put(valg, i);
		}
		
		response.sendRedirect(FAVORITTFRUKT_SERVLET);
	}




	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html; charset=ISO-8859-1");

		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>Favorittfrukt resultat</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Favorittfrukt resultat</h1>");
		
		for (String fruktnavn:fruktMap.keySet()) {
			out.println("<p>"+ fruktnavn +": " +fruktMap.get(fruktnavn)  +"</p>");

 		}
		out.println("<a href=\""+STEMMESKJEMA_SERVLET+"\">Stem en gang til</a>");
		out.println("</body>");
		out.println("</html>");


	}
	
	
}
