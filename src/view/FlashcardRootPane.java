package view;

import javafx.scene.layout.BorderPane;

public class FlashcardRootPane extends BorderPane {
	private FlashcardMenuBar fmb;
	private FlashcardSubjectPane fsp;
	private FlashcardPane fp;
	private FlashcardAddSubjectPane fasp;
	private FlashcardAddFlashcardPane fafp;
	
	public FlashcardRootPane() {
		this.setMinWidth(1000);
		this.setMinHeight(500);
		
		this.fmb = new FlashcardMenuBar();
		this.fsp = new FlashcardSubjectPane();
		this.fp = new FlashcardPane();
		this.fasp = new FlashcardAddSubjectPane();
		this.fafp = new FlashcardAddFlashcardPane();
		
		this.setTop(fmb);
		this.setLeft(fsp);
		this.setCenter(fp);
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
}
