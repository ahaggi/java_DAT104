package utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Lecturer;

/**
 * @author Peter
 * 
 * Authenticator class to:
 * 1. Login
 * 2. Logout
 * 3. Confirm if user is logged in.
 *
 */
public class Authenticator {
	
	/**
	 * Login a user if the provided details are correct after validation.
	 * 
	 * @param The HttpServletRequest request
	 * @param Lecturer l (the lecturer object returned after calling EAO.getLecturer(request.get("username"));
	 * 
	 * @returns boolean: if the user is logged in
	 */
	
	//public boolean sjekkPassord(String passord, String kryptert) {
		
	public static boolean login(HttpServletRequest request, Lecturer l) {
		
		String password = request.getParameter("password");


		// Sjekk p� nullverdi
		if (l == null || password == null) {
			return false;
		// Sjekk om login info er gyldige (forhindre HTML og SQL injection angrep)
		} else if (Validator.isValidPassword(password)) {
			// Sjekk om angit pasord matcher lagret passord
			if (PassordUtil.sjekkPassord(password, l.getPassword())){
				HttpSession session = request.getSession(true);
				session.setAttribute("username", l.getUsername());
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Logs out a user
	 * 
	 * @param HttpServletRequest request
	 */
	public static void logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
	}

	/**
	 * Checks if the user is logged inn and authenticated
	 * 
	 * @param HttpServletRequest request
	 * @param Lecturer l
	 * 
	 * @return boolean: if the user is logged in or not
	 */

	public static boolean isAuthenticated(HttpServletRequest request, Lecturer l) {
		
		if (request.getSession(false) != null) {
			HttpSession sesjon = request.getSession(false);
			String username = (String) sesjon.getAttribute("username");

			if (l != null && username != null) {
				// Hent session brukernavn 
				
				// check if session username == database username
				if (l.getUsername().equals(username)) {
					return true;
				}
			}
		}
		return false;
	}

}
