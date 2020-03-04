package model.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.*;
import model.answer.*;

@Entity
@Table(schema="questionnaire_joined", name="multiplechoicequestion")

public class MultipleChoiceQuestion extends Question implements MCQinterface{
	
	private Boolean singleAnswer;
	
	@ElementCollection
	@CollectionTable(name ="questionnaire_joined.mcoption") //funket når eg endret navn fra "mcoption" til "questionnaire_joined.mcoption" av en eller annen grunn
	@Column(name="options")
    @OrderBy("id ASC")
	private List<String> options = new ArrayList<String>();

	public MultipleChoiceQuestion() {
		super();
 	}

	public MultipleChoiceQuestion(String text,boolean singleAnswer) {
		super(text);
		this.singleAnswer = singleAnswer;
 	}
	
	public void addAnswer(MultipleChoiceAnswer answer) {
		  super.getAnswers().add(answer);
	}

	public void removeAnswer(MultipleChoiceAnswer answer) {
		super.getAnswers().remove(answer);
	}

	public MultipleChoiceAnswer findAnswer(Integer id) {
		MultipleChoiceAnswer answer;
	    for (Answer a : super.getAnswers()) {
	        if (a.getId()==(id)) {
	        	answer=(MultipleChoiceAnswer) a;
	        	return answer;
	        }
	    }
	    return null;
	}

	  public void addOption(String option) {
		  options.add(option);
	}

	public void removeOption(String option) {
		options.remove(option);
	}

	public String findOption(String option) {
		String opt;
	    for (String o : options) {
	        if (o.equals(option)) {
	        	opt= o;
	        	return opt;
	        }
	    }
	    return null;
	}
	
	/* Getters & setters */
	public Boolean getSingleAnswer() {
		return singleAnswer;
	}

	public void setSingleAnswer(Boolean singleAnswer) {
		this.singleAnswer = singleAnswer;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	/**
	 * Get the type of answer
	 * @return answer type as string
	 */
	@Override
	public String getType() {
 		return "mc";
	}
	
	public HashMap<String, Integer> generateHashMap() {
		HashMap<String, Integer> answers = new HashMap<String, Integer>();
		
		for (int i = 0; i < options.size(); i++) {
			answers.put(options.get(i), 0);
		}
		
		for (int i = 0; i < super.getAnswers().size(); i++) {
			MultipleChoiceAnswer answer = (MultipleChoiceAnswer) super.getAnswers().get(i);
			for (int j = 0; j < answer.getOptions().size(); j++) {
				int answeredOption = answer.getOptions().get(j);
				answers.put(options.get(answeredOption), answers.get(options.get(answeredOption)) + 1);
			}
		}
		
		return answers;
	}
}
