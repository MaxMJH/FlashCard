package model;

import java.io.Serializable;

/**
 * Represents a specific flashcard.
 * 
 * @author Max
 * @author MaxHarrisMJH@gmail.com
 * @version 0.2
 * @since 0.1
 */
public class Flashcard implements Serializable {
	/*---- Fields ----*/
	private String flashcardTitle;
	private String flashcardText;
	private Subject subject;
	
	/*---- Constructor ----*/
	/**
	 * Initialises the flashcard with specified values.
	 * 
	 * @param flashcardTitle The title of the flashcard.
	 * @param flashcardText The text of the flashcard.
	 * @param subject The subject type of the flashcard.
	 */
	public Flashcard(String flashcardTitle, String flashcardText, Subject subject) {
		this.flashcardTitle = flashcardTitle;
		this.flashcardText = flashcardText;
		this.subject = subject;
	}
	
	/*---- Getters and Setters ----*/
	/**
	 * Gets the title of the flashcard.
	 * 
	 * @return A String representation of the flashcard's title.
	 */
	public String getFlashcardTitle() {
		return this.flashcardTitle;
	}
	
	/**
	 * Sets the title of the flashcard.
	 * 
	 * @param flashcardTitle A String representation of the flashcard's title.
	 */
	public void setFlashcardTitle(String flashcardTitle) {
		this.flashcardTitle = flashcardTitle;
	}
	
	/**
	 * Gets the text of the flashcard.
	 * 
	 * @return A String representation of the flashcard's text.
	 */
	public String getFlashcardText() {
		return this.flashcardText;
	}
	
	/**
	 * Sets the text of the flashcard.
	 * 
	 * @param flashcardText A String representation of the flashcard's text.
	 */
	public void setFlashcardText(String flashcardText) {
		this.flashcardText = flashcardText;
	}
	
	/**
	 * Gets the subject of the flashcard.
	 * 
	 * @return The flashcard's subject.
	 */
	public Subject getSubject() {
		return this.subject;
	}
	
	/**
	 * Sets the subject of the flashcard.
	 * 
	 * @param subject A String representation of the flashcard's subject.
	 */
	public void setSubject(String subject) {
		this.subject = new Subject(subject);
	}
	
	/**
	 * Sets the subject of the flashcard.
	 * 
	 * @param subject A flashcard's subject.
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	/*---- Overridden Methods ----*/
	/**
	 * @return true if <i>this</i> flashcard's title is equal to the <i>others</i>
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
			
		Flashcard other = (Flashcard) obj;
		
		return this.flashcardTitle.equals(other.flashcardTitle) && this.subject.equals(other.subject);
	}
	
	/**
	 * @return A textual representation of the flashcard.
	 */
	@Override
	public String toString() {
		return this.subject + " --- " + this.flashcardTitle + " : " + this.flashcardText;
	}
}
