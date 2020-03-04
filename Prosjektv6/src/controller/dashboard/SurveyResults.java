package controller.dashboard;

import static utility.URLMappings.SURVEY_RESULTS_SERVLET;
import static utility.URLMappings.SURVEY_RESULTS_JSP;
import static utility.URLMappings.LOGIN_SERVLET;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EAO.EAO;
import model.Survey;
import utility.Authenticator;
import utility.BCA_AllQuestions;

/**
 * Servlet implementation class SurveyResults
 */
@WebServlet("/" + SURVEY_RESULTS_SERVLET)
public class SurveyResults extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private EAO EAO;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession(false) != null && Authenticator.isAuthenticated(request, EAO.findLecturer((String) request.getSession(false).getAttribute("username")))) {
			String surveyIdParameter = request.getParameter("surveyId");
			Integer surveyId = Integer.parseInt(surveyIdParameter);
			Survey survey = EAO.findSurvey(surveyId);
			request.getSession().setAttribute("surveyResult", survey);
			
			BCA_AllQuestions bca_allQuestions = new BCA_AllQuestions(survey);
			request.getSession().setAttribute("bca_allQuestions", bca_allQuestions);

			
			request.getRequestDispatcher(SURVEY_RESULTS_JSP).forward(request, response);
		} else {
			response.sendRedirect(LOGIN_SERVLET);
		}
	}

}
