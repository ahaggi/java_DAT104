package model;

import java.util.ArrayList;
import java.util.List;

public class GenererData {
	
	public static void generer(Survey s) {
		
		
		List<Answer> alternatives = new ArrayList<Answer>();
		alternatives.add(new Answer("Rød", 0));
		alternatives.add(new Answer("Grønn", 0));
		alternatives.add(new Answer("blå", 0));
		
		Question en = new Question("Hva er favoritt fargen din?", alternatives);
		
		List<Answer> alternatives2 = new ArrayList<Answer>();
		alternatives2.add(new Answer("Taco", 0));
		alternatives2.add(new Answer("Pizza", 0));
		alternatives2.add(new Answer("Grønnsaker", 0));
		
		Question to = new Question("Hva liker du best?", alternatives2);
		
//		List<Alternative> alternatives3 = new ArrayList<Alternative>();
//		alternatives3.add(new Alternative("4", 0));
//		alternatives3.add(new Alternative("234", 0));
//		
//		Question tre = new Question("Hva er 2+2?", alternatives3);
		
		s.getQuestions().add(en);
		s.getQuestions().add(to);
//		s.getQuestions().add(tre);
	}
}
