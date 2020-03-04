package controller.dashboard;

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
import model.Lecturer;
import model.Survey;
import model.question.MultipleChoiceQuestion;
import model.question.Question;
import utility.AddToSurvey;
import utility.Authenticator;
/**
 * Servlet for creating a new survey. 
 */
@WebServlet("/" + EDITOR_SURVEY_SERVLET)
public class Editor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private EAO EAO;

	/**
	 * Authenticates the user then:
	 * Editor a fresh survey object. 
	 * Then sends this to the create.jsp page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Authenticate
		if (request.getSession(false) != null && Authenticator.isAuthenticated(request, EAO.findLecturer((String) request.getSession(false).getAttribute("username")))) {
			// If ther is no attached survey, make a new survey
			if (request.getSession().getAttribute("survey") == null) {
				// Editor new survey, get the referance to the list of questions
				Survey currentSurvey = new Survey();
				List<Question> questions = currentSurvey.getQuestions();
				// Editor a new empty question, add the empty question to the question list
				MultipleChoiceQuestion question1 = new MultipleChoiceQuestion();
				questions.add(question1);
				// put the survey in the session
				request.getSession(true).setAttribute("survey", currentSurvey);
			}
			request.getRequestDispatcher(EDITOR_SURVEY_JSP).forward(request, response);
		}
		else{
			response.sendRedirect(LOGIN_SERVLET);
		}
	}

	/**
	 * Authenticates the user:
	 * Takes the new survey and stores it in the database. 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Authencicate
		if (request.getSession(false) != null && Authenticator.isAuthenticated(request, EAO.findLecturer((String) request.getSession(false).getAttribute("username")))) {
			
			if (AddToSurvey.addWithValidation(request)) {
				Survey currentSurvey = (Survey) request.getSession(true).getAttribute("survey");
				
				if (currentSurvey.getId() != null) {
					EAO.updateSurvey(currentSurvey);
				} else {
					String username = (String) request.getSession().getAttribute("username");
					Lecturer l = EAO.findLecturer(username);
					l.addSurvey(currentSurvey);
					EAO.updateLecturer(l);
				}
		
				request.getSession().removeAttribute("survey");
				response.sendRedirect(DASHBOARD_SERVLET);
			} else {
				response.sendRedirect(EDITOR_SURVEY_SERVLET);
			}

		} else {
			response.sendRedirect(LOGIN_SERVLET);
		}	
	}
}
