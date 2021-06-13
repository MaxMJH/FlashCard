package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains an ArrayList which stores flashcards. The ArrayList can be manipulated in ways of adding,
 * editing and removing flashcards from the array. In terms of the GUI, this is the model that will be
 * manipulated. 
 * 
 * @author Max Harris
 * @author MaxHarrisMJH@gmail.com
 * @version 0.1
 * @since 0.1
 */
public class UserFlashcards implements Serializable {
	/*---- Fields ----*/
	private List<Flashcard> flashcards;
	private List<Subject> subjects;
	
	/*---- Constructor ----*/
	/**
	 * Initialises the ArrayList of type Flashcard.
	 */
	public UserFlashcards() {
		this.flashcards = new ArrayList<>();
		this.subjects = new ArrayList<>();
	}
	
	/*---- Methods ----*/
	/**
	 * Allows the user to add a new flashcard to the ArrayList.
	 * 
	 * @param flashcard The user's flashcard.
	 */
	public void addFlashcard(Flashcard flashcard) {
		this.flashcards.add(flashcard);
	}
	
	/**
	 * Allows the user to add a new subject to the ArrayList.
	 * 
	 * @param subject The subject type of the flashcard.
	 */
	public void addSubject(Subject subject) {
		this.subjects.add(subject);
	}
	
	/**
	 * Allows the user to edit an existing flashcard from the ArrayList.
	 * 
	 * @param flashcard The flashcard that the user intends to edit.
	 * @param flashcardTitle The title of the flashcard.
	 * @param flashcardText The text of the flashcard.
	 * @param subject The subject of the flashcard.
	 */
	public void editFlashcard(Flashcard flashcard, String flashcardTitle, String flashcardText, String subject) {
		int flashcardIndex = this.flashcards.indexOf(flashcard);
		Flashcard tempFlashcard = this.flashcards.get(flashcardIndex);
		
		tempFlashcard.setFlashcardTitle(flashcardTitle);
		tempFlashcard.setFlashcardText(flashcardText);
		tempFlashcard.setSubject(subject);
		
		this.flashcards.set(flashcardIndex, tempFlashcard);
	}
	
	/**
	 * Allows the user to remove a flashcard from the ArrayList.
	 * 
	 * @param flashcard The flashcard that the user wishes to delete.
	 */
	public void removeFlashcard(Flashcard flashcard) {
		this.flashcards.remove(flashcard);
	}
	
	/**
	 * Allows the user to remove a subject from the ArrayList.
	 * 
	 * @param subject The subject that the user wishes to delete.
	 */
	public void removeSubject(Subject subject) {
		this.subjects.remove(subject);
	}
	
	/**
	 * Gets the size of the ArrayList.
	 * 
	 * @return An integer which represents the size of the ArrayList.
	 */
	public int size() {
		return this.flashcards.size();
	}
	
	/*---- Getters and Setters ----*/
	/**
	 * Gets the list of all flashcards in the ArrayList.
	 * 
	 * @return All the flashcards within the ArrayList.
	 */
	public List<Flashcard> getFlashcards() {
		return this.flashcards;
	}
	
	/**
	 * Gets a flashcard at a specific index from the ArrayList.
	 * 
	 * @param index The index of the flashcard.
	 * @return A flashcard if the index is within the ArrayList bounds or null.
	 */
	public Flashcard getFlashcard(int index) {
		try {
			return this.flashcards.get(index);
		} catch(IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	/**
	 * Gets a flashcard from the ArrayList by passing a flashcard title and subject.
	 * 
	 * @param flashcardTitle The title of the flashcard.
	 * @param currentSubject The subject that the flashcard belongs to.
	 * @return A flashcard with the specified title and subject or null.
	 */
	public Flashcard getFlashcard(String flashcardTitle, String currentSubject) {
		for(int i = 0; i < this.flashcards.size(); i++) {
			Flashcard currentFlashcard = this.flashcards.get(i);
			
			if(currentFlashcard.getFlashcardTitle().equals(flashcardTitle) && currentFlashcard.getSubject().toString().equals(currentSubject)) {
				return currentFlashcard;
			}
		}
		
		return null;
	}
	
	/**
	 * Gets the list of all subjects in the ArrayList.
	 * 
	 * @return All the subjects within the ArrayList.
	 */
	public List<Subject> getSubjects() {
		return this.subjects;
	}
}
