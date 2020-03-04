package Kontroll;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringEscapeUtils;

import EAO.EAO;
import modell.InnloggingUtil;
import modell.Personer;

import static modell.UrlMappings.*;
/**
 * Servlet implementation class LogginnServlet
 */
@WebServlet("/"+LOGGINN_DELTAGERE_SERVLET)
public class LogginnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private EAO EAO;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		request.getRequestDispatcher(LOGGINN_DELTAGERE_JSP).forward(request, response);
		HttpSession session= request.getSession(false);
		session.removeAttribute("feilMelding");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mobil = StringEscapeUtils.escapeHtml4(request.getParameter("mobil"));
		mobil = Personer.getMobilFormatert(mobil);

		Personer p = EAO.finnPerson(mobil);
		HttpSession session = request.getSession(false);

		if (p != null) {// hvis den er deltager

			InnloggingUtil.loggInnSom(request, p.getmobil());
			session = request.getSession(false); 
 			
			response.sendRedirect(DELTAGERLISTE_SERVLET);
			
		} else {// hvis passord ikke fins i databasen
			session.setAttribute("feilMelding", "Manglende eller ugyldig brukernavn");
			response.sendRedirect(LOGGINN_DELTAGERE_SERVLET);
		}
	}

}
