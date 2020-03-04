package no.hib.dat104;


import static no.hib.dat104.UrlMappings.TEMPRATUR_SERVLET_URL;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class HilseServlet
 */
@WebServlet("/"+TEMPRATUR_SERVLET_URL)
public class CelsiusTilFahrenheitServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	String serverinfo;
	
	
	
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		serverinfo =  getServletContext().getServerInfo();
	}
	
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		String valg = request.getParameter("valg");
		String tempInndata = request.getParameter("temp").replaceAll(",", ".");
		String res="";
		
		if (Regnom.validate(tempInndata, valg)){
			double tempRes= Regnom.regn(tempInndata, valg);
			 res="<h3> "+ Regnom.Formater(tempInndata, valg, tempRes) +"</h3>";
		}else{
			res="<h3 style=\"color:red\">Ugyldig brukerinput. Temperaturen må være et tall (lik eller over det absolutte nullpunkt). Pass også på at du har valgt en av omregningene før du trykker \"Regn om\"</h3>";
		}
		
		
		response.setContentType("text/html; charset=ISO-8859-1");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>Handle</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Tempraturen omregning resulatat</h1>");
		out.println(res);
		out.println("<p >Info om serveren: " + serverinfo + ".</p>");
		out.println("<a href=\"index.html\">En gang til</a>");
		out.println("</body>");
		out.println("</html>");
	}

	



}
