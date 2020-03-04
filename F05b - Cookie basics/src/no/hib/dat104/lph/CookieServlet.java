package no.hib.dat104.lph;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* For å se sjekke cookies i Chrome:
 * 		chrome://settings/cookies
 */

@WebServlet("/somepage")
public class CookieServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        //Sjekker om cookie var med i requesten
        boolean besokt = false;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("besokt")) {
                    besokt = true;
                }
            }
        }

        //Sender med cookie i responsen uansett
        Cookie nycookie = new Cookie("besokt", "SpillerIngenRolle");
        nycookie.setMaxAge(60 * 60 * 24 * 365);
        response.addCookie(nycookie);

        PrintWriter out = response.getWriter();
        if (besokt) {
            out.println("Du har vært her før.");
        } else {
            out.println("Du har ikke vært her før.");
        }
    }
}
