package controller.editor;

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
import model.question.TextQuestion;
import utility.AddToSurvey;
import utility.Authenticator;

import static utility.URLMappings.*;

/**
 * 
 * Adds a question to a Survey.
 * @see MultipleChoiceQuestion, TextQuestion
 */
@WebServlet("/AddQuestion")
public class AddQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private EAO EAO;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession(false) != null && Authenticator.isAuthenticated(request, EAO.findLecturer((String) request.getSession(false).getAttribute("username")))) {
			AddToSurvey.add(request);
			String questionType = request.getParameter("questionType");
			Survey survey = (Survey) request.getSession().getAttribute("survey");
			List<Question> questions = survey.getQuestions();
			
			if (questionType.equals("mcQuestion")) {
				MultipleChoiceQuestion question = new MultipleChoiceQuestion();
				questions.add(question);
			} else if (questionType.equals("textQuestion")) {
				TextQuestion question = new TextQuestion();
				questions.add(question);
			}

			request.getSession().setAttribute("autoFocus", -1);
			request.getSession().setAttribute("survey", survey);
			response.sendRedirect(EDITOR_SURVEY_SERVLET);
		} else {
			response.sendRedirect(LOGIN_SERVLET);
		}
	}

}
