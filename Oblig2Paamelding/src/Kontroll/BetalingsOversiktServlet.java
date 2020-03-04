package Kontroll;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import EAO.*;
import modell.*;

import static modell.UrlMappings.*;
/**
 * Servlet implementation class LogginnServlet
 */
@WebServlet("/"+BETALINGSOVERSIKT_SERVLET)
public class BetalingsOversiktServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private EAO EAO;
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session= request.getSession(false);
		
/**
 * Kontrollerer om kasserer har skrivet inn riktig passord.
 * 
 * @return betalingsoversikt dersom passord er riktig.
 * 	
 */
		
		if (InnloggingUtil.isInnloggetSomKasserer(request)) {
			
			List<Personer> personerListe = EAO.allePersoner();
			session.setAttribute("personerListe", personerListe);

			request.getRequestDispatcher(BETALINGSOVERSIKT_JSP).forward(request, response);
		}else{
			session= request.getSession(true);
			session.setAttribute("feilMelding", "Forespørselen din krever pålogging som admin. (Du kan ha blitt logget ut automatisk)");
			response.sendRedirect(LOGGINN_KASSERER_SERVLET);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	/**
	 * Kontrollerer om kasserer har skrivet inn riktig passorder logget in.
	 * @return refresher betalingsoversikt etter å ha oppdatert en betaling.
	 * 	
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (InnloggingUtil.isInnloggetSomKasserer(request)) {
			String mobil=request.getParameter("mobilNr");
			Personer p = EAO.finnPerson(mobil);
			if (p!=null) {
				p.setBetalingStatus(true);
				EAO.oppdaterPerson(p);
			}
			response.sendRedirect(BETALINGSOVERSIKT_SERVLET);
		}else{
			HttpSession session= request.getSession(false);
			session.setAttribute("feilMelding", "Forespørselen din krever pålogging som admin. (Du kan ha blitt logget ut automatisk)");
			response.sendRedirect(LOGGINN_KASSERER_SERVLET);

		}


	}

}
