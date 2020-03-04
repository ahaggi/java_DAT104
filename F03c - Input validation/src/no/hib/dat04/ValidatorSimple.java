package no.hib.dat04;

public class ValidatorSimple {
	
	public boolean isValidStudentId(String s) {
		
		if (s == null) {
			return false;
		}
		if (s.length() != 7) {
			return false;
		}
		if (s.charAt(0) != 'h') {
			return false;
		}
		if (!containsOnlyDigits(s.substring(1))) {
			return false;
		}
		
		return true;
	}

	private boolean containsOnlyDigits(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
