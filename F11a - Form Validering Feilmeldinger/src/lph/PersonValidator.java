package lph;

public class PersonValidator {

    public static boolean isValidNavn(String navn) {
        return navn != null && navn.length() >= 2 && navn.length() <= 20;
    }

    public static boolean isValidPostnr(String postnr) {
        try {
            int pnr = Integer.parseInt(postnr);
            return pnr >= 1000 && pnr <= 9999;
        } catch (NumberFormatException e) {
            return false;
        }
    }
 }
