package no.hib.dat104.lph;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hei")
public class HeiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String melding = "Dette er en melding";

		request.setAttribute("melding", melding);

		request.getRequestDispatcher("Hei.jsp").forward(request, response);
	}

}
