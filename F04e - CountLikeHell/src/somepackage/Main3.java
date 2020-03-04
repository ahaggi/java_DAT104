package somepackage;

/* I stedet for � ha to tr�der har vi 100. Metodekall til start().
 * De kj�res derfor parallelt som separate tr�der. G�r dette bra ???
 */
public class Main3 {

	public static void main(String[] args) throws InterruptedException {
		
		int NUMBER_OF_THREADS = 10;
		int ITERATIONS = 10000;
		
		Counter counter = new Counter();
		
		for (int i=0; i<NUMBER_OF_THREADS; i++) {
			new Thread(new CountLikeHellTraad(i, counter, ITERATIONS)).start();
		}
		
		Thread.sleep(200);
		
		System.out.println("Fra Main: counter = " + counter.getVerdi());
		System.out.println("  Forventet verdi = " + NUMBER_OF_THREADS * ITERATIONS);
		
	}

}
