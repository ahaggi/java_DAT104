package no.hib.dat104;

import static no.hib.dat104.UrlMappings.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class Hjemmlig_Servlet
 */
@WebServlet("/"+HEMMELIG_SIDE_URL)
public class Hemmlig_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!InnloggingUtil.isInnlogget(request)) { 
            response.sendRedirect(LOGGIN_URL  + "?requiresLogin=102");
		}else{
			String username = InnloggingUtil.isInnloggetSom(request);
			 
			response.setContentType("text/html; charset=ISO-8859-1");

			PrintWriter out = response.getWriter();

			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset=\"ISO-8859-1\">");
			out.println("<title>Web Shop</title>");
			out.println("</head>");
			out.println("<body>");
			
			out.println("</form>");
			out.println("<form action=\"" + HEMMELIG_SIDE_URL + "\" method=\"post\">");
			out.println("<fieldset>");
			
 			out.println("<p> Du er logget in som :"+ "<strong>"+username +"</strong>"+"</p>");
 			out.println("<h3> Denne er hemmelig side!!!</h3>");
			out.println("<p><input type=\"submit\" value=\"Logg ut\" /></p>");

			out.println("</fieldset>");
			out.println("</form>");

 			
 			out.println("</body>");
			out.println("</html>");

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InnloggingUtil.loggUt(request);
		response.sendRedirect(LOGGIN_URL + "?requiresLogin=103");

	}

}
