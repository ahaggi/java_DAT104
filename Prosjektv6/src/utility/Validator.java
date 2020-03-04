package utility;

import org.apache.commons.lang.StringEscapeUtils;

public class Validator {

	private static String emailRegex = "(.+@.+)";
	private static String passwordRegex = "([^a-zA-Z0-9_-])";
	private static String integerRegex = "([^0-9]{1,})";

	/**
	 * Checks if userName is an email address.
	 * 
	 * @param String
	 *            useName
	 * @return boolean
	 */
	public static boolean isValidUsername(String username) {
		if (username != null) {
			username = StringEscapeUtils.escapeSql(username);
			username = StringEscapeUtils.escapeHtml(username);
			username = StringEscapeUtils.escapeJavaScript(username);
			return username.matches(emailRegex);
		}
		return false;
	}

	/**
	 * Validate the entered password for an existing user.
	 * 
	 *
	 * @param String
	 *            password
	 * @return boolean
	 */

	public static boolean isValidPassword(String password) {
		if (password != null && !password.matches(passwordRegex) && password.length() >= 8
				&& password.length() <= 500) {
			password = StringEscapeUtils.escapeSql(password);
			password = StringEscapeUtils.escapeHtml(password);
			password = StringEscapeUtils.escapeJavaScript(password);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Validate the entered integer.
	 *
	 * 
	 * @param String
	 *            input
	 * @return boolean
	 */

	public static boolean isValidInteger(String input) {
		if (!input.matches(integerRegex)) {
			input = StringEscapeUtils.escapeSql(input);
			input = StringEscapeUtils.escapeHtml(input);
			input = StringEscapeUtils.escapeJavaScript(input);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Escapes SQL and HTML injection on input text. Checks the amount of
	 * characters in input text.
	 * 
	 * @param String
	 *            text
	 * @return boolean
	 */
	public static boolean isValidInput(String text) {
		if (text != null && text.length() <= 500 && text.length() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Sørger for at det escapes SQL, HTML og JavaScript for å forebygge injection attacks.
	 * 
	 * @param text Input
	 * @return formatert text dersom den ikke er null
	 */
	public static String makeValidInput(String text) {
		if (text != null && text.length() <= 500 && text.length() > 0) {
			text = StringEscapeUtils.escapeSql(text);
			text = StringEscapeUtils.escapeHtml(text);
			text = StringEscapeUtils.escapeJavaScript(text);
			
			text = text.replaceAll(StringEscapeUtils.escapeHtml("ø"), "ø");
			text = text.replaceAll(StringEscapeUtils.escapeHtml("æ"), "æ");
			text = text.replaceAll(StringEscapeUtils.escapeHtml("å"), "å");
			text = text.replaceAll(StringEscapeUtils.escapeHtml("Ø"), "Ø");
			text = text.replaceAll(StringEscapeUtils.escapeHtml("Æ"), "Æ");
			text = text.replaceAll(StringEscapeUtils.escapeHtml("Å"), "Å");
			
			return text;
		} else {
			return null;
		}
	}

	/**
	 * parse a string value to an integer or return -1
	 * 
	 * @param x
	 *            input as string
	 * @return The value of x, or -1
	 */
	public static int toInt(String x) {
		int num;
		try {
			num = Integer.valueOf(x);
		} catch (Exception e) {
			num = -1;
		}
		return num;

	}
}