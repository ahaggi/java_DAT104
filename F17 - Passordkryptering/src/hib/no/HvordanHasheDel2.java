package hib.no;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

public class HvordanHasheDel2 {

    public static void main(String[] args) throws UnsupportedEncodingException,
            NoSuchAlgorithmException {
    	
////    	final String HASH_FUNCTION = "MD5";
    	final String HASH_FUNCTION = "SHA-256";
//    	
    	final int SALTLENGDE = 8;
//    	
    	final int ITERASJONER = 1000;
//    	
//    	final String encoding = "Hex";
    	final String encoding = "Base64";
//    	
//    	
//        String passord = "Hei";
        String passord = "Hallaien";
////        String passord = "pwd123";
////        String passord = "Peace&Love";
////        String passord = "4everSecure!";
//
        byte[] pwdBytes = passord.getBytes("UTF-8");
//
        System.out.println("Passord: " + passord);
        System.out.println("Passord som bytes: " + Arrays.toString(pwdBytes));
//
        byte[] salt = new byte[SALTLENGDE];
        new SecureRandom().nextBytes(salt);
//        
        System.out.println("Salt: " + Arrays.toString(salt));
//
        byte[] saltPlussPassord = new byte[salt.length + pwdBytes.length];
        System.arraycopy(salt, 0, saltPlussPassord, 0, salt.length);
        System.arraycopy(pwdBytes, 0, saltPlussPassord, salt.length, pwdBytes.length);
        
        System.out.println("Salt+Passord: " + Arrays.toString(saltPlussPassord));
        
        MessageDigest md = MessageDigest.getInstance(HASH_FUNCTION);
        
        byte[] digest = saltPlussPassord;
        for (int i=1; i<=ITERASJONER; i++) {
            digest = md.digest(digest);
        }
        
        System.out.println("Hashet med " + HASH_FUNCTION + ": " + Arrays.toString(digest));
        
        String kodetDigest = "";
        if (encoding.equalsIgnoreCase("Hex")) {
        	kodetDigest = DatatypeConverter.printHexBinary(digest);
            System.out.println("Hex-kodet digest: " + kodetDigest);
            
        } else if (encoding.equalsIgnoreCase("Base64")) {
        	kodetDigest = DatatypeConverter.printBase64Binary(digest);
            System.out.println("Base64-kodet digest: " + kodetDigest);
            
        } else {
            System.out.println("Invalid encoding: " + encoding);
        }
    }

}










