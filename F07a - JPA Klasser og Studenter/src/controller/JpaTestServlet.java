package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataaccess.StudentEAO;
import model.Student;

@WebServlet("/JpaTestServlet")
public class JpaTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private StudentEAO studentEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Student> alleStudenter = studentEAO.alleStudenterTotalt();

		PrintWriter out = response.getWriter();
		for (Student s : alleStudenter) {
			out.println(s.getNavn());
		}

		/************************************Sjekk om den er sikkert nok********************************************/
		List<Student> viseStudenter = studentEAO.finnStudentMedNavn("qqqqqq");

		for (Student s : viseStudenter) {
			out.println(s.getNavn());
		}
		/***********************************************************************************************/

 		Student s = studentEAO.finnStudent("h123456");
  		s.setNavn("qqqqq");
		studentEAO.oppdaterStudent(s);

	}
}
