package no.hib.dat104;

import static no.hib.dat104.UrlMappings.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;


/**
 * Servlet implementation class Loginn_Servlet
 */
@WebServlet("/"+LOGGIN_URL)
public class Logginn_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		
		boolean requiresLoginRedirect = request.getParameter("requiresLogin") != null;
        String beskjedKode= request.getParameter("requiresLogin");

        response.setContentType("text/html; charset=ISO-8859-1");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"ISO-8859-1\">");
        out.println("<title>Login</title>");
        out.println("</head>");
        out.println("<body>");
        
        if (requiresLoginRedirect) {
        	if ("101".equals(beskjedKode)) {
                out.println("<p><font color=\"red\">" +"Manglende eller ugyldig brukernavn</font></p>");

        	}else if ("102".equals(beskjedKode)) {
                out.println("<p><font color=\"red\">" +"Forespørselen din krever pålogging. " +
                        "(Du kan ha blitt logget ut automatisk)</font></p>");

            }else if ("103".equals(beskjedKode)) {
                out.println("<p> Du er blitt logget ut</p>");

            }
        } 
        
        out.println("<form action=\"" + LOGGIN_URL + "\" method=\"post\">");
        out.println("  <fieldset>");
        out.println("    <legend>Loggin</legend>");
        out.println("    <p>Navn: <input type=\"text\" name=\"brukernavn\"  required/></p>");
        out.println("    <p><input type=\"submit\" value=\"Logg inn\" /></p>");
        out.println("  </fieldset>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String brukernavn = StringEscapeUtils.escapeHtml4(  request.getParameter("brukernavn")  );

        if (!InnloggingUtil.isGyldigBrukernavn(brukernavn)) {
            response.sendRedirect(LOGGIN_URL + "?requiresLogin=101");
        } else {

        	InnloggingUtil.loggInnSom(request, brukernavn);
            response.sendRedirect(HEMMELIG_SIDE_URL);
        }
		
	}

}
