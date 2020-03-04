package somepackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @WebServlet("/") Dette går ikke hvis vi har web.xml !!!
@WebServlet(name = "Min magiske Servlet", urlPatterns = "/")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String greetingForThisServletOnly;
	String greetingForTheEntireApplication;

	@Override
	public void init() throws ServletException {
		
		greetingForThisServletOnly = getServletConfig().getInitParameter("greeting");
		greetingForTheEntireApplication = getServletContext().getInitParameter("greeting");
//		getServletContext().setInitParameter("Kasserer_Passord", "admin");
//		Én grei ting å bruke web.xml til er init-parametre til en servlet, dvs. konfigdata som kan brukes i en servlet.
//		• Tilgang via getServletConfig(). getInitParameter(pNavn);
//		context-params
//		• init-parametre (se forrige slide) er knyttet til én bestemt servlet.
//		• Av og til ønsker vi at slike parametre er tilgjengelig i hele applikasjonen
//		(context-en).
//		• Da har vi noe som heter context-parametre, som også kan angis i
//		web.xml
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/plain");
		
		PrintWriter out = response.getWriter();
		
		out.println(greetingForThisServletOnly);
		out.println(greetingForTheEntireApplication);
	}

}
