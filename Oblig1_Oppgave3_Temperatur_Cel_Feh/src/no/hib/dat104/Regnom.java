package no.hib.dat104;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Regnom {

	public static double regn(String tempInndata, String valg) {
		double tempRes=0;

		switch (valg) {
		case "1":
			tempRes =(Double.parseDouble(tempInndata) * (9./5.))+32 ;
			break;
		case "2":
			tempRes = (Double.parseDouble(tempInndata)  - 32) / (9./5.)  ;
			break;

		default:
			break;
		}

		return tempRes;

	}

	public static String Formater(String tempInndata, String valg, double tempRes) {
		String resMelding = "";
		NumberFormat formater = new DecimalFormat("#0.0");

		switch (valg) {
		case "1":
			resMelding = (formater.format(Double.parseDouble(tempInndata))) + " \u00b0  C = " + formater.format(tempRes) + "\u00b0 F";
			break;
		case "2":
			resMelding = (formater.format(Double.parseDouble(tempInndata))) + "\u00b0 F = "	+ formater.format(tempRes) + "\u00b0 C";
			break;

		default:
			break;
		}
		return resMelding;

	}



	public static boolean validate(String tempInndata, String valg){
		boolean sant=false;
		
		if (tempInndata.matches("^-?\\d+\\.?\\d*$") ) {
			if (valg.equals("1") && (Double.parseDouble(tempInndata)>= -273.15)) {
				sant=true;
			}else if (valg.equals("2") && Double.parseDouble(tempInndata)>= -459.67) {
				sant=true;
			}
			
		}
		
		
		//		 try {
		//	            Double.parseDouble(tempInndata);
		//	            return true;
		//	        } catch (NumberFormatException e) {
		//	            return false;
	//	        }
	return sant;
	}
	
}
