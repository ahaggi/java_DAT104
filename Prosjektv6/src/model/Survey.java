package model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import model.question.Question;
import utility.URLGenerator;

/**
 * Survey contains Questions.
 *
 */
@Entity
@Table(schema = "questionnaire_joined", name = "survey")
public class Survey {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	private Boolean repeatable;
	private Boolean traversable;
	private Long length;
	private Long enddate;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "survey", referencedColumnName = "id")
	@OrderBy("id ASC")
	private List<Question> questions = new ArrayList<Question>();

	public Survey() {
		super();
	}

	/**
	 * Creates a new Survey object.
	 * @param name
	 * @param description
	 * @param repeatable
	 * @param traversable
	 * @param length
	 */
	public Survey(String name, String description, Boolean repeatable, Boolean traversable, Long length) {
		super();
		this.name = name;
		this.description = description;
		this.repeatable = repeatable;
		this.traversable = traversable;
		this.length = length;
		this.enddate = null;

	}

	/**
	 * Publishes survey
	 */
	public void publish() {
		if (!isPublished()) {
			if (length.equals(0L)) {
				this.enddate = 2109515409101L;
			} else {
				this.enddate = Instant.now().toEpochMilli() + this.length;
			}
		}
	}

	/**
	 * Closes a published survey
	 */
	public void close() {
		this.enddate = Instant.now().toEpochMilli() - 10;
	}

	/**
	 * @return Whether or not a survey is open.
	 */
	public Boolean isOpen() {
		if (isPublished()) {
			return Instant.now().toEpochMilli() < this.enddate;
		}
		return false;
	}

	/**
	 * @return Questions in survey
	 */
	public List<Question> getQuestions() {
		return questions;
	}

	/**
	 * Sets the surveys question list to a specified question list.
	 * @param questions
	 */
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	/**
	 * Adds a question to the surveys list of questions.
	 * @param question
	 */
	public void addQuestion(Question question) {
		this.questions.add(question);
	}

	/**
	 * Removes a question from the surveys question list.
	 * @param question
	 */
	public void removeQuestion(Question question) {
		this.questions.remove(question);
	}

	/**
	 * @param index Position of the question you'd like to find.
	 * @return Question in specified position in list.
	 */
	public Question getQuestion(int index) {
		Question question;
		question = (index < questions.size()) ? questions.get(index) : null;
		return question;

	}

	/**
	 * @param id
	 * @return
	 */
	public Question findQuestion(Integer id) {
		Question question;
		for (Question q : questions) {
			if (q.getId() == (id)) {
				question = q;
				return question;
			}
		}
		return null;
	}
	
	/*  */
	public String getEncryptedSurveyId() {
		return URLGenerator.getEncryptedSurveyId(this.getId().toString());
	}

	/* IS-es */
	/**
	 * @return Whether or not a survey is repeatable. 
	 */
	public Boolean isRepeatable() {
		return this.repeatable;
	}

	/**
	 * @return Whether or not a survey is traversable.
	 */
	public Boolean isTraversable() {
		return this.traversable;
	}

	/**
	 * @return Whether or not a survey is published.
	 */
	public Boolean isPublished() {
		return (enddate != null);
	}

	/* GETTERS AND SETTERS */
	/**
	 * @return The ID of server
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets ID of survey.
	 * @param id The new ID.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Name of Survey.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Renames the Survey.
	 * @param name The new name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return The description of the Survey.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description Sets the description of the Survey.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return Whether or not a survey is repeatable.
	 */
	public Boolean getRepeatable() {
		return repeatable;
	}

	/**
	 * Sets the surveys repeatable setting.
	 * @param repeatable
	 */
	public void setRepeatable(Boolean repeatable) {
		this.repeatable = repeatable;
	}

	/**
	 * @return Whether or not a survey is traverable.
	 */
	public Boolean getTraversable() {
		return traversable;
	}

	/**
	 * Sets the surveys traverable setting.
	 * @param traversable
	 */
	public void setTraversable(Boolean traversable) {
		this.traversable = traversable;
	}

	/**
	 * @return returns the length of Survey.
	 */
	public Long getLength() {
		return length;
	}

	/**
	 * Sets the lenght of the Survey.
	 * @param length
	 */
	public void setLength(Long length) {
		this.length = length;
	}

	/**
	 * @return The end date of the Survey.
	 */
	public Long getEnddate() {
		return enddate;
	}

	/**
	 * Sets the end date of the Survey.
	 * @param enddate
	 */
	public void setEnddate(Long enddate) {
		this.enddate = enddate;
	}

	/**
	 * Formats the length in miliseconds to hours and minutes.
	 * @return HH:MM
	 */
	public String fetchFormatedLength() {
		if (length != null) {
			int hours = (int) ((length / (1000 * 60 * 60)) % 24);
			String hoursString = String.valueOf(hours);
			int minutes = (int) ((length / (1000 * 60)) % 60);
			String minutesString = String.valueOf(minutes);

			if (hoursString.matches("[0-9]") && !minutesString.matches("[0-9]")) {
				return "0" + hours + ":" + minutes;
			} else if (!hoursString.matches("[0-9]") && minutesString.matches("[0-9]")) {
				return hours + ":" + "0" + minutes;
			} else if (hoursString.matches("[0-9]") && minutesString.matches("[0-9]")) {
				return "0" + hours + ":" + "0" + minutes;
			} else {
				return hours + ":" + minutes;
			}

		}

		return null;
	}

	/**
	 * Converts string to milliseconds and from milliseconds to seconds.
	 * @param lengthInString the amount of time as string.
	 * @return Number of seconds as Long.
	 */
	public Long convertLength(String lengthInString) {
		Long hours = Long.parseLong(lengthInString.substring(0, 2));
		Long minutes = Long.parseLong(lengthInString.substring(3));
		return ((hours * 60 * 60 * 1000) + (minutes * 60 * 1000));
	}
	
	public String getFormatedEnddate() {
		return new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (enddate));
	}

}
