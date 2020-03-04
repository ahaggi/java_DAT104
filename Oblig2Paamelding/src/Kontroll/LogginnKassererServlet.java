package Kontroll;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringEscapeUtils;

import modell.InnloggingUtil;

import static modell.UrlMappings.*;
/**
 * Servlet implementation class LogginnServlet
 * 
 * Login side til Kasserer. 
 */
@WebServlet("/"+LOGGINN_KASSERER_SERVLET)
public class LogginnKassererServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// hent eventuelle innlogging-feilmelding og sett de i req.setpatameter men ikke lag en ny session
		
		request.getRequestDispatcher(LOGGINN_KASSERER_JSP).forward(request, response);
		HttpSession session= request.getSession(false);
		session.removeAttribute("feilMelding");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String passord_xml=getServletContext().getInitParameter("passord");
		String passord= StringEscapeUtils.escapeHtml4(request.getParameter("password"));
		

		if (passord_xml.equals(passord)) {
			
			InnloggingUtil.loggInnSom(request, "kasserer");
			response.sendRedirect(BETALINGSOVERSIKT_SERVLET);
		}else{
			HttpSession session= request.getSession(false);
			session.setAttribute("feilMelding", "Manglende eller ugyldig brukernavn");
			response.sendRedirect(LOGGINN_KASSERER_SERVLET);
			
		}
	}
	


}
