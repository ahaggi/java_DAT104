package hib.no;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

public class HvordanHasheDel1 {

	public static void main(String[] args)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {

//		final String HASH_FUNCTION = "MD5";
        final String HASH_FUNCTION = "SHA-256";

		String noenPassord[] = { "Hei", "Hallaien", "pwd123", "Peace&Love",
				"4everSecure!" };

		for (String passord : noenPassord) {
			byte[] passordSomBytes = passord.getBytes("UTF-8");

			System.out.println(
					"-------------------------------------------------------");
			System.out.println("Passord: " + passord);
			System.out.println(
					"Passord som bytes: " + Arrays.toString(passordSomBytes));

			MessageDigest md = MessageDigest.getInstance(HASH_FUNCTION);

			byte[] digest = md.digest(passordSomBytes);

			System.out.println("Digest (" + HASH_FUNCTION + "): "
					+ Arrays.toString(digest));

			String base64Digest = DatatypeConverter.printBase64Binary(digest);
			String hexDigest = DatatypeConverter.printHexBinary(digest);

			System.out.println("Base64-kodet: " + base64Digest);
			System.out.println("Hex-kodet: " + hexDigest);
		}
	}

}
