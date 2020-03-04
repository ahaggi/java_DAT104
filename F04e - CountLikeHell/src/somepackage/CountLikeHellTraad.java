package somepackage;

public class CountLikeHellTraad implements Runnable {

	private int id;
	private Counter counter;
	private int iterations;
	
	public CountLikeHellTraad(int id, Counter counter, int iterations) {
		this.id = id;
		this.counter = counter;
		this.iterations = iterations;
	}

	@Override
	public void run() {
		for (int i=1; i<=iterations; i++) {
			counter.increment(1);
		}
		System.out.println("CountLikeHell " + id + " ferdig !!!");
	}
	
}
