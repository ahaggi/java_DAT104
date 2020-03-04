package somepackage;

/* Bruker to tråder av typen CountLikeHellTraad. Metodekall til start().
 * De kjøres derfor parallelt som separate tråder. Går dette bra ???
 */
public class Main2 {

	public static void main(String[] args) throws InterruptedException {
		
		int ITERATIONS = 10000;
		
		Counter counter = new Counter();
		
		Thread t0 = new Thread(new CountLikeHellTraad(0, counter, ITERATIONS));
		Thread t1 = new Thread(new CountLikeHellTraad(1, counter, ITERATIONS));
		
		t0.start();
		t1.start();
		
		Thread.sleep(100);
		
		System.out.println("Fra Main: counter = " + counter.getVerdi());
		System.out.println("  Forventet verdi = " + 2 * ITERATIONS);
		
	}

}
