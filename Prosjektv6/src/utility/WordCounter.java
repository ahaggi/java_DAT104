package utility;

public class WordCounter {

	private String text;
	private int frq;
	private int uniqueAppearances;
	private int flag;
	
	public WordCounter(String text) {
		super();
		this.text = text;
		this.frq = 0;
		this.uniqueAppearances = 0;
		this.flag=-1;
	}

	public void increaseFrq() {
		frq++;
	}

	public void increaseNumberOfappearance() {
		uniqueAppearances++;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

 
	public int getFrq() {
		return frq;
	}

	public void setFrq(int frq) {
		this.frq = frq;
	}


	public int getUniqueAppearances() {
		return uniqueAppearances;
	}

	public void setUniqueAppearances(int uniqueAppearances) {
		this.uniqueAppearances = uniqueAppearances;
	}

	public int compareToFrq(WordCounter o) {
		return this.frq - o.frq;
	}
	public int compareToApp(WordCounter o) {
		return this.uniqueAppearances - o.uniqueAppearances;
	}

}
