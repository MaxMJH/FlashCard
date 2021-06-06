package model;

/**
 * Represents a specific subject.
 * 
 * @author Max
 * @author MaxHarrisMJH@gmail.com
 * @version 0.1
 * @since 0.1
 */
public class Subject {
	// Fields.
	String subjectName;
	
	// Constructors.
	/**
	 * Initialises the subject to a default value.
	 */
	public Subject() {
		this.subjectName = "";
	}
	
	/**
	 * Initialises the subject to a specified value.
	 * 
	 * @param subjectName The name of the subject.
	 */
	public Subject(String subjectName) {
		this.subjectName = subjectName;
	}
	
	// Getters and Setters.
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
	
	// Overridden Methods.
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
