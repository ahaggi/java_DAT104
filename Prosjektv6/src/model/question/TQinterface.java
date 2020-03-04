package model.question;

public interface TQinterface {
	
	public Integer getMinLength() ;
	public void setMinLength(Integer minLength);
	public Integer getMaxLength();
	public void setMaxLength(Integer maxLength) ;
	public abstract String getType() ;

}
