package no.hib.dat014;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import no.hib.dat04.ValidatorRegExp;

public class ValidatorTest {
	
	private ValidatorRegExp validator = new ValidatorRegExp();
	
	@Test
	public void nullStringIsInvalidStudentId() {
		assertFalse(validator.isValidStudentId(null));
	}
	
	@Test
	public void emptyStringIsInvalidStudentId() {
		assertFalse(validator.isValidStudentId(""));
	}
	
	@Test
	public void sixNumbersIsInvalidStudentId() {
		assertFalse(validator.isValidStudentId("123456"));
	}
	
	@Test
	public void sevenNumbersIsInvalidStudentId() {
		assertFalse(validator.isValidStudentId("1234567"));
	}
	
	@Test
	public void hPlusFiveNumbersIsInvalidStudentId() {
		assertFalse(validator.isValidStudentId("h12345"));
	}
	
	@Test
	public void hPlusSevenNumbersIsInvalidStudentId() {
		assertFalse(validator.isValidStudentId("h1234567"));
	}
	
	@Test
	public void hPlusSixNotOnlyNumbersIsInvalidStudentId() {
		assertFalse(validator.isValidStudentId("h123x56"));
	}
	
	@Test
	public void HPlusSixNumbersIsInvalidStudentId() {
		assertFalse(validator.isValidStudentId("H123456"));
	}
	
	@Test
	public void hPlusSixNumbersIsValidStudentId() {
		assertTrue(validator.isValidStudentId("h123456"));
	}
	
}
