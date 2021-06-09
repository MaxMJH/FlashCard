package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import model.Flashcard;
import model.Subject;
import model.UserFlashcards;
import view.FlashcardAddFlashcardPane;
import view.FlashcardAddSubjectPane;
import view.FlashcardEditFlashcardPane;
import view.FlashcardMenuBar;
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
	private FlashcardMenuBar fmb;
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
		this.fmb = view.getFlashcardMenuBar();
		this.model = model;
		
		fmb.addSaveHandler(new FMBAddSaveProfileHanlder());
		fmb.addLoadHandler(new FMBAddLoadProfileHanlder());
		
		this.fsp.addAddSubjectHandler(new FSPAddSubjectHandler());
		this.fp.addRemoveSubjectButton(new FPAddRemoveSubjectHandler());
		this.fafp.addAddFlashcardHandler(new FAFPAddFlashcardHandler());
		this.fvp.addBackButtonHandler(e -> view.setCenter(fp));
		this.fvp.addEditButtonHandler(new FVPAddEditHandler());
		this.fefp.addEditFlashcardHandler(new FEFPAddEditFlashcardHandler());
		this.fvp.addRemoveButtonHandler(new FVPAddRemoveHandler());
		this.fasp.addAddSubjectHandler(new FASPAddSubjectHandler());
	}
	
	// Event Handlers.
	private class FSPAddSubjectHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			view.setCenter(fasp);
			
			fasp.addAddSubjectHandler(new FASPAddSubjectHandler());
		}
	}
	
	private class FPAddRemoveSubjectHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			model.removeSubject(new Subject(currentSubject));
			
			fsp.removeSubject(currentSubject);
			
			fp.hideAll();
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
				
				Button addedSubject = fsp.getButtonsArray().get(fsp.getButtonsArray().size() - 1);
				
				addedSubject.fire();
				addedSubject.requestFocus();

				fp.showAll();
			}
		}
	}
	
	private class FSPAddPressSubjectHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			view.setCenter(fp); // Allows user to click on other subjects if creating one.
			
			fp.clearFlashcards();
			
			fp.setupFlashcards();
			
			currentSubject = ((Button) ae.getSource()).getText();
			
			fp.setCurrentSubject(currentSubject);
			
			fp.showAll();
			
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
			if(!model.getFlashcards().contains(new Flashcard(fafp.getFlashcardTitle(), "", currentSubject))) {
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
	
	private class FMBAddSaveProfileHanlder implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Flashcard.dat"))) {
				oos.writeObject(model);
				oos.flush();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private class FMBAddLoadProfileHanlder implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Flashcard.dat"))) {
				model = (UserFlashcards) ois.readObject();

				fsp.removeAllSubjects();

				fp.hideAll();
				
				model.getSubjects().forEach(e -> {
					fsp.addButton(e.getSubjectName());
					fsp.addPressSubjectHandler(new FSPAddPressSubjectHandler()); 
				});
			} catch(IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
