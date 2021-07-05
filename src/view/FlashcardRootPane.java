package view;

import javafx.scene.layout.BorderPane;

/**
 * The main root view.
 * 
 * @author Max
 * @author MaxHarrisMJH@gmail.com
 * @version 0.2
 * @version 0.1
 */
public class FlashcardRootPane extends BorderPane {
	/*---- Fields ----*/
	private FlashcardMenuBar flashcardMenuBar;
	private FlashcardSubjectPane flashcardSubjectPane;
	private FlashcardPane flashcardPane;
	private FlashcardHeaderPane flashcardHeaderPane;
	private FlashcardFooterPane flashcardFooterPane;
	private FlashcardAddSubjectPane flashcardAddSubjectPane;
	private FlashcardAddFlashcardPane flashcardAddFlashcardPane;
	private FlashcardViewPane flashcardViewPane;
	private FlashcardEditFlashcardPane flashcardEditFlashcardPane;
	private FlashcardEditSubjectPane flashcardEditSubjectPane;
	
	/*---- Constructor ----*/
	/**
	 * Initialises the view and necessary fields.
	 */
	public FlashcardRootPane() {	
		// Initialise all panes.
		this.flashcardMenuBar = new FlashcardMenuBar();
		this.flashcardSubjectPane = new FlashcardSubjectPane();
		this.flashcardPane = new FlashcardPane();
		this.flashcardHeaderPane = new FlashcardHeaderPane();
		this.flashcardFooterPane = new FlashcardFooterPane();
		this.flashcardAddSubjectPane = new FlashcardAddSubjectPane();
		this.flashcardAddFlashcardPane = new FlashcardAddFlashcardPane();
		this.flashcardViewPane = new FlashcardViewPane();
		this.flashcardEditFlashcardPane = new FlashcardEditFlashcardPane();
		this.flashcardEditSubjectPane = new FlashcardEditSubjectPane();
		
		// Style BorderPane.
		this.setMinWidth(1200);
		this.setMinHeight(570);
		this.setTop(flashcardMenuBar);
		this.setLeft(flashcardSubjectPane);
		this.setCenter(flashcardPane);
		
		// Ensures that the remove button cannot be seen if there are no subjects.
		if(this.flashcardSubjectPane.getButtonsArray().isEmpty()) {
			flashcardFooterPane.setBtnRemoveSubjectVisible(false);
			flashcardFooterPane.setBtnEditSubjectVisible(false);
		}
	}

	/*---- Getters and Setters ----*/
	/**
	 * Gets the FlashcardMenuBar view.
	 * 
	 * @return The FlashcardMenuBar view.
	 */
	public FlashcardMenuBar getFlashcardMenuBar() {
		return this.flashcardMenuBar;
	}
	
	/**
	 * Gets the FlashcarSubjectPane view.
	 * 
	 * @return The FlashcardSubjectPane view.
	 */
	public FlashcardSubjectPane getFlashcardSubjectPane() {
		return this.flashcardSubjectPane;
	}
	
	/**
	 * Gets the FlashcardPane view.
	 * 
	 * @return The FlashcardPane view.
	 */
	public FlashcardPane getFlashcardPane() {
		return this.flashcardPane;
	}

	/**
	 * Gets the FlashcardHeaderPane view.
	 * 
	 * @return The FlashcardHeaderPane view.
	 */
	public FlashcardHeaderPane getFlashcardHeaderPane() {
		return this.flashcardHeaderPane;
	}
	
	/**
	 * Gets the FlashcardFooterPane view.
	 * 
	 * @return The FlashcardFooterPane view.
	 */
	public FlashcardFooterPane getFlashcardFooterPane() {
		return this.flashcardFooterPane;
	}
	
	/**
	 * Gets the FlashcardAddSubjectPane view.
	 * 
	 * @return The FlashcardAddSubjectPane view.
	 */
	public FlashcardAddSubjectPane getFlashcardAddSubjectPane() {
		return this.flashcardAddSubjectPane;
	}
	
	/**
	 * Gets the FlashcardAddFlashcardPane view.
	 * 
	 * @return The FlashcardAddFlashcardPane view.
	 */
	public FlashcardAddFlashcardPane getFlashcardAddFlashcardPane() {
		return this.flashcardAddFlashcardPane;
	}
	
	/**
	 * Gets the FlashcardViewPane view.
	 * 
	 * @return The FlaschcardViewPane view.
	 */
	public FlashcardViewPane getFlashcardViewPane() {
		return this.flashcardViewPane;
	}
	
	/**
	 * Gets the FlashcardEditFlashcardPane view.
	 * 
	 * @return The FlashcardEditFlashcardPane view.
	 */
	public FlashcardEditFlashcardPane getFlashcardEditFlashcardPane() {
		return this.flashcardEditFlashcardPane;
	}
	
	/**
	 * Gets the FlashcarEditSubjectPane view.
	 * 
	 * @return The FlashcardEditSubjectPane view.
	 */
	public FlashcardEditSubjectPane getFlashcardEditSubjectPane() {
		return this.flashcardEditSubjectPane;
	}
}
