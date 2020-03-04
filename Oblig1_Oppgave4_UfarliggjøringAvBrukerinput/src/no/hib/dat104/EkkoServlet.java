package no.hib.dat104;

import static no.hib.dat104.UrlMappings.EKKO_SERVLET;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;

@WebServlet("/"+EKKO_SERVLET)
public class EkkoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		String brukerinput = req.getParameter("fritekst");
		String valg = req.getParameter("valg");
		
		if (valg.equals("1")) {
			brukerinput =StringEscapeUtils.escapeHtml4(brukerinput);
		}else if (valg.equals("2")) {
			brukerinput = escapeHtml(brukerinput);
		}
		
        resp.setContentType("text/html; charset=ISO-8859-1");

        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"ISO-8859-1\">");
        out.println("<title>Ekko</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p> Hello  " +  brukerinput +"</p>");
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
