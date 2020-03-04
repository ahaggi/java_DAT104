package model;

import java.util.ArrayList;
import java.util.List;

public class Survey {
	private String navn;
	private List<Question> questions = new ArrayList<Question>();

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}
	
}
