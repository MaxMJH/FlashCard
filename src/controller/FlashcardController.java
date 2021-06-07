package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import model.Flashcard;
import model.Subject;
import model.UserFlashcards;
import view.FlashcardAddFlashcardPane;
import view.FlashcardAddSubjectPane;
import view.FlashcardEditFlashcardPane;
import view.FlashcardPane;
import view.FlashcardRootPane;
import view.FlashcardSubjectPane;
import view.FlashcardViewPane;

public class FlashcardController {
	// Fields.
	private FlashcardRootPane view;
	private FlashcardSubjectPane fsp;
	private FlashcardPane fp;
	private FlashcardAddSubjectPane fasp;
	private FlashcardAddFlashcardPane fafp;
	private FlashcardViewPane fvp;
	private FlashcardEditFlashcardPane fefp;
	private UserFlashcards model;
	
	private String currentSubject;
	private String currentFlashcard;
	
	// Constructor.
	public FlashcardController(FlashcardRootPane view, UserFlashcards model) {
		this.view = view;
		this.fsp = view.getFlashcardSubjectPane();
		this.fp = view.getFlashcardPane();
		this.fasp = view.getFlashcardAddSubjectPane();
		this.fafp = view.getFlashcardAddFlashcardPane();
		this.fvp = view.getFlashcardViewPane();
		this.fefp = view.getFlashcardEditFlashcardPane();
		this.model = model;
		
		this.fsp.addAddSubjectHandler(new FSPAddSubjectHandler());
		this.fafp.addAddFlashcardHandler(new FAFPAddFlashcardHandler());
		this.fvp.addBackButtonHandler(e -> view.setCenter(fp));
		this.fvp.addEditButtonHandler(new FVPAddEditHandler());
		this.fefp.addEditFlashcardHandler(new FEFPAddEditFlashcardHandler());
		this.fvp.addRemoveButtonHandler(new FVPAddRemoveHandler());
	}
	
	// Event Handlers.
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
				
				fasp.clearText();
				
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
					fp.addPressFlashcardHandler(new FPAddPressFlashcardHandler());
				}
			});

			fp.addAddFlashcardHandler(e -> view.setCenter(fafp));
		}
	}
	
	private class FAFPAddFlashcardHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			if(!model.getFlashcards().contains(new Flashcard(fafp.getFlashcardText(), "", currentSubject))) {
				model.addFlashcard(new Flashcard(fafp.getFlashcardTitle(), fafp.getFlashcardText(), currentSubject));
				
				fp.addButton(fafp.getFlashcardTitle());
				
				fp.addPressFlashcardHandler(new FPAddPressFlashcardHandler());
				
				fafp.clearText();
				
				view.setCenter(fp);
			}	
		}
	}
	
	private class FPAddPressFlashcardHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			currentFlashcard = ((Button) ae.getSource()).getText(); 

			model.getFlashcards().forEach(e -> {
				if(e.getFlashcardTitle().equals(currentFlashcard) && e.getSubject().toString().equals(currentSubject)) {
					fvp.setFlashcardText(e.getFlashcardText());
					view.setCenter(fvp);
				}
			});			
		}
	}
	
	private class FVPAddEditHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			view.setCenter(fefp);

			Flashcard flashcard = model.getFlashcard(currentFlashcard, currentSubject);
			
			fefp.setFlashcardTitle(flashcard.getFlashcardTitle());
			fefp.setFlashcardText(flashcard.getFlashcardText());
		}
	}
	
	private class FEFPAddEditFlashcardHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			Flashcard flashcard = model.getFlashcard(currentFlashcard, currentSubject);
			
			flashcard.setFlashcardTitle(fefp.getFlashcardTitle());
			flashcard.setFlashcardText(fefp.getFlashcardText());
			
			fp.updateFlashcard(currentFlashcard, flashcard.getFlashcardTitle());
			
			view.setCenter(fp);
		}
	}
	
	private class FVPAddRemoveHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			Flashcard flashcard = model.getFlashcard(currentFlashcard, currentSubject);
			
			model.removeFlashcard(flashcard);
			fp.removeFlashcard(currentFlashcard);
			
			view.setCenter(fp);
		}
	}
}
