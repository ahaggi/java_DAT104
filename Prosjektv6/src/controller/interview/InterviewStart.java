package controller.interview;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import EAO.EAO;
import model.*;
import model.question.Question;
import utility.URLGenerator;

import static utility.URLMappings.*;

/**
 * Sevlet for organizing all surveys. Loads the correct survey, give it to the
 * user and redirects.
 */
@WebServlet("/" + INTERVIEW_START_SERVLET)
public class InterviewStart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private EAO EAO;

	/**
	 * Finds the correct survey if it exists. Cleans session. Store survey info
	 * in ProcessAwnswer filter object stored in session.
	 * 
	 * Check if survey exists and if it is available. Redirects to
	 * INTERVIEW_HOME_JSP and adds either nececary parameters or correct error
	 * message.
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id_parameter = request.getParameter("surveyId");
		String error_id = request.getParameter("e");
		
		// Returnerer -1 om det skjer en feil. EAO h�terer dette.
		int id = URLGenerator.getDecryptedSurveyId(id_parameter);
		Survey survey = EAO.findSurvey(id);
		Long time = survey.getEnddate();

		// Sikrer at vi ikke har noe som kan lage problemer i session.
		// G�r en fra dashboard til Interview blir en logget ut.
		request.getSession().invalidate();
		HttpSession session = request.getSession(true);
		ProcessAnswerFilter processAnswerFilter;
		Question question;

		// if survey does not exist
		if (survey == null) {
			String e = ("-101".equals(error_id)) ? "You can't respond to this survey more than once."
					: "An error occured! The survey you are looking for does not exist";
			session.setAttribute("error", e);

			// exists and is open
		} else if (survey.isOpen()) {
			processAnswerFilter = new ProcessAnswerFilter(survey);
			session.setAttribute("processAnswerFilter", processAnswerFilter);
			question = processAnswerFilter.getQuestion(); // for f�rste sp�rsm�l
			session.setAttribute("question", question);
			
		} else {
			// exist but is not open
			session.setAttribute("error",
					"An error occured! The survey you are looking for is not available.");
		}
		Cookie endDate = new Cookie("ENDDATE", time.toString());
		endDate.setHttpOnly(false);
		endDate.setSecure(false);
		response.addCookie(endDate);
		request.getRequestDispatcher(INTERVIEW_START_JSP).forward(request, response);
		session.removeAttribute("error");
	}

	/**
	 * Starts the survey if one is allowed and redirects. Otherwise it adds the
	 * correct error message and redirects.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Declarations
		HttpSession session = request.getSession(true); // Guarrantee that a
														// session exists.
		ProcessAnswerFilter pac = (ProcessAnswerFilter) session.getAttribute("processAnswerFilter");
		String cookieName = "hasTokenSurvey";
		Cookie ck = null;
		boolean taken = false;
		Cookie[] cookies = request.getCookies(); // Collect cookies from user
		String[] surveys_token;

		// If the survey is repeatable, skip this step
		if (!pac.isRepeatable() && cookies != null)
			for (int i = 0; i < cookies.length && ck == null; i++)
				ck = (cookies[i] != null && cookies[i].getName().equals(cookieName)) ? cookies[i] : null;

		// If valid survey, cookie exists and is open
		// Check if the survey Id is allready stored in the cookie

		if (ck != null) {
			surveys_token = ck.getValue().split(":");

			for (String id : surveys_token) {
				if ((id != null) && (!"".equals(id)) && (id.equals(pac.getEncryptedSurveyId()))) {
					taken = true;
				}
			}
		}

		// If user is allowed/not allowed to take survey
		if (!taken && pac.isOpen()) {
			response.sendRedirect(INTERVIEW_SERVLET);

		} else if (taken) {
			response.sendRedirect(INTERVIEW_START_SERVLET + "?e=-101");
		}

	}

}
