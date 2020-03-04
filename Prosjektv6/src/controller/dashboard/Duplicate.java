package controller.dashboard;

import static utility.URLMappings.*;

import java.io.IOException;
import java.util.ArrayList;
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
import utility.Authenticator;

/**
 * Duplicate Servlet
 * Duplicates an existing survey
 */
@WebServlet("/" + DUPLICATE_SURVEY_SERVLET)
public class Duplicate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private EAO EAO;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession(false) != null && Authenticator.isAuthenticated(request, EAO.findLecturer((String) request.getSession(false).getAttribute("username")))) {
			Integer surveyId = Integer.parseInt(request.getParameter("surveyId"));
			Survey currentSurvey = EAO.findSurvey(surveyId);
			Survey newSurvey = new Survey();
			
			newSurvey.setName(currentSurvey.getName());
			newSurvey.setDescription(currentSurvey.getDescription());
			newSurvey.setRepeatable(currentSurvey.getRepeatable());
			newSurvey.setTraversable(currentSurvey.getTraversable());
			newSurvey.setLength(currentSurvey.getLength());
			
			List<Question> questions = new ArrayList<Question>();
			
			for (int i = 0; i < currentSurvey.getQuestions().size(); i++) {
				if (currentSurvey.getQuestions().get(i).getType().equals("tq")) {
					TextQuestion question = (TextQuestion) currentSurvey.getQuestions().get(i);
					TextQuestion newQuestion = new TextQuestion(question.getText(), question.getMinLength(), question.getMaxLength());
					questions.add(newQuestion);
				} else if (currentSurvey.getQuestions().get(i).getType().equals("mc")) {
					MultipleChoiceQuestion question = (MultipleChoiceQuestion) currentSurvey.getQuestions().get(i);
					MultipleChoiceQuestion newQuestion = new MultipleChoiceQuestion(question.getText(), true);
					
					List<String> options = new ArrayList<String>();
					
					for (int j = 0; j < question.getOptions().size(); j++) {
						options.add(question.getOptions().get(j));
					}
					
					newQuestion.setOptions(options);
					
					questions.add(newQuestion);
				}
			}
			
			newSurvey.setQuestions(questions);
			
//			newSurvey.setQuestions(currentSurvey.getQuestions());
			
//			for (int i = 0; i < currentSurvey.getQuestions().size(); i++) {
//				newSurvey.getQuestions().add(currentSurvey.getQuestions().get(i));
//			}
			
//			newSurvey.setId(null);
//			newSurvey.setEnddate(null);
//			
//			for (int i = 0; i < newSurvey.getQuestions().size(); i++) {
//				newSurvey.getQuestions().get(i).setAnswers(new ArrayList<Answer>());
//			}
			
			request.getSession(true).setAttribute("survey", newSurvey);
			response.sendRedirect(EDITOR_SURVEY_SERVLET);
		}
		else{
			response.sendRedirect(LOGIN_SERVLET);
		}
	}
}
