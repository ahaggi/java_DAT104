package no.hib.dat104.lph;

import static no.hib.dat104.lph.UrlMappings.LOGIN_URL;
import static no.hib.dat104.lph.UrlMappings.WEBSHOP_URL;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/" + LOGIN_URL)
public class LoggInnServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String redirectErrorMessage = "";

        if (request.getParameter("requiresLogin") != null) {
            redirectErrorMessage = "Forespørselen din krever pålogging. "
                    + "(Du kan ha blitt logget ut automatisk)";

        } else if (request.getParameter("invalidUsername") != null) {
            redirectErrorMessage = "Manglende eller ugyldig brukernavn";
        }
        request.setAttribute("redirectErrorMessage", redirectErrorMessage);

        request.getRequestDispatcher("WEB-INF/loginform.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");

        if (username == null || !Validator.isValidUsername(username)) {
            response.sendRedirect(LOGIN_URL + "?invalidUsername");
        } else {

            HttpSession sesjon = request.getSession(false);
            if (sesjon != null) {
                sesjon.invalidate();
            }
            sesjon = request.getSession(true);
            sesjon.setMaxInactiveInterval(30);

            sesjon.setAttribute("username", username);
            sesjon.setAttribute("cart", new Cart());

            response.sendRedirect(WEBSHOP_URL);
        }
    }
}
