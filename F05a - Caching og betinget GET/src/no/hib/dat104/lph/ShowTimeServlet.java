package no.hib.dat104.lph;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/showtime")
public class ShowTimeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private long lastModifiedMillis = (new Date()).getTime()/1000*1000;
    
    @Override
    protected long getLastModified(HttpServletRequest req) {
        return lastModifiedMillis;
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

    	//Thread safe?
        lastModifiedMillis = (new Date()).getTime()/1000*1000;
        
        response.sendRedirect("showtime");
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html; charset=ISO-8859-1");
//        response.setHeader("Refresh", "5");
        
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"ISO-8859-1\">");
        out.println("<title>Siste posting</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>Siste posting: " + DateFormat.getTimeInstance(DateFormat.LONG, new Locale("no", "NO")).format(new Date(lastModifiedMillis))
                + "</p>");
        out.println("</body>");
        out.println("</html>");

    }

}
