
package model.question;

import model.answer.Answer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(schema = "questionnaire_joined", name = "question")

@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "class_name")
public abstract class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String text;

	@OneToMany(cascade = CascadeType.ALL,orphanRemoval=true)
	@JoinColumn(name = "question", referencedColumnName = "id")
	@OrderBy("id ASC")
	private List<Answer> answers =new ArrayList<Answer>();

	public Question() {
		super();
	}

	public Question(String text) {
		super();
		this.text = text;
	}
	
	public void addAnswer(Answer answer) {
		  this.answers.add(answer);
	}
	
	public void removeAnswer(Answer answer) {
		this.answers.remove(answer);
	}

	public Integer getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	/**
	 * Get the type of answer
	 * @return answer type as string
	 */
	public abstract String getType() ;
	
}
