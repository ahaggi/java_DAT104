package controller.editor;

import static utility.URLMappings.*;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EAO.EAO;
import model.Survey;
import model.question.MultipleChoiceQuestion;
import model.question.Question;
import utility.AddToSurvey;
import utility.Authenticator;

/**
 * Adds an option to a MultipleChoiceQuestion.
 * @see MultipleChoiceQuestion
 */
@WebServlet("/AddOption")
public class AddOption extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private EAO EAO;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession(false) != null && Authenticator.isAuthenticated(request, EAO.findLecturer((String) request.getSession(false).getAttribute("username")))) {
			AddToSurvey.add(request);
			String addOption = request.getParameter("addOption");
			int questionIndex = Integer.parseInt(addOption.substring(23)) - 1;
			
			Survey survey = (Survey) request.getSession().getAttribute("survey");
			List<Question> questions = survey.getQuestions();
			MultipleChoiceQuestion question = (MultipleChoiceQuestion) questions.get(questionIndex);
			List<String> options = question.getOptions();
			
			options.add("");
			
			request.getSession().setAttribute("autoFocus", questionIndex);
			request.getSession().setAttribute("survey", survey);
			response.sendRedirect(EDITOR_SURVEY_SERVLET);
		} else {
			response.sendRedirect(LOGIN_SERVLET);
		}
	}

}
