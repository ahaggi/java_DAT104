package no.hib.dat104.lph;

import static no.hib.dat104.lph.UrlMappings.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//@WebServlet("/" + LOGIN_URL)
public class LoggInnServlet_lagFeilMeldingISesjonAttr extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


//		response.setContentType("text/html; charset=ISO-8859-1");
//
//		PrintWriter out = response.getWriter();
//
//		out.println("<!DOCTYPE html>");
//		out.println("<html>");
//		out.println("<head>");
//		out.println("<meta charset=\"ISO-8859-1\">");
//		out.println("<title>Login</title>");
//		out.println("</head>");
//		out.println("<body>");

		// Inn noe kode her i forbindelse med evt. feilmeldinger?
		// Generer en feil melding 
		HttpSession sesion = request.getSession(true);
		String feilMelding = (String) sesion.getAttribute("feilmelding");
		feilMelding=(feilMelding==null)?"":feilMelding;
		
		sesion.removeAttribute("feilmelding");//så den ikke henger 
		
//		 out.println("<h3 style=color:red> "+ feilMelding+"</h3>");
//
//		out.println("<form action=\"" + LOGIN_URL + "\" method=\"post\">");
//		out.println("  <fieldset>");
//		out.println("    <legend>Login</legend>");
//		out.println("    <p>Navn: <input type=\"text\" name=\"username\" /></p>");
//		out.println("    <p><input type=\"submit\" value=\"Logg inn\" /></p>");
//		out.println("  </fieldset>");
//		out.println("</form>");
//		out.println("</body>");
//		out.println("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Inn noe kode her i forbindelse med innlogging av bruker?
//		String username = request.getParameter("username");
//		
//		if (username == null || !username.equals("aaa")) { 
			HttpSession sesion = request.getSession(true);
			sesion.setAttribute("feilmelding", "Melgende eller ugyldig brukenavn");
			response.sendRedirect(LOGIN_URL);

//		} else {
//			// Inn noe kode her i forbindelse med oppretting av sesjonsdata?
//			
//			/***/
//			//Hvis det fins en sesjon, invalidate.   
//			HttpSession sesion = request.getSession(false);
//			if (sesion != null) {
//				sesion.invalidate();
//			} // Den er lik sesion=request.getSession(true); sesion.invalidate();
//			/***/
//
//			sesion = request.getSession(true);// lag en nye.
//			sesion.setAttribute("username", username);
//			sesion.setAttribute("cart", new Cart()); // OBS! vi må lage en ny cart her 
//			response.sendRedirect(WEBSHOP_URL);
//		}
	}
}
