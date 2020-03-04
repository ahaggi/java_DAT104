package no.hib.dat104.lph;

public class TallUtils {
    
    public static boolean erEnDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
