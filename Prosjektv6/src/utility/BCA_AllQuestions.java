package utility;

import java.util.HashMap;

 
import model.Survey;
import model.question.Question;
import model.question.TQinterface;
  
public class BCA_AllQuestions {
	HashMap<Integer, BubbleChartAnalyse> bcaMapAllQuestions = new HashMap<Integer, BubbleChartAnalyse>();

	public BCA_AllQuestions(Survey survey) {
		super();
		for (Question q : survey.getQuestions()) {
			if (q instanceof TQinterface) {
				BubbleChartAnalyse bca= new BubbleChartAnalyse(q.getAnswers());
				this.bcaMapAllQuestions.put(q.getId(), bca);
			}
		}
 	}

	public HashMap<Integer, BubbleChartAnalyse> getBcaMapAllQuestions() {
		return bcaMapAllQuestions;
	}

 
	public WordCounter[] getTableSortedByFrequency(int q_Id) {
		WordCounter[] wordtable =(bcaMapAllQuestions.get(q_Id) !=null)? bcaMapAllQuestions.get(q_Id).getTableSortedByFrequency() : null ;
 		return wordtable;
	}

 
	public WordCounter[] getTableSortedByuniqueAppearances(int q_Id) {
		WordCounter[] wordtable =(bcaMapAllQuestions.get(q_Id)!=null)? bcaMapAllQuestions.get(q_Id).getTableSortedByuniqueAppearances() : null ;
		return wordtable;
	}

	public int getNrOfWords(int q_Id){
		int antall=0;
		antall= (bcaMapAllQuestions.get(q_Id)!=null)? bcaMapAllQuestions.get(q_Id).getMapWords().size() : 0 ;

		
		return antall;
	}
	
	
}
