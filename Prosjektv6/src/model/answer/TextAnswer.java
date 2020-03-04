package model.answer;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema="questionnaire_joined", name="textanswer")
public class TextAnswer extends Answer implements TextAnswerInterface{

	private String text;

	public TextAnswer() {
		super();
 	}

 	public TextAnswer(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public void addAnswer(String answer) {
		setText(answer);
	}
}
