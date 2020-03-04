package prg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/paamelding")
public class PaameldingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String navn = request.getParameter("navn");
		
		//Melder på, f.eks. ved å lagre greier i database!!!
		
		//Alt.1 - Sende data videre som parametre
//		response.sendRedirect("kvittering?navn=" + navn);
		
		//Alt.2 - Lagre data i såkalt sesjonsobjekt
		request.getSession().setAttribute("navn", navn);
		response.sendRedirect("kvittering");
	}

}
