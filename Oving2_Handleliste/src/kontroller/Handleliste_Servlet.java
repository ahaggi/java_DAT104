package kontroller;

import static kontroller.UrlMappings.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;

import EAO.EAO;
import modell.Cart;
import modell.Item;


/**
 * Servlet implementation class Handlelisten_Servlet
 */
@WebServlet("/"+HANDLEL_LISTE_JPA_URL)
public class Handleliste_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private EAO EAO;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cart cart = EAO.alleItems();

		response.setContentType("text/html; charset=ISO-8859-1");

		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>handleliste</title>");

		out.println("</head>");
		out.println("<body>");

		out.println("<form  action=\""+HANDLEL_LISTE_JPA_URL+"\"  method=\"post\" >");
		out.println("<fieldset>");

		out.println("<p><input type=\"submit\" value=\"Legg til\"  />");
		out.println(" <input type=\"text\" name=\"tekst_felt\"  required/></p>");
		out.println("<input type=\"hidden\" name =\"handelse\" value=\"leggTil\" />");

		out.println("</fieldset>");
		out.println("</form>");

		for (Item ItemNavn : cart.getItems()) {

			out.println("<form  action=\""+HANDLEL_LISTE_JPA_URL+"\"  method=\"post\" >");

			out.println("<p><input type=\"submit\" value=\"Slett\"  />");
			out.println(" " + ItemNavn.getItem_navn() + "</p>");
			out.println("<input type=\"hidden\" name =\"handelse\" value=\"slett\"/>");
			out.println("<input type=\"hidden\" name =\"itemId\" value=\"" + ItemNavn.getItem_id() + "\"/>");

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

		Cart cart = EAO.alleItems()   ;
		
		boolean leggTil = "leggTil".equals(request.getParameter("handelse"));
		boolean slett = "slett".equals(request.getParameter("handelse"));
 
		if (cart != null) {

			if (leggTil) {
				String itemNavn =StringEscapeUtils.escapeHtml4( request.getParameter("tekst_felt"));
				Item item = new Item(itemNavn);
				EAO.leggTilItem(item);
			}else if (slett){
				EAO.slettItem(request.getParameter("itemId"));
			}

		}


		response.sendRedirect(HANDLEL_LISTE_JPA_URL);

	}

}
