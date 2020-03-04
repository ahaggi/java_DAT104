package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Survey;

@WebServlet("/InterviewStart")
public class InterviewStart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("questionNr") == null) {
			request.getSession().setAttribute("questionNr", 0);
		}
		
		request.getRequestDispatcher("WEB-INF/InterviewStart.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Survey survey = (Survey) request.getSession().getAttribute("survey");
		Integer questionNr = (Integer) request.getSession().getAttribute("questionNr");
		String stemme = request.getParameter("stemme");
		int stemmeNr = Integer.parseInt(stemme);
		
		survey.getQuestions().get(questionNr).getAlternatives().get(stemmeNr).setAntallStemmer(survey.getQuestions().get(questionNr).getAlternatives().get(stemmeNr).getAntallStemmer() + 1);
		questionNr++;
		
		if (questionNr >= survey.getQuestions().size()) {
			questionNr = 0;
			request.getSession().removeAttribute("questionNr");
			response.sendRedirect("InterviewFinish");
		} else {
			request.getSession().setAttribute("questionNr", questionNr);
			response.sendRedirect("InterviewStart");
		}
	}

}
