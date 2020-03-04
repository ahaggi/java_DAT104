package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(schema="questionnaire_joined", name="lecturer")

/**
 * Lecturer makes the surveys. 
 *
 */
public class Lecturer {
	@Id
	private String username;
	
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="lecturer", referencedColumnName="username")
	@OrderBy("id ASC")
	private List<Survey> surveys = new ArrayList<Survey>();
	
	/**
	 * Calls the parent constructor of lecturer
	 */
	public Lecturer() {
		super();
 	}
	
	/**
	 * Creates a new lecturer with username as @param username and  password as @param password
	 */
	public Lecturer(String username, String password) {
		super();
		this.username = username;
		this.password = password;
 	}
	
	/**
	 * Returns the lecturers survey list.
	 */
	public List<Survey> getSurveys() {
		return surveys;
	}
	
	/**
	 * Sets existing lecturers surveys to whatever.
	 */
	public void setSurveys(List<Survey> surveys) {
		this.surveys = surveys;
	}

    /**
     * Adds a survey to the lecturers survey list
     */
    public void addSurvey(Survey survey) {
    	this.surveys.add(survey);
    }
    
    /**
     * Removes specified survey from lecturers survey list.
     */
    public void removeSurvey(Survey survey) {
    	this.surveys.remove(survey);
    }
        
	/**
	 * Returns username of lecturer.
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Sets username of lecturer.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Returns password of lecturer.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets password of lecturer.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
