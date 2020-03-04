package no.hib.dat104.lph;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bmi1")
public class BmiServlet1 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String hoydeInput = request.getParameter("hoyde");
        String vektInput = request.getParameter("vekt");

        if (hoydeInput != null && vektInput != null) {

            try {
                double hoydeMeter = Double.parseDouble(hoydeInput) / 100;
                double vektKg = Double.parseDouble(vektInput);

                if (hoydeMeter >= 1 && hoydeMeter <= 2.5 
                        && vektKg >= 10 && vektKg <= 250) {
                    
                    double bmi = vektKg / (hoydeMeter * hoydeMeter);
                    request.setAttribute("bmi", Math.round(bmi*10)/10.0);
                    
                } else {
                    request.setAttribute("feilmelding",
                            "Høyde og vekt må være normale verdier!");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("feilmelding",
                        "Høyde og vekt må være tall!");
            }
        }

        request.getRequestDispatcher("WEB-INF/bmi1.jsp").forward(request,
                response);
    }
}
