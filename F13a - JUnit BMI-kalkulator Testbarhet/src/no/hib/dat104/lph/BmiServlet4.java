package no.hib.dat104.lph;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bmi4")
public class BmiServlet4 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private BmiCalculator4 bmiCalculator = new BmiCalculator4();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String hoydeInput = request.getParameter("hoyde");
        String vektInput = request.getParameter("vekt");

        if (hoydeInput != null && vektInput != null) {

            try {
                double hoydeMeter = Double.parseDouble(hoydeInput) / 100;
                double vektKg = Double.parseDouble(vektInput);

                if (bmiCalculator.gyldigHoydeMeter(hoydeMeter) 
                        && bmiCalculator.gyldigVektKg(vektKg)) {

                    double bmi = bmiCalculator.beregnBmi(hoydeMeter, vektKg);
                    double avrundetBmi = bmiCalculator.rundAvTilEnDesimal(bmi);
                    request.setAttribute("bmi", avrundetBmi);

                    String vektklasse = bmiCalculator.beregnVektklasse(bmi);
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

        request.getRequestDispatcher("WEB-INF/bmi4.jsp").forward(request,
                response);
    }
    
}
