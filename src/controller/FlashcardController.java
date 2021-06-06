package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import model.Flashcard;
import model.Subject;
import model.UserFlashcards;
import view.FlashcardAddFlashcardPane;
import view.FlashcardAddSubjectPane;
import view.FlashcardPane;
import view.FlashcardRootPane;
import view.FlashcardSubjectPane;

public class FlashcardController {
	// Fields.
	private FlashcardRootPane view;
	private FlashcardSubjectPane fsp;
	private FlashcardPane fp;
	private FlashcardAddSubjectPane fasp;
	private FlashcardAddFlashcardPane fafp;
	private UserFlashcards model;
	
	private String currentSubject;
	
	// Constructor.
	public FlashcardController(FlashcardRootPane view, UserFlashcards model) {
		this.view = view;
		this.fsp = view.getFlashcardSubjectPane();
		this.fp = view.getFlashcardPane();
		this.fasp = view.getFlashcardAddSubjectPane();
		this.fafp = view.getFlashcardAddFlashcardPane();
		this.model = model;
		
		this.fsp.addAddSubjectHandler(new FSPAddSubjectHandler());
		
		this.fafp.addAddFlashcardHandler(new FAFPAddFlashcardHandler());
	}
	
	private class FSPAddSubjectHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			view.setCenter(fasp);
			
			fasp.addAddSubjectHandler(new FASPAddSubjectHandler());
		}
	}
	
	private class FASPAddSubjectHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			if(!model.getSubjects().contains(new Subject(fasp.getFlashcardSubject()))) {
				model.addSubject(new Subject(fasp.getFlashcardSubject()));
				
				fsp.addButton(fasp.getFlashcardSubject());
				
				fp.clearFlashcards();
				
				view.setCenter(fp);
				
				fsp.addPressSubjectHandler(new FSPAddPressSubjectHandler());
			}
		}
	}
	
	private class FSPAddPressSubjectHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			view.setCenter(fp); // Allows user to click on other subjects if creating one.
			
			fp.clearFlashcards();
			
			fp.setupFlashcards();
			
			currentSubject = ((Button) ae.getSource()).getText();
			
			// Show cards in subject.
			model.getFlashcards().forEach(e -> {
				fp.getButtonsArray().clear();
				if(e.getSubject().toString().equals(currentSubject)) {
					fp.addButton(e.getFlashcardTitle());
				}
			});

			fp.addAddFlashcardHandler(e -> view.setCenter(fafp));
		}
	}
	
	private class FAFPAddFlashcardHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			model.addFlashcard(new Flashcard(fafp.getFlashcardTitle(), fafp.getFlashcardText(), currentSubject));
			
			fp.addButton(fafp.getFlashcardTitle());
			
			view.setCenter(fp);
		}
	}
}
