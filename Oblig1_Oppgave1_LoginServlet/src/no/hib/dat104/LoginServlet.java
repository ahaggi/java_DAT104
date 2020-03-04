package no.hib.dat104;

import static no.hib.dat104.UrlMappings.LOGIN_URL;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/" + LOGIN_URL )
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String fornavn = escapeHtml(request.getParameter("fornavn"));
        String etternavn = escapeHtml(request.getParameter("etternavn"));
		String[] lang= request.getParameterValues("language");

        
        response.setContentType("text/html; charset=ISO-8859-1");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"ISO-8859-1\">");
        out.println("<title>Hallo</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Loggin result</h1> <br>");

        out.println("<h3>Logged in form "+request.getRequestURL()+"</h3> <br>");
        out.println("<p>Username " + fornavn  +"</p> <br>");
        out.println("<p>Username " + etternavn  +"</p>");
	    out.println("  language = " + Arrays.toString(lang));

        out.println("</body>");
        out.println("</html>");
        
    }
	private String escapeHtml(String str) {
		str = str.replaceAll("<","&lt;"); 
		str = str.replaceAll(">","&gt;");
		str = str.replaceAll("'","&#39;");
		str = str.replaceAll("\"","&#34;");
		str = str.replaceAll("=","&#61;");
		str = str.replaceAll("/","&#47;");
  		return str;
	}

}
