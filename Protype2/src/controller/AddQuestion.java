package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Answer;
import model.Question;
import model.Survey;

@WebServlet("/AddQuestion")
public class AddQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String navn = request.getParameter("navn");
		String sporsmaal = request.getParameter("sporsmaal");
		String alternativ1 = request.getParameter("alternativ1");
		String alternativ2 = request.getParameter("alternativ2");
		Survey survey;
		
		if (request.getSession().getAttribute("survey") == null) {
			survey = new Survey();
		} else {
			survey = (Survey) request.getSession().getAttribute("survey");
		}
		
		if (navn != null) {
			 survey.setNavn(navn);
		}
		
		List<Answer> answers = new ArrayList<Answer>();
		answers.add(new Answer(alternativ1, 0));
		answers.add(new Answer(alternativ2, 0));
		
		Question question = new Question(sporsmaal, answers);
		
		survey.getQuestions().add(question);
		
		request.getSession().setAttribute("survey", survey);
		
		response.sendRedirect("Create");
		
	}

}
