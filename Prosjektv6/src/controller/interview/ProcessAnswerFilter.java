package controller.interview;

import java.time.Instant;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

import model.*;
import model.answer.*;
import model.question.*;
import utility.Validator;

/**
 * A class that processes and organizes data from servlets into rational data.
 * @see Survey, Question, Answer
 */
public class ProcessAnswerFilter {	
	private List<Question> iterator;
	private Question question;
	private HashMap<Integer, Answer> map;
	private int questionNr;
	private boolean allDataProcessed;
	
	//Survey data
	private Integer id;
	private String name;
	private String description;
	private Boolean repeatable;
	private Boolean traversable;
	private Long enddate;
	private String encryptedSurveyId;

	/**
	 * Constructs an object that can process and filter Answer in a Survey from
	 * a user.
	 * 
	 * @param survey
	 *            Survey to be processed and filtered.
	 * @see Survey, Question, HashMap, Iterator, Integer, Answer
	 */
	public ProcessAnswerFilter(Survey survey) {
		super();
		this.iterator = survey.getQuestions();
		this.map = new HashMap<Integer, Answer>();
		questionNr = 0;
		
		// Hente basic survey data
		id = survey.getId();
		name = survey.getName();
		description = survey.getDescription();
		repeatable = survey.isRepeatable();
		traversable = survey.isTraversable();
		enddate = survey.getEnddate();
		encryptedSurveyId = survey.getEncryptedSurveyId();
	}

	/**
	 * Determines whether the Question is MultipleChoiceQuestion or
	 * TextQuestion, gets the information from the user via a servlet, updates
	 * and processes the Answer into an object.
	 * 
	 * @see HttpServletRequest, MultipleChoiceQuestion, TextQuestion, Validator
	 * @param request
	 * @return true is everything went well, false if something went wrong.
	 */
	public boolean processData(HttpServletRequest request) {
		boolean altOK = true;
		if (question instanceof MCQinterface) {

			String[] options_answer = request.getParameterValues("options_answer");
			List<Integer> answeredOptions = new ArrayList<Integer>();

			if (options_answer != null) {
				for (String s : options_answer) {
					s = Validator.makeValidInput(s);
					try {
						answeredOptions.add(Integer.valueOf(s));
					} catch (Exception e) {
						altOK = false;
					}

				}
				MultipleChoiceAnswer answer = new MultipleChoiceAnswer();
				answer.setOptions(answeredOptions);
				map.put(question.getId(), answer);

			} else
				altOK = false;

		} else if (question instanceof TQinterface) {
			
			String text_answer = (request.getParameter("text_answer"));
 			
			if (text_answer != null && Validator.isValidInput(text_answer)) {
 				TextAnswer answer = new TextAnswer();
 				//TODO Hvis vi escape HTML, vil vi få en tekst som er innholder unicode character inni i svar
 				//Det er bedre å lagre svar som det er "uten å escape noe", men da trenger vi å bruke <c:out for å vise det inn i jsp fil 
 				text_answer = Validator.makeValidInput(text_answer);
 				answer.setText(text_answer);
 				map.put(question.getId(), answer);
				altOK =  true;
			}else
				altOK = false;

		}
		allDataProcessed=altOK && !hasNext_Question();
		return altOK;
	}

	/**
	 * 
	 * Gets a question from the Survey.
	 * 
	 * @see Question, Survey
	 * @return sprï¿½smï¿½l dersom det finnes, null dersomd et ikke finnes.
	 */
	public Question getQuestion() {
		if (questionNr < iterator.size() && questionNr >= 0) {
			return this.question = iterator.get(questionNr);
		}
		return null;
	}

	/**
	 * 
	 * Gets the next Question in the Survey.
	 * 
	 * @see Question, Survey
	 * @return Next Question in the Survey, if it exists, returns null if it
	 *         does not exist.
	 */
	public Question getNextQuestion() {
		if (hasNext_Question() && isOpen()) {
			increaseQuestionNr();
			return this.question = iterator.get(questionNr);
		}
		return null;
	}

	/**
	 * Gets the previous Question in the Survey.
	 * 
	 * @see Question, Survey
	 * @return Previous Question in the Survey, if it exists, returns null it
	 *         does not exist.
	 */
	public Question getPreviousQuestion() {
		if (hasPrevious_Question() && isOpen()) {
			decreaseQuestionNr();
			return this.question = iterator.get(questionNr);
		}
		return null;
	}

	/**
	 * 
	 * @return true if there is a next Question in the Survey, returns false if
	 *         there is no next Question.
	 */
	public boolean hasNext_Question() {
		return questionNr < iterator.size() - 1;
	}

	/**
	 * 
	 * @return true if there is a previous Question in the Survey, returns false
	 *         if there is no previous Question.
	 */
	public boolean hasPrevious_Question() {
		return questionNr > 0;
	}
	/**
	 * Increases the counter that tells which question number you are working
	 * on.
	 * 
	 * @return Question number
	 */
	private int increaseQuestionNr() {
		return questionNr = questionNr + 1;
	}

	/**
	 * Decreases the counter that tells which question number you are working
	 * on.
	 * 
	 * @return Question number.
	 */
	private int decreaseQuestionNr() {
		return questionNr = questionNr - 1;
	}


	/**
	 * Gets the contents of the TextAnswer String.
	 * 
	 * @see TextAnswer
	 * @return The contents of the TextAnswer
	 */
	public String getTAnswerInhold() {
		TextAnswer t = ((TextAnswer) this.map.get(question.getId()));
		String a = (t != null) ? t.getText() : "";
		return a;
	}

	/**
	 * 
	 * @see MultipleChoiceAnswer, MultipleChoiceQuestion
	 * @param x
	 *            The choice in a MultipleChoiceQuestion submitted by user.
	 * @return If the MultipleChoiceAnswer is answered correctly it returns
	 *         true, false if it is not filled out correctly.
	 */
	public boolean isMCAnswercontain(int x) {

		MultipleChoiceAnswer t = ((MultipleChoiceAnswer) this.map.get(question.getId()));
		List<Integer> l = (t != null) ? t.getOptions() : null;
		if (l != null) {
			return l.contains(x);
		}
		return false;
	}

	/**
	 * Sets the Question to current Question.
	 * 
	 * @param question
	 *            That you wish to update to.
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * Gets the iterator for the Question List.
	 * 
	 * @return List with Question.
	 * @see List, Question, Iterator
	 */
	public List<Question> getIterator() {
		return iterator;
	}

	/**
	 * Gets the HashMap with all the current question numbers and corresponding
	 * Answer.
	 * 
	 * @return A HashMap with Integer and Answer
	 * @see HashMap, Integer, Answer
	 */
	public HashMap<Integer, Answer> getMap() {
		return map;
	}

	/**
	 * Sets the pointer to this HashMap.
	 * 
	 * @param map
	 *            The HashMap that you wish to update the pointer to.
	 */
	public void setMap(HashMap<Integer, Answer> map) {
		this.map = map;
	}

	/**
	 * Gets the number of the question the user is working on.
	 * 
	 * @return Question number.
	 */
	public int getQuestionNr() {
		return questionNr;
	}

	/**
	 * Sets the number of the Question the user is working on.
	 * 
	 * @param questionNr
	 *            Question number
	 */
	public void setQuestionNr(int questionNr) {
		this.questionNr = questionNr;
	}
	/**
	 * return true if all the question been answered.
	 * 
	 * @return  boolean
	 */
	public boolean isAllDataProcessed() {
		return allDataProcessed;
	}
	
	/**
	 * @return Whether or not a survey is published.
	 */
	public Boolean isPublished() {
		return (enddate != null);
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
	
	public int getAmountOfQuestions() {
		return iterator.size();
	}
	
	/*  */
	public String getEncryptedSurveyId() {	
		return encryptedSurveyId;
	}
	
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
	 * Sets the surveys repeatable setting.
	 * @param repeatable
	 */
	public void setRepeatable(Boolean repeatable) {
		this.repeatable = repeatable;
	}
	
	/**
	 * Sets the surveys traverable setting.
	 * @param traversable
	 */
	public void setTraversable(Boolean traversable) {
		this.traversable = traversable;
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
	 * Converts string to milliseconds and from milliseconds to seconds.
	 * @param lengthInString the amount of time as string.
	 * @return Number of seconds as Long.
	 */
	public Long convertLength(String lengthInString) {
		Long hours = Long.parseLong(lengthInString.substring(0, 2));
		Long minutes = Long.parseLong(lengthInString.substring(3));
		return ((hours * 60 * 60 * 1000) + (minutes * 60 * 1000));
	}
}
