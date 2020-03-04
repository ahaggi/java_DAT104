package Servlets;

import java.util.ArrayList;
import java.util.List;

import javaKode.Alternative;

public class Question {
	private String beskrivelse;
	private List<Alternative> alternatives = new ArrayList<Alternative>();
	
	public Question(String beskrivelse, List<Alternative> alternatives) {
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
	public List<Alternative> getAlternatives() {
		return alternatives;
	}
	public void setAlternatives(List<Alternative> alternatives) {
		this.alternatives = alternatives;
	}
	
}

//Kan bruke denne for textQuestion og. Forskjelen er at alternativene blir lagt til når en bruker skriver inn et svar. 
//Må ha en test i DynamiskTest.jsp om det er mcQuestion eller textQuestion 
//Ha en variabel i Question som heter type?
