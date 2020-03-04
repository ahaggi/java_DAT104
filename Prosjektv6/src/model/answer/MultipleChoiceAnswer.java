package model.answer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
 

@Entity
@Table(schema="questionnaire_joined", name="multiplechoiceanswer")

public class MultipleChoiceAnswer extends Answer {
	
	@ElementCollection
	@CollectionTable(name ="questionnaire_joined.answeredoptions")
	@Column(name="mcoption_id")
    @OrderBy("id ASC")
	// Liste over options som er svart
	private List<Integer> options = new ArrayList<Integer>();

	public MultipleChoiceAnswer() {
		super();
 	}

	public List<Integer> getOptions() {
		return options;
	}

	public void setOptions(List<Integer> choices) {
		this.options = choices;
	}
	
	public void addOption(Integer option) {
		  options.add(option);
	}

	public void removeOption(int index) {
		options.remove(index);
	}
	
	/**
	 * Finds option in multiple choice answer.
	 * @param option
	 * @return the option in specified position.
	 */
	public Integer findOption(Integer option) {
		Integer opt;
	    for (Integer o : options) {
	        if (o==(option)) {
	        	opt= o;
	        	return opt;
	        }
	    }
	    return null;
	}

	@Override
	public void addAnswer(String answer) {
		String[] ans = answer.split(",");
		for (String s : ans) {
			addOption(Integer.parseInt(s));
		}
	}
}
