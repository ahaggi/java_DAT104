package no.hib.dat04;

public class ValidatorBetter {
	
	public boolean isValidStudentId(String s) {
		
		return s != null 
				&& s.length() == 7 
				&& s.startsWith("h") 
				&& containsOnlyDigits(s.substring(1));
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
