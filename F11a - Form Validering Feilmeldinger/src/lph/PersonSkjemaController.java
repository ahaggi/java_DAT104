package lph;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/personskjema")
public class PersonSkjemaController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/personskjema.jsp").forward(
                request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();
    	
    	PersonSkjema ps = new PersonSkjema(request);
     	
        if (!ps.erAllInputGyldig()) {
        	session.setAttribute("ps", ps);
            response.sendRedirect("personskjema");
        } else {
        	
			Person p = new Person();
			p.setNavn(ps.getNavn());
			p.setPostnr(Integer.parseInt(ps.getPostnr()));
			//Evt. lagre p i database ...
        	
        	session.removeAttribute("ps");
            response.sendRedirect("hurra.html");
        }
    }
}
