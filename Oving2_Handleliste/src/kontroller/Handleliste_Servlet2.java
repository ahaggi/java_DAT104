package kontroller;


import static kontroller.UrlMappings.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringEscapeUtils;

import modell.Cart;
import modell.Item;


/**
 * Servlet implementation class Handlelisten_Servlet
 */
@WebServlet("/"+HANDLEL_LISTE_SASJON_URL)
public class Handleliste_Servlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sesjon = request.getSession(true);
		Cart cart = (Cart) sesjon.getAttribute("cart");

		if (cart == null) {
			cart = new Cart();
			sesjon.setAttribute("cart", cart);
		}

		response.setContentType("text/html; charset=ISO-8859-1");

		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>handleliste</title>");

		out.println("</head>");
		out.println("<body>");

		out.println("<form  action=\""+HANDLEL_LISTE_SASJON_URL+"\"  method=\"post\" >");
		out.println("<fieldset>");

		out.println("<p><input type=\"submit\" value=\"Legg til\"  />");
		out.println(" <input type=\"text\" name=\"tekst_felt\"  required/></p>");
		out.println("<input type=\"hidden\" name =\"handelse\" value=\"leggTil\" />");

		out.println("</fieldset>");
		out.println("</form>");

		for (Item ItemNavn : cart.getItems()) {

			out.println("<form  action=\""+HANDLEL_LISTE_SASJON_URL+"\"  method=\"post\" >");

			out.println("<p><input type=\"submit\" value=\"Slett\"  />");
			out.println(" " + ItemNavn.getItem_navn() + "</p>");
			out.println("<input type=\"hidden\" name =\"handelse\" value=\"slett\"/>");
			out.println("<input type=\"hidden\" name =\"itemNavn\" value=\"" + ItemNavn.getItem_navn() + "\"/>");

			out.println("</form>");

		}

		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sesion = request.getSession(false);
		Cart cart = (Cart) sesion.getAttribute("cart"); // ikke lage en ny cart
		
		boolean leggTil = "leggTil".equals(request.getParameter("handelse"));
		boolean slett = "slett".equals(request.getParameter("handelse"));

		if (sesion != null && cart != null) {

			if (leggTil) {
				String itemNavn =StringEscapeUtils.escapeHtml4( request.getParameter("tekst_felt"));
				Item item = new Item(itemNavn);
				cart.leggTilItem(item);
			}else if (slett){
				cart.slettItem_navn(request.getParameter("itemNavn"));
			}

		}


		response.sendRedirect(HANDLEL_LISTE_SASJON_URL);

	}

}
