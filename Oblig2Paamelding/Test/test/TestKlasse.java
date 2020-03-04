package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modell.PersonSkjema;


 
public class TestKlasse {
	PersonSkjema ps;
	String fornavn="Fornavn";
	String etternavn="Etternavn";
	String mobil="11111111";
	String kjonn="mann";

	String fornavnFeilmelding="";
	String etternavnFeilmelding="";
	String mobilFeilmelding="";
	String kjonnFeilmelding="";

	
    @Before
	public final void setup() throws Exception {
    	ps = new PersonSkjema();
    	ps.setFornavn(fornavn);
    	ps.setEtternavn(etternavn);
    	ps.setMobil(mobil);
    	ps.setKjonn(kjonn);
    	ps.setRegisterertFrafoer(false);

    }

	
	@Test
	public void testAllInputGyldig() {
		assertTrue(ps.erAllInputGyldig());
	}
	@Test
	public void testIsValidFornavn() {
		ps.setFornavn("A");
		assertFalse(ps.erAllInputGyldig());
		ps.setFornavn("aA");
		assertFalse(ps.erAllInputGyldig());
		ps.setFornavn(" ");
		assertFalse(ps.erAllInputGyldig());
		ps.setFornavn("@yolo");
		assertFalse(ps.erAllInputGyldig());
		ps.setFornavn("A a");
		assertTrue(ps.erAllInputGyldig());
	}

	@Test
	public void testisValidEtterNavn() {
		ps.setEtternavn("A");
		assertFalse(ps.erAllInputGyldig());
		ps.setEtternavn("aA");
		assertFalse(ps.erAllInputGyldig());
		ps.setEtternavn("A a");
		assertFalse(ps.erAllInputGyldig());
		ps.setEtternavn("A_a@");
		assertFalse(ps.erAllInputGyldig());
		ps.setEtternavn("A_a");
		assertTrue(ps.erAllInputGyldig());
	}

	@Test
	public void testisValidMobil() {
		assertFalse(ps.isValidMobil("1234567"));
		assertFalse(ps.isValidMobil("12345678a"));
		assertFalse(ps.isValidMobil("12345 67"));
		assertFalse(ps.isValidMobil("1234567 "));
		assertTrue(ps.isValidMobil("12345678"));

	}
	
	@Test
	public void testisValidKjonn() {
		assertFalse(ps.isValidKjonn(null));
		assertTrue(ps.isValidKjonn("mann"));
		assertTrue(ps.isValidKjonn("kvinne"));

	}

}
