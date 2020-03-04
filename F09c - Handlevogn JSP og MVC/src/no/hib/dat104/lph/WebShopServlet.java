package no.hib.dat104.lph;

import static no.hib.dat104.lph.UrlMappings.LOGIN_URL;
import static no.hib.dat104.lph.UrlMappings.WEBSHOP_URL;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/" + WEBSHOP_URL)
public class WebShopServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        HttpSession sesjon = request.getSession(false);

        if (sesjon == null) {
            response.sendRedirect(LOGIN_URL + "?requiresLogin");
        } else {
            request.getRequestDispatcher("WEB-INF/webshop.jsp").forward(
                    request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        HttpSession sesjon = request.getSession(false);

        if (sesjon == null) {
            response.sendRedirect(LOGIN_URL + "?requiresLogin");
        } else {
            Cart cart = (Cart) sesjon.getAttribute("cart");

            Map<String, String[]> params = request.getParameterMap();  // OBS et map med alle parameters,, Hver parameter vil ha tabell med mulige values

            if (params.containsKey("bukse")) {
                cart.addItem(new CartItem("Bukse", 699.0));
            }
            if (params.containsKey("genser")) {
                cart.addItem(new CartItem("Genser", 399.0));
            }

            response.sendRedirect(WEBSHOP_URL);
        }
    }
}
