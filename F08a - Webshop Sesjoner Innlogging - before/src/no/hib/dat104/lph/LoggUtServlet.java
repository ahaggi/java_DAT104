package no.hib.dat104.lph;

import static no.hib.dat104.lph.UrlMappings.LOGOUT_URL;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/" + LOGOUT_URL)
public class LoggUtServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        // Inn noe kode her i forbindelse med utlogging av bruker?
		HttpSession sesion = request.getSession(false);
		// avslutte sesion så du ikke gå back til webshop side uten å logge inn på nytt 
		if (sesion != null) {
			sesion.invalidate();
		}

        response.setContentType("text/html; charset=ISO-8859-1");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"ISO-8859-1\">");
        out.println("<title>Logget ut</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>Du er nå logget ut!</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
