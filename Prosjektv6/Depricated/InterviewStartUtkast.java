package controller.interview;

import static utility.URLMappings.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import EAO.EAO;
import model.Survey;
import model.answer.Answer;
import model.answer.MultipleChoiceAnswer;
import model.answer.TextAnswer;
import model.question.Question;

/**
 * Servlet implementation class InterviewStart
 */
@WebServlet("/" + INTERVIEW_SERVLET+"xxxxxxx")
public class InterviewStartUtkast extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InterviewStartUtkast() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		if (session.getAttribute("questionNr") == null || (int) session.getAttribute("questionNr") == 0) {
			session.setAttribute("questionNr", 0);
		}

		if (session.getAttribute("survey") != null) {
			request.getRequestDispatcher(INTERVIEW_JSP).forward(request, response);
		} else {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		// TODO: Sjekk at survey-attributten heter "survey"
		Survey sur = (Survey) session.getAttribute("survey");

		
		// TODO: valider at svardata er korrekt

		
		if (request.getParameter("submit").equals("next")) {
			int questionNr = (int) session.getAttribute("questionNr");
			Question que = sur.getQuestion(questionNr);
			Answer ans = null;

			if (que.getType().equals("mc")) {
				ans = new MultipleChoiceAnswer();
				((MultipleChoiceAnswer) ans).addOption(Integer.parseInt(request.getParameter("answer")));
			} else if (que.getType().equals("tq")) {
				ans = new TextAnswer();
				((TextAnswer) ans).setText(request.getParameter("answer"));
			}

			que.addAnswer(ans);
			questionNr++;
			session.setAttribute("questionNr", questionNr);
			session.setAttribute("survey", sur);

			if (questionNr >= sur.getQuestions().size()) {
				EAO eao = new EAO();
				eao.updateSurvey(sur);
				response.sendRedirect(request.getContextPath() + "/" + INTERVIEW_COMPLETE_SERVLET);
			} else {
				response.sendRedirect(request.getContextPath() + "/" + INTERVIEW_SERVLET);
			}
		} else if (request.getParameter("submit").equals("previous") && sur.isTraversable()) {
			int questionNr = (int) session.getAttribute("questionNr");
			questionNr--;
			
			sur.getQuestions().remove(questionNr);
			
			session.setAttribute("questionNr", questionNr);
			session.setAttribute("survey", sur);

			response.sendRedirect(request.getContextPath() + "/" + INTERVIEW_SERVLET);
		} else {
			response.sendRedirect(request.getContextPath() + "/" + INTERVIEW_SERVLET);
		}
	}
}
