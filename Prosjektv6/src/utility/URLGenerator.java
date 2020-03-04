package utility;

/**
 * Address generator class. Editor a URL to connect to survey Reconstruct a URL
 * to a surveyId
 */

public class URLGenerator {

	/**
	 * Generates URL address to a survey.
	 * 
	 * @param String
	 *            SurveyId not Null
	 * @return String Url
	 */

	public static String getEncryptedSurveyId(String surveyId) {
		int temp;
		// gj�re noe med integeren
		try {
		temp = Integer.parseInt(surveyId) * 97;
		} catch (NumberFormatException e) {
			return "-1";
		}
		// konvertere til hexsadesimal
		String Url = Integer.toHexString(temp);

		return Url;
	}

	/**
	 * Returns the surveyId to the specified URL Decodes the url. No checks for
	 * the existance of the survey.
	 * 
	 * @param the
	 *            URL for a survey not Null
	 * @return String SurveyId
	 */
	public static int getDecryptedSurveyId(String URL) {
		int value;
		// konverter fra hexadesimal til ineger
		try {
		value = Integer.parseUnsignedInt(URL, 16);
		} catch (NumberFormatException e){
			return -1;
		}
		// deler p� 97 for � f� original Survey Id
		return (value/97);
	}

}
