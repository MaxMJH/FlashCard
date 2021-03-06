package model;

import java.io.Serializable;

/**
 * Represents a specific subject.
 * 
 * @author Max
 * @author MaxHarrisMJH@gmail.com
 * @version 0.2
 * @since 0.1
 */
public class Subject implements Serializable {
	/*---- Fields ----*/
	private String subjectName;
	
	// Constructor.
	/**
	 * Initialises the subject to a specified value.
	 * 
	 * @param subjectName The name of the subject.
	 */
	public Subject(String subjectName) {
		this.subjectName = subjectName;
	}
	
	/*---- Getters and Setters ----*/
	/**
	 * Gets the name of the subject.
	 * 
	 * @return A String representing the subject.
	 */
	public String getSubjectName() {
		return this.subjectName;
	}
	
	/**
	 * Sets the name of the subject.
	 * 
	 * @param subjectName A String representing the subject.
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	/*---- Overridden Methods ----*/
	/**
	 * @return true if <i>this</i> flashcard's subject is equal to the <i>others</i>
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
			
		Subject other = (Subject) obj;
		
		return this.subjectName.equals(other.subjectName);
	}
	
	/**
	 * @return A textual representation of the subject.
	 */
	@Override
	public String toString() {
		return this.subjectName;
	}
}
