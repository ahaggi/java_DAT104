package Servlets;

import java.util.ArrayList;
import java.util.List;

import javaKode.Alternative;

public class GenererData {
	
	public static void generer(Survey s) {
		
		
		List<Alternative> alternatives = new ArrayList<Alternative>();
		alternatives.add(new Alternative("Rød", 0));
		alternatives.add(new Alternative("Grønn", 0));
		alternatives.add(new Alternative("Blå", 0));
		
		Question en = new Question("Hva er favoritt fargen din?", alternatives);
		
		List<Alternative> alternatives2 = new ArrayList<Alternative>();
		alternatives2.add(new Alternative("Taco", 0));
		alternatives2.add(new Alternative("Pizza", 0));
		alternatives2.add(new Alternative("Grønnsaker", 0));
		
		Question to = new Question("Hva liker du best?", alternatives2);
		
		List<Alternative> alternatives3 = new ArrayList<Alternative>();
		alternatives3.add(new Alternative("4", 0));
		alternatives3.add(new Alternative("234", 0));
		
		Question tre = new Question("Hva er 2+2?", alternatives3);
		
		List<Alternative> alternatives4 = new ArrayList<Alternative>();
		alternatives4.add(new Alternative("Trump", 0));
		alternatives4.add(new Alternative("Hillary", 0));
		
		Question fire = new Question("Hvem vinner valget?", alternatives4);
		
		s.getQuestions().add(en);
		s.getQuestions().add(to);
		s.getQuestions().add(tre);
		s.getQuestions().add(fire);
	}
}
