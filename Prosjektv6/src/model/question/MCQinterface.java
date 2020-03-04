package model.question;

import java.util.List;

 
public interface MCQinterface {

	public Boolean getSingleAnswer();

	public void setSingleAnswer(Boolean singleAnswer);

	public List<String> getOptions();

	public void setOptions(List<String> options);

}
