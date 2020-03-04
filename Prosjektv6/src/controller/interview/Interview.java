package controller.interview;

import static utility.URLMappings.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.question.Question;

/**
 * Servlet that handles the survey. Serves the questions and registers answers.
 */
@WebServlet("/" + INTERVIEW_SERVLET)
public class Interview extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Starts the survey.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true); // true hindrer
														// nullpointerexception
														// i .getAttribute
		ProcessAnswerFilter processAnswerFilter = (ProcessAnswerFilter) session.getAttribute("processAnswerFilter");

		if (processAnswerFilter == null) {
			response.sendRedirect(INTERVIEW_START_SERVLET + "?e=-101");
		} else {
			session.removeAttribute("survey");
			request.getRequestDispatcher(INTERVIEW_JSP).forward(request, response);
		}
	}

	/**
	 * Handles the anwser
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		ProcessAnswerFilter processAnswerFilter = (ProcessAnswerFilter) session.getAttribute("processAnswerFilter");
		
		if (processAnswerFilter == null) {
			response.sendRedirect(INTERVIEW_START_SERVLET);
		} else {
			boolean processData_OK =  processAnswerFilter.processData(request);
			Question question = (Question) session.getAttribute("question");
			String submit = request.getParameter("Action");
	
			if ("Next".equals(submit)) {
 				question = (processData_OK) ? processAnswerFilter.getNextQuestion() : processAnswerFilter.getQuestion();
			} else if ("Previous".equals(submit)) {
 				question = (processData_OK) ? processAnswerFilter.getPreviousQuestion() : processAnswerFilter.getQuestion();
			} else if ("Back".equals(submit)) {
				question = processAnswerFilter.getQuestion();
			}
	
			if (question == null) {
	
				if (!processAnswerFilter.isAllDataProcessed())
					session.removeAttribute("processAnswerFilter");
	
				session.removeAttribute("question");
				response.sendRedirect(INTERVIEW_COMPLETE_SERVLET);
			} else {
				session.setAttribute("question", question);
				response.sendRedirect(INTERVIEW_SERVLET);
			}
		}
	}
}
