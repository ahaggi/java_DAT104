package hib.no;


public class EksemplePaaBrukAvPassordUtil {
    public static void main(String[] args) {

        PassordUtil passordUtil = new PassordUtil();

        String mittPassord = "123";
        String dittPassord = "hemmelig";

        String mittSaltPlussDigest = passordUtil.krypterPassord(mittPassord);
        String dittSaltPlussDigest = passordUtil.krypterPassord(dittPassord);

        System.out.println("Kryptert '" + mittPassord + "': "
                + mittSaltPlussDigest);
        System.out.println("Kryptert '" + dittPassord + "': "
                + dittSaltPlussDigest);

        System.out.print("Sjekk av 123 mot mitt lagrede passord: ");
        if (passordUtil.sjekkPassord("123", mittSaltPlussDigest)) {
            System.out.println("MATCH");
        } else {
            System.out.println("IKKE MATCH");
        }

        System.out.print("Sjekk av 123 mot ditt lagrede passord: ");
        if (passordUtil.sjekkPassord("123", dittSaltPlussDigest)) {
            System.out.println("MATCH");
        } else {
            System.out.println("IKKE MATCH");
        }
    }
}
