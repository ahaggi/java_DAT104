package model.answer;


import javax.persistence.*;

@Entity
@Table(schema="questionnaire_joined", name="answer")

@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "class_name")
public abstract class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id;
	
	public Answer() {
		super();
 	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public abstract void addAnswer(String answer);
	
	/**
	 * Get the type of answer
	 * @return answer type as string
	 */
	public String getType() {
		return this.getClass().getSimpleName();
	}
}
