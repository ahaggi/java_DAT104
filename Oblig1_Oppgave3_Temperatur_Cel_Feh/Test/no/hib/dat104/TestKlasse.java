package no.hib.dat104;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;

public class TestKlasse {
	

	@Test
	public void testRegnom1() {
//		assertEquals(double expected, double actual, double delta)
		assertEquals( 71.6, Regnom.regn("22", "1") , 0.01 );
		
	}
	
	@Test
	public void testRegnom2() {
//		assertEquals(double expected, double actual, double delta)
		assertEquals( 22, Regnom.regn("71.6", "2") , 0.01 );
		
	}

	@Test
	public void tempErUgyldig() {
		assertFalse(Regnom.validate("", "3"));
		assertFalse(Regnom.validate("", "1"));
		assertFalse(Regnom.validate("", "2"));
		assertTrue(Regnom.validate("22", "1"));
		assertTrue(Regnom.validate("22.", "2"));
	}

}
