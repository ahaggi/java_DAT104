package lph;

import javax.servlet.http.HttpServletRequest;

public class PersonSkjema {
	
	private String navn;
	private String postnr;
	
	private String navnFeilmelding="";
	private String postnrFeilmelding;
	
	public PersonSkjema(HttpServletRequest request) {
		navn = request.getParameter("navn");
		postnr = request.getParameter("postnr");
	}

	public String getNavn() {
		return navn;
	}

	public String getPostnr() {
		return postnr;
	}

	public String getNavnFeilmelding() {
		return navnFeilmelding;
	}

	public String getPostnrFeilmelding() {
		return postnrFeilmelding;
	}

	
	public boolean erAllInputGyldig() {
		
		boolean allInputGyldig = true;
		
		if (!isValidNavn(navn)) {
// 			navnFeilmelding = "Navn er obligatorisk og m� v�re </br> 2-20 tegn.";
			allInputGyldig = false;
		}
		if (!isValidPostnr(postnr)) {
 			postnrFeilmelding = "Postnummer er obligatorisk og m� v�re 4 sifre.";
			allInputGyldig = false;
		}
		return allInputGyldig;
	}
	
	
    public  boolean isValidNavn(String fornavn) {
        boolean valid=true;
        if (fornavn == null) {
    		navnFeilmelding = "<br/>Fornavn er obligatorisk og m� v�re 2-20 tegn.</br> - F�rste tegn skal v�re en stor bokstav.</br> - Fornavn kan kun inneholde bokstaver, bindestrek og mellomrom.";
    		 valid=false;
    		
        }else {
        	if (! fornavn.matches("^.{2,20}$")) {
        		navnFeilmelding =  "<br/>- Fornavn er m� v�re 2-20 tegn.";
        		valid=false;
        	}
        	if (! fornavn.matches("^[A-Z���].*$")) {
        		navnFeilmelding =  navnFeilmelding+"<br/>- F�rste tegn m� v�re en stor bokstav";
        		valid=false;
        	}
        	if (! fornavn.matches("[a-zA-z0-9������_ ]+")) {
        		navnFeilmelding =  navnFeilmelding+"</br> - Fornavn kan kun inneholde bokstaver, bindestrek og mellomrom.";
        		valid=false;

        	}
        }
        
		return valid;
        
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











