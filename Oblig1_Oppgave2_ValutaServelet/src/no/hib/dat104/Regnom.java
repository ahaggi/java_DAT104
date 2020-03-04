package no.hib.dat104;


public class Regnom {

	public static double regn(String amount, Double rate, String source, String target) {
		double veksletBelop=Double.parseDouble(amount) * rate ;
		return (veksletBelop) ;

	}
	


	public static boolean validate(String amount){
		boolean sant=true;
		
		sant= (amount.matches("^-?\\d+\\.?\\d*$"))?true:false;
			
		return sant;
	}
	
	
}
