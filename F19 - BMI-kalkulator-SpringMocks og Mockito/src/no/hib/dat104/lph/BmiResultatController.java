package no.hib.dat104.lph;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/bmiResultat")
public class BmiResultatController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String FEILMELDING_ULOVLIG_REQUEST = "Ulovlig forespørsel!!! :(";
    private static final String FEILMELDING_MANGLENDE_INPUT = "Du må oppgi høyde og vekt!";
    private static final String FEILMELDING_UGYLDIG_INPUT = "Høyde og vekt må være tall!";
    private static final String FEILMELDING_UNORMAL_INPUT = "Høyde og vekt må være normale verdier!";

    private BmiKalkulator bmiCalc = new BmiKalkulator();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.removeAttribute("feilmelding");

        String hoydeInput = request.getParameter("hoyde");
        String vektInput = request.getParameter("vekt");

        if (hoydeInput == null || vektInput == null) {
            redirectTilSkjema(session, FEILMELDING_ULOVLIG_REQUEST, response);

        } else if (hoydeInput.isEmpty() || vektInput.isEmpty()) {
            redirectTilSkjema(session, FEILMELDING_MANGLENDE_INPUT, response);

        } else if (!TallUtils.erEnDouble(hoydeInput)
                || !TallUtils.erEnDouble(vektInput)) {
            redirectTilSkjema(session, FEILMELDING_UGYLDIG_INPUT, response);

        } else {

            double hoydeMeter = Double.parseDouble(hoydeInput) / 100;
            double vektKg = Double.parseDouble(vektInput);

            if (!bmiCalc.gyldigHoydeMeter(hoydeMeter)
                    || !bmiCalc.gyldigVektKg(vektKg)) {
                redirectTilSkjema(session, FEILMELDING_UNORMAL_INPUT, response);

            } else {

                double bmi = bmiCalc.beregnBmi(hoydeMeter, vektKg);
                double avrundetBmi = bmiCalc.rundAvTilEnDesimal(bmi);
                request.setAttribute("bmi", avrundetBmi);

                BmiVektklasse vektklasse = bmiCalc.beregnVektklasse(bmi);
                request.setAttribute("vektklasse", vektklasse);

                request.getRequestDispatcher("WEB-INF/bmiResultat.jsp")
                        .forward(request, response);
            }
        }
    }

    private void redirectTilSkjema(HttpSession session, String feilmelding,
            HttpServletResponse response) throws IOException {
        session.setAttribute("feilmelding", feilmelding);
        response.sendRedirect("bmiSkjema");
    }

}
