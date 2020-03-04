package controller.dashboard;

import static utility.URLMappings.*;

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

/**
 * Servlet implementation class Delete
 */
@WebServlet("/" + CLOSE_SURVEY_SERVLET)
public class Close extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private EAO EAO;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession(false) != null && Authenticator.isAuthenticated(request,
				EAO.findLecturer((String) request.getSession(false).getAttribute("username")))) {

			int surveyId = -1;
			String sid = request.getParameter("surveyId");
			//try {
				surveyId = Integer.parseInt(sid);
				// if (surveyId < 0) {
				// // ID is not a valid number!!!
				// } else {
				// }
			//} catch (IllegalFormatException e) {
			//	e.printStackTrace();
			//}
			Survey survey = EAO.findSurvey(surveyId);
			//if (survey != null) {
				survey.close();
			//} else {
				//TODO Fikse feilh�ndtering
			//}
				
			EAO.updateSurvey(survey);

			response.sendRedirect(DASHBOARD_SERVLET);
		} else {
			response.sendRedirect(LOGIN_SERVLET);
		}
	}

}
