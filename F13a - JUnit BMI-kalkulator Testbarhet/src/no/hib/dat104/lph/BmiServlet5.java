package no.hib.dat104.lph;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/bmi5")
public class BmiServlet5 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private BmiCalculator5 bmiCalc = new BmiCalculator5();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String hoydeInput = request.getParameter("hoyde");
        String vektInput = request.getParameter("vekt");

        if (hoydeInput != null && vektInput != null) {

            try {
                double hoydeMeter = Double.parseDouble(hoydeInput) / 100;
                double vektKg = Double.parseDouble(vektInput);

                if (bmiCalc.gyldigHoydeMeter(hoydeMeter) 
                        && bmiCalc.gyldigVektKg(vektKg)) {

                    double bmi = bmiCalc.beregnBmi(hoydeMeter, vektKg);
                    double avrundetBmi = bmiCalc.rundAvTilEnDesimal(bmi);
                    request.setAttribute("bmi", avrundetBmi);

                    BmiVektklasse5 vektklasse = bmiCalc.beregnVektklasse(bmi);
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

        request.getRequestDispatcher("WEB-INF/bmi5.jsp").forward(request,
                response);
    }
    
}
