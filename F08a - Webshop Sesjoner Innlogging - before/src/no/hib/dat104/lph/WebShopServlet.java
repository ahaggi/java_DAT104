package no.hib.dat104.lph;

import static no.hib.dat104.lph.UrlMappings.LOGOUT_URL;
import static no.hib.dat104.lph.UrlMappings.WEBSHOP_URL;
import static no.hib.dat104.lph.UrlMappings.LOGIN_URL;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.sun.org.apache.xerces.internal.util.HTTPInputSource;

@WebServlet("/" + WEBSHOP_URL)
public class WebShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Hent sesjon men retuner null hvis sesjonen ikke finns 
		HttpSession sesion = request.getSession(false);

		if (sesion== null || sesion.getAttribute("username")  == null) {// itilfelle man skriver http://localhost:8080/f08a/webshop uten � logge inn
            response.sendRedirect(LOGIN_URL + "?requiresLogin=102");
		}else{
			/**Hvis bruker er innlogget*/
			//legg merke til at vi m�tte teste om sesjonen != null

			// Inn noe kode her i forbindelse med uthenting av sesjonsdata?

			String username = (String) sesion.getAttribute("username");
			Cart cart = (Cart) sesion.getAttribute("cart");

			response.setContentType("text/html; charset=ISO-8859-1");

			PrintWriter out = response.getWriter();

			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset=\"ISO-8859-1\">");
			out.println("<title>Web Shop</title>");
			out.println("</head>");
			out.println("<body>");

			// Inn noe kode her for "Du er innlogget som <bruker>"?
			out.println("<p> Du er logget in som username "+ username +"</p>");

			out.println("Din handlekurv:<br />");
			out.println("<table border=\"1\">");
			out.println("<tr><th>Vare</th><th>Pris</th></tr>");

			// Inn noe kode her for � vise innhold i handlevogn:
			for (CartItem item : cart.getItems()) {
				out.println("<tr><td>" + item.getName() + "</td>"
						+ "<td align=\"right\">" + item.getPrice() + "</td></tr>");
			}

			out.println("</table><br/>");
			out.println("<form action=\"" + WEBSHOP_URL + "\" method=\"post\">");
			out.println("<fieldset>");
			out.println("<legend>Handle</legend>");
			out.println("<input type=\"checkbox\" name=\"varer\" value=\"Bukse\" />Bukse<br/>");
			out.println("<input type=\"checkbox\" name=\"varer\" value=\"Genser\" />Genser<br/>");
			out.println("<p><input type=\"submit\" value=\"Legg i handlekurv\" /></p>");
			out.println("</fieldset>");
			out.println("</form>");
			out.println("<form action=\"" + LOGOUT_URL + "\" method=\"get\">");
			out.println("<fieldset>");
			out.println("<p><input type=\"submit\" value=\"Logg ut\" /></p>");
			out.println("</fieldset>");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
		}//else
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Inn noe kode her i forbindelse med autorisasjon?
		// Inn noe kode her i forbindelse med oppdatering av sesjonsdata?

		HttpSession sesion = request.getSession(false);
		if (sesion!= null && request.getParameterValues("varer") != null) { 
			//legg merke til at vi m�tte teste om sesjonen != null
			//itilfellet sesjonen er lik null, vil req bli sendt til WEBSHOP_URL som vil sender den videre til LOGIN_URL

//            Map<String, String[]> params = request.getParameterMap();  // OBS et map med alle parameters,, Hver parameter vil ha tabell med mulige values

			Cart cart = (Cart) sesion.getAttribute("cart"); //ikke lage en ny cart
			String[] items= request.getParameterValues("varer");

			for (String ItemNavn : items) {
				CartItem item= new CartItem(ItemNavn, 100.);
				cart.addItem(item);
			}

		}
		response.sendRedirect(WEBSHOP_URL);


	}
}
