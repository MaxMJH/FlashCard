package view;

import javafx.scene.layout.BorderPane;

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
	public FlashcardMenuBar getFlashcardMenuBar() {
		return this.flashcardMenuBar;
	}
	
	public FlashcardSubjectPane getFlashcardSubjectPane() {
		return this.flashcardSubjectPane;
	}
	
	public FlashcardPane getFlashcardPane() {
		return this.flashcardPane;
	}

	public FlashcardHeaderPane getFlashcardHeaderPane() {
		return this.flashcardHeaderPane;
	}
	
	public FlashcardFooterPane getFlashcardFooterPane() {
		return this.flashcardFooterPane;
	}
	
	public FlashcardAddSubjectPane getFlashcardAddSubjectPane() {
		return this.flashcardAddSubjectPane;
	}
	
	public FlashcardAddFlashcardPane getFlashcardAddFlashcardPane() {
		return this.flashcardAddFlashcardPane;
	}
	
	public FlashcardViewPane getFlashcardViewPane() {
		return this.flashcardViewPane;
	}
	
	public FlashcardEditFlashcardPane getFlashcardEditFlashcardPane() {
		return this.flashcardEditFlashcardPane;
	}
	
	public FlashcardEditSubjectPane getFlashcardEditSubjectPane() {
		return this.flashcardEditSubjectPane;
	}
}
