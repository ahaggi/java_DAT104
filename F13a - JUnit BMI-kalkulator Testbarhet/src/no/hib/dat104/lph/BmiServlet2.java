package no.hib.dat104.lph;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bmi2")
public class BmiServlet2 extends HttpServlet {
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

                if (hoydeMeter >= 1 && hoydeMeter <= 2.5 && vektKg >= 10
                        && vektKg <= 250) {

                    double bmi = vektKg / (hoydeMeter * hoydeMeter);
                    request.setAttribute("bmi", Math.round(bmi * 10) / 10.0);

                    /*
                     * BMI-grenser fra WHO: BMI under 18,5 regnes som undervekt
                     * BMI mellom 18,5 og 24,9 regnes som normalvekt BMI over 25
                     * er definert som overvekt BMI over 30 er definert som
                     * helseskadelig overvekt
                     */
                    String vektklasse = 
                            bmi <= 18.5 ? "undervekt"
                            : bmi <= 24.9 ? "normalvekt"
                            : bmi <= 30 ? "overvekt"
                            : "helseskadelig overvekt";

                    request.setAttribute("vektklasse", vektklasse);

                } else {
                    request.setAttribute("feilmelding",
                            "Høyde og vekt må være normale verdier!");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("feilmelding",
                        "Høyde og vekt må være tall!");
            }
        }

        request.getRequestDispatcher("WEB-INF/bmi2.jsp").forward(request,
                response);
    }
}
