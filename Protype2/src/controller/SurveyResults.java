package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Survey;

@WebServlet("/SurveyResults")
public class SurveyResults extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String surveyName = request.getParameter("surveyName");
		List<Survey> surveys = (List<Survey>) request.getSession().getAttribute("surveys");
		
		for (int i = 0; i < surveys.size(); i++) {
			if (surveys.get(i).getNavn().equals(surveyName)) {
				request.getSession().setAttribute("survey", surveys.get(i));
			}
		}
		request.getRequestDispatcher("WEB-INF/SurveyResultsHighcharts.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
