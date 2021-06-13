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
import view.FlashcardFooterPane;
import view.FlashcardHeaderPane;
import view.FlashcardRootPane;
import view.FlashcardSubjectPane;
import view.FlashcardViewPane;

public class FlashcardController {
	/*---- Fields ----*/
	private FlashcardRootPane view;
	private FlashcardSubjectPane flashcardSubjectPane;
	private FlashcardPane flashcardPane;
	private FlashcardHeaderPane flashcardHeaderPane;
	private FlashcardFooterPane flashcardFooterPane;
	private FlashcardAddSubjectPane flashcardAddSubjectPane;
	private FlashcardAddFlashcardPane flashcardAddFlashcardPane;
	private FlashcardViewPane flashcardViewPane;
	private FlashcardEditFlashcardPane flashcardEditFlashcardPane;
	private FlashcardMenuBar flashcardMenuBar;
	private UserFlashcards model;
	
	private String currentSubject;
	private String currentFlashcard;
	
	/*---- Constructor ----*/
	public FlashcardController(FlashcardRootPane view, UserFlashcards model) {
		this.view = view;
		this.flashcardSubjectPane = view.getFlashcardSubjectPane();
		this.flashcardPane = view.getFlashcardPane();
		this.flashcardHeaderPane = view.getFlashcardHeaderPane();
		this.flashcardFooterPane = view.getFlashcardFooterPane();
		this.flashcardAddSubjectPane = view.getFlashcardAddSubjectPane();
		this.flashcardAddFlashcardPane = view.getFlashcardAddFlashcardPane();
		this.flashcardViewPane = view.getFlashcardViewPane();
		this.flashcardEditFlashcardPane = view.getFlashcardEditFlashcardPane();
		this.flashcardMenuBar = view.getFlashcardMenuBar();
		this.model = model;
		
		this.flashcardPane.setHeader(this.flashcardHeaderPane);
		this.flashcardPane.setFooter(this.flashcardFooterPane);
		
		this.attachHandlers();
	}
	
	/*---- Methods ----*/
	public void attachHandlers() {
		this.flashcardMenuBar.addSaveHandler(new FMBSaveHandler());
		this.flashcardMenuBar.addLoadHandler(new FMBLoadHandler());
		
		this.flashcardSubjectPane.addAddSubjectHandler(new FSPCreateSubjectHandler());
		this.flashcardFooterPane.addRemoveSubjectButton(new FPFRemoveSubjectHandler());
		this.flashcardAddSubjectPane.addCreateSubjectHandler(new FASPCreateSubjectHandler());
		this.flashcardAddFlashcardPane.addCreateFlashcardHandler(new FAFPFlashcardHandler());
		this.flashcardViewPane.addBackHandler(e -> view.setCenter(this.flashcardPane));
		this.flashcardViewPane.addEditHandler(new FVPEditHandler());
		this.flashcardViewPane.addRemoveHandler(new FVPRemoveHandler());
		this.flashcardEditFlashcardPane.addEditFlashcardHandler(new FEFPEditFlashcardHandler());	
	}
	
	/*---- Event Handlers ----*/
	private class FSPCreateSubjectHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			// Sets the Add Flashcard view to the centre of the programme.
			view.setCenter(flashcardAddSubjectPane);
			
			flashcardAddSubjectPane.addCreateSubjectHandler(new FASPCreateSubjectHandler());
		}
	}
	
	private class FPFRemoveSubjectHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			// Remove the subject from the model's subject array list.
			model.removeSubject(new Subject(currentSubject));
			
			// Remove the subject from the subject pane.
			flashcardSubjectPane.removeSubject(currentSubject);
			
			// Set the footer's remove subject button invisible as well as the central view.
			flashcardFooterPane.setBtnRemoveSubjectVisible(false);
			flashcardPane.hideAll();
		}
	}
	
	private class FASPCreateSubjectHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			// Check if the subject being added already exists within the model.
			if(!model.getSubjects().contains(new Subject(flashcardAddSubjectPane.getFlashcardSubject()))) {
				model.addSubject(new Subject(flashcardAddSubjectPane.getFlashcardSubject()));
				
				flashcardSubjectPane.addButton(flashcardAddSubjectPane.getFlashcardSubject());
				
				flashcardPane.clearFlashcards();
				
				flashcardAddSubjectPane.clearText();
				
				view.setCenter(flashcardPane);
				
				flashcardSubjectPane.addSubjectHandler(new FSPSubjectHandler()); 
				
				// Set the subject into focus.
				flashcardSubjectPane.getButtonsArray().get(flashcardSubjectPane.getButtonsArray().size() - 1).fire();

				// Set the footer's remove subject button visible as well as the central view.
				flashcardFooterPane.setBtnRemoveSubjectVisible(true);
				flashcardPane.showAll();
			}
		}
	}
	
	private class FSPSubjectHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			view.setCenter(flashcardPane); // Allows user to click on other subjects if creating one.
			
			flashcardPane.clearFlashcards();
			
			flashcardPane.setupFlashcards();
			
			// Set the currently selected subject.
			currentSubject = ((Button) ae.getSource()).getText();
			
			flashcardHeaderPane.setCurrentSubject(currentSubject);
			
			flashcardFooterPane.setBtnRemoveSubjectVisible(true);
			flashcardPane.showAll();
			
			// Show cards in subject.
			model.getFlashcards().forEach(e -> {
				flashcardPane.getButtonsArray().clear();
				if(e.getSubject().toString().equals(currentSubject)) {
					flashcardPane.addButton(e.getFlashcardTitle());
					flashcardPane.addFlashcardHandler(new FPFlashcardHandler());
				}
			});
			
			flashcardPane.addAddFlashcardHandler(e -> view.setCenter(flashcardAddFlashcardPane));
		}
	}
	
	private class FAFPFlashcardHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			if(!model.getFlashcards().contains(new Flashcard(flashcardAddFlashcardPane.getFlashcardTitle(), "", currentSubject))) {
				model.addFlashcard(new Flashcard(flashcardAddFlashcardPane.getFlashcardTitle(), flashcardAddFlashcardPane.getFlashcardText(), currentSubject));
				
				flashcardPane.addButton(flashcardAddFlashcardPane.getFlashcardTitle());
				
				flashcardPane.addFlashcardHandler(new FPFlashcardHandler());
				
				flashcardAddFlashcardPane.clearText();
				
				view.setCenter(flashcardPane);
			}	
		}
	}
	
	private class FPFlashcardHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			currentFlashcard = ((Button) ae.getSource()).getText(); 

			model.getFlashcards().forEach(e -> {
				if(e.getFlashcardTitle().equals(currentFlashcard) && e.getSubject().toString().equals(currentSubject)) {
					flashcardViewPane.setFlashcardText(e.getFlashcardText());
					view.setCenter(flashcardViewPane);
				}
			});			
		}
	}
	
	private class FVPEditHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			view.setCenter(flashcardEditFlashcardPane);

			Flashcard flashcard = model.getFlashcard(currentFlashcard, currentSubject);
			
			flashcardEditFlashcardPane.setFlashcardTitle(flashcard.getFlashcardTitle());
			flashcardEditFlashcardPane.setFlashcardText(flashcard.getFlashcardText());
		}
	}
	
	private class FEFPEditFlashcardHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			Flashcard flashcard = model.getFlashcard(currentFlashcard, currentSubject);
			
			flashcard.setFlashcardTitle(flashcardEditFlashcardPane.getFlashcardTitle());
			flashcard.setFlashcardText(flashcardEditFlashcardPane.getFlashcardText());
			
			flashcardPane.updateFlashcard(currentFlashcard, flashcard.getFlashcardTitle());
			
			view.setCenter(flashcardPane);
		}
	}
	
	private class FVPRemoveHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			Flashcard flashcard = model.getFlashcard(currentFlashcard, currentSubject);
			
			model.removeFlashcard(flashcard);
			flashcardPane.removeFlashcard(currentFlashcard);
			
			view.setCenter(flashcardPane);
		}
	}
	
	private class FMBSaveHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Flashcard.dat"))) {
				oos.writeObject(model);
				oos.flush();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private class FMBLoadHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			view.setCenter(flashcardPane);
			
			try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Flashcard.dat"))) {
				model = (UserFlashcards) ois.readObject();

				flashcardSubjectPane.removeAllSubjects();

				flashcardFooterPane.setBtnRemoveSubjectVisible(false);
				flashcardPane.hideAll();
				
				model.getSubjects().forEach(e -> {
					flashcardSubjectPane.addButton(e.getSubjectName());
					flashcardSubjectPane.addSubjectHandler(new FSPSubjectHandler()); 
				});
			} catch(IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
