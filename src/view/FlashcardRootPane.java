package view;

import javafx.scene.layout.BorderPane;

public class FlashcardRootPane extends BorderPane {
	private FlashcardMenuBar fmb;
	private FlashcardSubjectPane fsp;
	private FlashcardPane fp;
	private FlashcardAddSubjectPane fasp;
	private FlashcardAddFlashcardPane fafp;
	private FlashcardViewPane fvp;
	private FlashcardEditFlashcardPane fefp;
	
	public FlashcardRootPane() {
		this.setMinWidth(1000);
		this.setMinHeight(500);
		
		this.fmb = new FlashcardMenuBar();
		this.fsp = new FlashcardSubjectPane();
		this.fp = new FlashcardPane();
		this.fasp = new FlashcardAddSubjectPane();
		this.fafp = new FlashcardAddFlashcardPane();
		this.fvp = new FlashcardViewPane();
		this.fefp = new FlashcardEditFlashcardPane();

		this.setTop(fmb);
		this.setLeft(fsp);
		this.setCenter(fp);
		
		if(this.fsp.getButtonsArray().isEmpty()) {
			fp.setBtnRemoveSubjectVisible(false);
		}
	}

	public FlashcardMenuBar getFlashcardMenuBar() {
		return this.fmb;
	}
	
	public FlashcardSubjectPane getFlashcardSubjectPane() {
		return this.fsp;
	}
	
	public FlashcardPane getFlashcardPane() {
		return this.fp;
	}
	
	public FlashcardAddSubjectPane getFlashcardAddSubjectPane() {
		return this.fasp;
	}
	
	public FlashcardAddFlashcardPane getFlashcardAddFlashcardPane() {
		return this.fafp;
	}
	
	public FlashcardViewPane getFlashcardViewPane() {
		return this.fvp;
	}
	
	public FlashcardEditFlashcardPane getFlashcardEditFlashcardPane() {
		return this.fefp;
	}
}
