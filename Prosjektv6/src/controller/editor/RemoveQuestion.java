package controller.editor;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EAO.EAO;
import model.Survey;
import utility.AddToSurvey;
import utility.Authenticator;

import static utility.URLMappings.*;

/**
 * Removes a Question from a Survey.
 *
 */
@WebServlet("/RemoveQuestion")
public class RemoveQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private EAO EAO;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession(false) != null && Authenticator.isAuthenticated(request, EAO.findLecturer((String) request.getSession(false).getAttribute("username")))) {
			AddToSurvey.add(request);
			String removeQuestionNr = request.getParameter("removeQuestion");
			removeQuestionNr = removeQuestionNr.substring(15);
			int questionIndex = Integer.parseInt(removeQuestionNr) - 1;
			Survey survey = (Survey) request.getSession().getAttribute("survey");
			
			survey.getQuestions().remove(questionIndex);
			
			request.getSession().setAttribute("autoFocus", -1);
			request.getSession().setAttribute("survey", survey);
			response.sendRedirect(EDITOR_SURVEY_SERVLET);
		} else {
			response.sendRedirect(LOGIN_SERVLET);
		}
	}

}
