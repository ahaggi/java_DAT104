package somepackage;

/* Bruker to "tr�dobjekter", men gj�r et vanlig metodekall til run().
 * De kj�res derfor ikke som tr�der, og t1 venter til t0 er ferdig.
 */
public class Main1 {

	public static void main(String[] args) throws InterruptedException {
		
		int ITERATIONS = 10000;
		
		Counter counter = new Counter();
		
		CountLikeHellTraad t0 = new CountLikeHellTraad(0, counter, ITERATIONS);
		CountLikeHellTraad t1 = new CountLikeHellTraad(1, counter, ITERATIONS);
		
		t0.run();
		t1.run();
		
		Thread.sleep(100);
		
		System.out.println("Fra Main: counter = " + counter.getVerdi());
		System.out.println("  Forventet verdi = " + 2 * ITERATIONS);
		
	}

}
