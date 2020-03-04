package controller.editor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Survey;
import model.question.MultipleChoiceQuestion;

@WebServlet("/TestCreate")
public class TestCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Survey survey = (Survey) request.getSession().getAttribute("survey");
		
		out.println("Name: " + survey.getName());
		out.println("Description: " + survey.getDescription());
		out.println("Length: " + survey.getLength());
		out.println("Repeatable: " + survey.getRepeatable());
		out.println("Traversable: " + survey.getTraversable());
		
		for (int i = 0; i < survey.getQuestions().size(); i++) {
			out.println("Question " + (i+1) + ": " + survey.getQuestions().get(i).getText());
			
			if (survey.getQuestions().get(i).getType().equals("mc")) {
				MultipleChoiceQuestion question = (MultipleChoiceQuestion) survey.getQuestions().get(i);
				for (int j = 0; j < question.getOptions().size(); j++) {
					out.println("Option " + (j+1) + ": " + question.getOptions().get(j));
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
