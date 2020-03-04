package controller.editor;

import static utility.URLMappings.EDITOR_SURVEY_SERVLET;
import static utility.URLMappings.LOGIN_SERVLET;

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
 * Removes an option from a MultipleChoiceQuestion.
 * 
 */
@WebServlet("/RemoveOption")
public class RemoveOption extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private EAO EAO;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession(false) != null && Authenticator.isAuthenticated(request, EAO.findLecturer((String) request.getSession(false).getAttribute("username")))) {
			AddToSurvey.add(request);
			String removeOption = request.getParameter("removeOption");
			String[] splitRemoveOption = removeOption.split(" ");
			int optionIndex = Integer.parseInt(splitRemoveOption[0]) - 1; //funker ikke om det er option 10 eller mer
			int questionIndex = Integer.parseInt(splitRemoveOption[1]) - 1;
			
			Survey survey = (Survey) request.getSession().getAttribute("survey");
			List<Question> questions = survey.getQuestions();
			MultipleChoiceQuestion question = (MultipleChoiceQuestion) questions.get(questionIndex);
			
			question.getOptions().remove(optionIndex);
			
			request.getSession().setAttribute("autoFocus", questionIndex); //funker ikke om du remover den siste optionen
			request.getSession().setAttribute("survey", survey);
			response.sendRedirect(EDITOR_SURVEY_SERVLET);
		} else {
			response.sendRedirect(LOGIN_SERVLET);
		}
	}

}
