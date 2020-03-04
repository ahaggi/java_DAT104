package hib.no;


public class BruteForceAngrep {

    public static void main(String[] args) {
        
        PassordUtil passordUtil = new PassordUtil();

        String passord = "abc";
        
        String kryptert = passordUtil.krypterPassord(passord);

        long start = System.currentTimeMillis();
        
        boolean ferdig = false;
        for (char c1='0'; c1<='z' && !ferdig; c1++) {
            System.out.print(".");
            for (char c2='0'; c2<='z' && !ferdig; c2++) {
                for (char c3='0'; c3<='z' && !ferdig; c3++) {
                	
                    String p = "" + c1 + c2 + c3;
                    if (passordUtil.sjekkPassord(p, kryptert)) {
                        System.out.println("\nPassordet er " + p);
                        ferdig = true;
                    }
                    
                }
            }
        }
        
        long found = System.currentTimeMillis();
        
        System.out.println("Tid i sekunder: " + (found-start)/1_000);
    }
}
