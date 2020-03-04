package no.hib.dat104.lph;

import static no.hib.dat104.lph.BmiVektklasse.*;

public class BmiKalkulator {

    /**
     * BMI-grenser fra WHO: 
     * BMI under 18,5 regnes som undervekt
     * BMI mellom 18,5 og 24,9 regnes som normalvekt 
     * BMI over 25 er definert som overvekt 
     * BMI over 30 er definert som helseskadelig overvekt
     */
    BmiVektklasse beregnVektklasse(double bmi) {
        return bmi < 18.5 ? UNDERVEKT
                : bmi < 25 ? NORMALVEKT
                : bmi < 30 ? OVERVEKT
                : HELSESKADELIG_OVERVEKT;
    }

    double rundAvTilEnDesimal(double tall) {
        return Math.round(tall * 10) / 10.0;
    }

    double beregnBmi(double hoydeMeter, double vektKg) {
        return vektKg / (hoydeMeter * hoydeMeter);
    }

    boolean gyldigHoydeMeter(double hoydeMeter) {
        return hoydeMeter >= 1 && hoydeMeter <= 2.5;
    }

    boolean gyldigVektKg(double vektKg) {
        return vektKg >= 10 && vektKg <= 250;
    }

}
