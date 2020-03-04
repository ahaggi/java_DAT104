package model;

public class Answer {
	private String beskrivelse;
	private Integer AntallStemmer;
	

	
	public Answer(String beskrivelse, Integer antallStemmer) {
		super();
		this.beskrivelse = beskrivelse;
		AntallStemmer = antallStemmer;
	}
	public String getBeskrivelse() {
		return beskrivelse;
	}
	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

	public Integer getAntallStemmer() {
		return AntallStemmer;
	}

	public void setAntallStemmer(Integer antallStemmer) {
		AntallStemmer = antallStemmer;
	}
	
	
	
}
