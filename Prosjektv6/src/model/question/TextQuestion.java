package model.question;

import javax.persistence.*;

@Entity
@Table(schema="questionnaire_joined", name="textquestion")
public class TextQuestion extends Question implements TQinterface{

	private Integer minLength;
	private Integer maxLength;
	
	public TextQuestion() {
		super();
	}
	
	public TextQuestion( String text, Integer minLength, Integer maxLength) {
		super(text);
		this.minLength = minLength;
		this.maxLength = maxLength;
	}
	
	
	
	/* Getters & setters */
	public Integer getMinLength() {
		return minLength;
	}
	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}
	public Integer getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	/**
	 * Get the type of answer
	 * @return answer type as string
	 */
	@Override
	public String getType() {
	 	return "tq";
	}
}
