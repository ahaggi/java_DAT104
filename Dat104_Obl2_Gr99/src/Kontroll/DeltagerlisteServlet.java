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

import EAO.EAO;
import modell.InnloggingUtil;
import modell.Personer;

import static modell.UrlMappings.*;
/**
 * Servlet implementation class LogginnServlet
 */
@WebServlet("/"+DELTAGERLISTE_SERVLET)
public class DeltagerlisteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private EAO EAO;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);

		if (InnloggingUtil.isInnlogget(request)) {

			List<Personer> personerListe = EAO.allePersoner();
			session.setAttribute("personerListe", personerListe);
			request.getRequestDispatcher(DELTAGELISTE_JSP).forward(request, response);
		}else{
			session= request.getSession(true);
			session.setAttribute("feilMelding", "Forespørselen din krever pålogging som admin. (Du kan ha blitt logget ut automatisk)");
			response.sendRedirect(LOGGINN_DELTAGERE_SERVLET);
		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
  		//Kunne godt  logge ut her i stedenfor loggutServlet
	}

}
