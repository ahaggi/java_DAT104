package Kontroll;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import EAO.EAO;
import modell.*;
import static modell.UrlMappings.*;

/**
 * Servlet implementation class LogginnServlet
 */
@WebServlet("/" + PAAMELDING_SERVLET)
public class PaameeldingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private EAO EAO;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher(PAAMELDINGS_JSP).forward(request, response);
		HttpSession session = request.getSession(false);
		if (session != null)
			session.removeAttribute("ps");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		boolean altOK = true;
		Personer p = null; 

		/**
		 * OBS Vi kan ikke lage en EAO objekt inni i POJO, Så vi sender en
		 * ferdig lagt objekt til POJO "DEPENDENCY INJECTION"
		 */
		PersonSkjema ps = new PersonSkjema();
		ps.fyllPaaAttributter(request, EAO);
		
		//OBS isValidmobil vil bli utført 2 fullstendige ganger, inni 1.erAllgyldig (ikke sync),og den 2.LeggTilNyPerson(sync)
		altOK = ps.erAllInputGyldig() && LeggTilNyPerson(ps, p)!=null ; 
		
		if (altOK) {
			InnloggingUtil.loggInnSom(request, p.getmobil());
			session = request.getSession(false); 
			response.sendRedirect(PAAMELDINGS_BEKREFTELSE_SERVLET);
			
		} else {
			session.setAttribute("ps", ps);
			response.sendRedirect(PAAMELDING_SERVLET);
		}
	}

	/**En løsning for å sikkere at det ikke blir 2 tråder som prøver å lagre 2 deltakere med dat samme mob nr.
	 * OBS det fins andre løsninger f.eks. fange opp rollBack-unntakk og gi brukeren feilMelding
	 * @param ps
	 * @param p
	 * @return Personer
	 */
	public synchronized Personer LeggTilNyPerson(PersonSkjema ps, Personer p) {

		if (!ps.ErRegisterertFraFoer(EAO)) {
			p = new Personer(ps);
			EAO.leggTilPerson(p);
 		} 
		return p;

	}

}

