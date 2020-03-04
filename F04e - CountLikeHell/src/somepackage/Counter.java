package somepackage;

public class Counter {

	private int verdi = 0;

	public synchronized void increment(int i) {
			verdi = verdi + i;
	}

	public int getVerdi() {
		return verdi;
	}

}
