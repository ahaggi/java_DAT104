package modell;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringEscapeUtils;

import EAO.EAO;


/**
 * @author abdallah
 *
 */
/**
 * @author abdallah
 *
 */
/**
 * @author abdallah
 *
 */
public class PersonSkjema  {


	private String fornavn;
	private String etternavn;
	private String mobil;
	private String kjonn;
	private boolean registerertFrafoer;



	private String fornavnFeilmelding="";
	private String etternavnFeilmelding="";
	private String mobilFeilmelding=""; 
	private String kjonnFeilmelding="";
	/**
	 * Lager en ny person-skjema.
	 * 
	 * @param String fornavn
	 * @param String etternavn
	 * @param String mobil
	 * @param String kjonn.	 
	 */	
	public  void fyllPaaAttributter(HttpServletRequest request,EAO eao) {
		fornavn = StringEscapeUtils.escapeHtml4(request.getParameter("fornavn"));
		etternavn = StringEscapeUtils.escapeHtml4(request.getParameter("etternavn"));

		fornavn = (fornavn != null) ? fornavn.trim() : fornavn;
		etternavn = (etternavn != null) ? etternavn.trim() : etternavn;
		
		
		
		mobil = StringEscapeUtils.escapeHtml4(request.getParameter("mobil"));
		
		
		
		kjonn = request.getParameter("kjonn");
		registerertFrafoer = eao.erRegisterertFraFoer(mobil);

	}



	/**
	 * Sjekker at input er riktig.
	 */

	public boolean erAllInputGyldig() {

		boolean allInputGyldig = true;

		if (!isValidForNavn(fornavn)) {
			allInputGyldig = false;
		}
		if (!isValidEtterNavn(etternavn)) {
			allInputGyldig = false;
		}
		if (!isValidMobil(mobil)) {
			allInputGyldig = false;
		}
		if (!isValidKjonn(kjonn)) {
			allInputGyldig = false;
		}

		return allInputGyldig;
	}

	//	Fornavn skal v&#230re 2-20 tegn og kan inneholde bokstaver (inkl. &#230&#248&#229ÆØÅ),
	//	bindestrek og mellomrom. F&#248rste tegn skal v&#230re en stor bokstav.

	public  boolean isValidForNavn(String fornavn) {
		boolean valid=true;
		if (fornavn == null) {
			fornavnFeilmelding = "<br/>Fornavn er obligatorisk og m&#229 v&#230re 2-20 tegn.</br> - F&#248rste tegn m&#229 v&#230re en stor bokstav.</br> - Fornavn kan kun inneholde bokstaver, bindestrek og mellomrom.";
			valid=false;

		}else {
			if (! fornavn.matches(".{2,20}")) {
				fornavnFeilmelding =  "<br/>- Fornavn m&#229 v&#230re 2-20 tegn.";
				valid=false;
			}
			if (! fornavn.matches("^[A-ZÆØÅ]+.*$")) {
				fornavnFeilmelding =  fornavnFeilmelding+"<br/>- F&#248rste tegn m&#229 v&#230re en stor bokstav";
				valid=false;
			}
			if (! fornavn.matches("[a-zA-zæøåÆØÅ_ ]+")) {
				fornavnFeilmelding =  fornavnFeilmelding+"</br> - Fornavn kan kun inneholde bokstaver, bindestrek og mellomrom.";
				valid=false;

			}
		}

		return valid;

	}

	//	 Etternavn skal v&#230re 2-20 tegn og kan inneholde bokstaver (inkl. &#230&#248&#229ÆØÅ) og
	//	bindestrek (IKKE mellomrom). F&#248rste tegn skal v&#230re en stor bokstav.

	public  boolean isValidEtterNavn(String etternavn) {
		boolean valid=true;
		if (etternavn == null) {
			etternavnFeilmelding = "<br/>Etter er obligatorisk og m&#229 v&#230re 2-20 tegn.</br> - F&#248rste tegn m&#229 v&#230re en stor bokstav.</br> - Etter kan kun inneholde bokstaver og bindestrek (IKKE mellomrom).";
			valid=false;

		}else {
			if (! etternavn.matches("^.{2,20}$")) {
				etternavnFeilmelding =  "<br/>- Fornavn er m&#229 v&#230re 2-20 tegn.";
				valid=false;
			}
			if (! etternavn.matches("^[A-ZÆØÅ]+.*$")) {
				etternavnFeilmelding =  etternavnFeilmelding+"<br/>- F&#248rste tegn m&#229 v&#230re en stor bokstav";
				valid=false;
			}
			if (! etternavn.matches("[a-zA-zæøåÆØÅ_]+")) {
				etternavnFeilmelding =  etternavnFeilmelding+"</br> - Fornavn kan kun inneholde bokstaver og bindestrek.";
				valid=false;

			}
		}

		return valid;

	}

	//	at mobilnummeret IKKE m&#229 tilh&#248re en allerede p&#229meldt deltager. Alle
	//	mobilnumre i deltagerlisten skal v&#230re unike.
	public  boolean isValidMobil(String mobil) {
		boolean valid =true;
		if (mobil == null || !mobil.matches("^[0-9]{8}$")) {
			mobilFeilmelding="<br/>- Mobil skal v&#230re eksakt 8 siffer";
			valid= false;
		}else if (registerertFrafoer) {
			mobilFeilmelding="<br/>- Mobil nr er registerert fra f&#248r";
			valid= false;
		}

		return valid;
	}

	public  boolean isValidKjonn(String kjonn) {
		boolean valid=true;
		if (kjonn == null) {
			kjonnFeilmelding = "<br/>Kjonn er obligatorisk og m&#229 v&#230re fyllt ut.</br> ";
			valid=false;
		}

		return valid;

	}




/**Sjekker om brukeren er allerede registerert
 * @param eao
 * @return boolean
 */
public boolean ErRegisterertFraFoer(EAO eao){
	registerertFrafoer = eao.erRegisterertFraFoer(mobil);
	if (registerertFrafoer) {
		mobilFeilmelding="<br/>- Mobil nr er registerert fra f&#248r";
	}
	return registerertFrafoer;

}

	public String getFornavn() {
		return fornavn;
	}




	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}




	public boolean isRegisterertFrafoer() {
		return registerertFrafoer;
	}




	public void setRegisterertFrafoer(boolean registerertFrafoer) {
		this.registerertFrafoer = registerertFrafoer;
	}




	public String getEtternavn() {
		return etternavn;
	}




	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}




	public String getMobil() {
		return mobil;
	}




	public void setMobil(String mobil) {
		this.mobil = mobil;
	}




	public String getFornavnFeilmelding() {
		return fornavnFeilmelding;
	}




	public void setFornavnFeilmelding(String fornavnFeilmelding) {
		this.fornavnFeilmelding = fornavnFeilmelding;
	}




	public String getEtternavnFeilmelding() {
		return etternavnFeilmelding;
	}




	public void setEtternavnFeilmelding(String etternavnFeilmelding) {
		this.etternavnFeilmelding = etternavnFeilmelding;
	}




	public String getMobilFeilmelding() {
		return mobilFeilmelding;
	}




	public void setMobilFeilmelding(String mobilFeilmelding) {
		this.mobilFeilmelding = mobilFeilmelding;
	}




	public String getKjonn() {
		return kjonn;
	}

	public void setKjonn(String kjonn) {
		this.kjonn = kjonn;
	}

	public String getKjonnFeilmelding() {
		return kjonnFeilmelding;
	}

	public void setKjonnFeilmelding(String kjonnFeilmelding) {
		this.kjonnFeilmelding = kjonnFeilmelding;
	}

}
