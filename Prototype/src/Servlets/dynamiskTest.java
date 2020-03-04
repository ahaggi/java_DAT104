package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dynamiskTest")
public class dynamiskTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Survey s;
	private Integer questionNr;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (s == null) {
			s = new Survey();
			questionNr = 0;
			GenererData.generer(s);
			request.getSession().setAttribute("survey", s);
		}
		
		request.getSession().setAttribute("questionNr", questionNr);
		
		request.getRequestDispatcher("WEB-INF/DynamiskTest.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stemme = request.getParameter("stemme");
		int stemmeNr = Integer.parseInt(stemme);
		
		s.getQuestions().get(questionNr).getAlternatives().get(stemmeNr).setAntallStemmer(s.getQuestions().get(questionNr).getAlternatives().get(stemmeNr).getAntallStemmer() + 1);
		questionNr++;
		
		if (questionNr >= s.getQuestions().size()) {
			questionNr = 0;
			response.sendRedirect("resultat");
		} else {
			response.sendRedirect("dynamiskTest");
		}
	}

}
