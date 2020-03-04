package model;

import java.util.ArrayList;
import java.util.List;

public class Question {
	private String beskrivelse;
	private List<Answer> alternatives = new ArrayList<Answer>();
	
	public Question(String beskrivelse, List<Answer> alternatives) {
		super();
		this.beskrivelse = beskrivelse;
		this.alternatives = alternatives;
	}
	public String getBeskrivelse() {
		return beskrivelse;
	}
	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}
	public List<Answer> getAlternatives() {
		return alternatives;
	}
	public void setAlternatives(List<Answer> alternatives) {
		this.alternatives = alternatives;
	}
	
}

//Kan bruke denne for textQuestion og. Forskjelen er at alternativene blir lagt til når en bruker skriver inn et svar. 
//Må ha en test i DynamiskTest.jsp om det er mcQuestion eller textQuestion 
//Ha en variabel i Question som heter type?
