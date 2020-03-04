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
import model.Survey;
import model.answer.Answer;
import model.question.Question;

import static utility.URLMappings.*;

/**
 *Finishes the survey. 
 */
@WebServlet("/" + INTERVIEW_COMPLETE_SERVLET)
public class InterviewComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private EAO EAO;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		request.getRequestDispatcher(INTERVIEW_COMPLETE_JSP).forward(request, response);
		session.removeAttribute("status");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		boolean status = false;

		ProcessAnswerFilter processAnswerFilter = (ProcessAnswerFilter) session.getAttribute("processAnswerFilter");
		if (processAnswerFilter != null) {
			// henter survey id og survey
			Integer id = processAnswerFilter.getId();
			Survey survey;
			if (id != null) {
				survey = EAO.findSurvey(id);
				if (survey != null) {
					for (Question q : survey.getQuestions()) {
						Answer answer = processAnswerFilter.getMap().get(q.getId());
						if (answer != null)
							q.addAnswer(answer);
					}
					synchronized (survey) {
						EAO.updateSurvey(survey);
					}

					String cookieName = "hasTokenSurvey";
					Cookie ck = null;
					Cookie[] cookies = request.getCookies();
					if (cookies != null) {
						for (Cookie c : cookies) {
							ck = c.getName().equals(cookieName) ? c : null;
						}
						ck = (ck == null) ? new Cookie(cookieName, "") : ck;
						ck.setValue(ck.getValue() + ":" + survey.getEncryptedSurveyId());
						ck.setMaxAge(60 * 60 * 24 * 365);
						response.addCookie(ck);
					}

					session.removeAttribute("processAnswerFilter");
				}
			}
			
			status = true;
		}
		
		// set status flag for page. (true => success, false => failure)
		session.setAttribute("status", status);
		// finally redirect to itself.
		response.sendRedirect(INTERVIEW_COMPLETE_SERVLET);
	}

}
