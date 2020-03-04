/**
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utility.Validator;

/**
 * @author Hawkon
 *
 */
public class ValidatorTest {

	private String validUN;
	private String validPW;
	private String invalidUN;
	private String invalidPW;
	private String validInput;
	private String invalidInput1;
	private String invalidInput2;
	private String validInt;
	private String invalidInt;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		validUN = "hhl1987@hotmail.com";
		validPW = "dennorskebank123";
		validInput = "Hei, dette er en prøve. Dette er alternativene:";
		validInt = "3";
		invalidUN = "123";
		invalidPW = "hal1";
		// This input is now vallid
		invalidInput1 = "";
		// New invallid input
		invalidInput2 = "kkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekekekkkekekekeke";
		invalidInt = "hei";
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		validUN = null;
		validPW = null;
		validInput = null;
		validInt = null;
		invalidUN = null;
		invalidPW = null;
		invalidInput1 = null;
		invalidInput2 = null;
		invalidInt = null;
	}

	@Test
	public void testIsValidUsername() {
		assertTrue(Validator.isValidUsername(validUN));
	}

	@Test
	public void testIsInvalidUsername() {
		assertFalse(Validator.isValidUsername(invalidUN));
	}

	@Test
	public void testIsValidPassword() {
		assertTrue(Validator.isValidPassword(validPW));
	}

	@Test
	public void testIsInvalidPassword() {
		assertFalse(Validator.isValidPassword(invalidPW));
	}

	@Test
	public void testIsValidInput() {
		assertTrue(validInput.compareTo(Validator.makeValidInput(validInput)) == 0);
	}

	@Test
	public void testIsInvalidInputForKort() {
		assertFalse(invalidInput1.compareTo(Validator.makeValidInput(invalidInput1)) == 0);
	}
	
	@Test
	public void testIsInvalidInputForLang() {
		assertFalse(invalidInput2.compareTo(Validator.makeValidInput(invalidInput2)) == 0);
	}
	
	@Test
	public void testIsValidIntegerFromString(){
		assertTrue(Validator.isValidInteger(validInt));
	}
	
	@Test
	public void testIsInvalidIntegerFromString(){
		assertFalse(Validator.isValidInteger(invalidInt));
	}

}
