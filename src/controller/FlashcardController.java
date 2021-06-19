package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import model.Flashcard;
import model.Subject;
import model.UserFlashcards;
import view.FlashcardAddFlashcardPane;
import view.FlashcardAddSubjectPane;
import view.FlashcardEditFlashcardPane;
import view.FlashcardEditSubjectPane;
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
	private FlashcardEditSubjectPane flashcardEditSubjectPane;
	private FlashcardMenuBar flashcardMenuBar;
	
	private UserFlashcards model;
	
	private Subject currentSubject;
	private Flashcard currentFlashcard;
	
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
		this.flashcardEditSubjectPane = view.getFlashcardEditSubjectPane();
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
		
		this.flashcardSubjectPane.addCreateSubjectHandler(new FSPCreateSubjectHandler());
		this.flashcardFooterPane.addRemoveSubjectButton(new FPFRemoveSubjectHandler());
		this.flashcardFooterPane.addEditSubjectButton(new FPFEditSubjectHandler());
		this.flashcardAddSubjectPane.addCreateSubjectHandler(new FASPCreateSubjectHandler());
		this.flashcardAddFlashcardPane.addCreateFlashcardHandler(new FAFPCreateFlashcardHandler());
		this.flashcardViewPane.addBackFlashcardHandler(e -> view.setCenter(this.flashcardPane));
		this.flashcardViewPane.addEditFlashcardHandler(new FVPEditFlashcardHandler());
		this.flashcardViewPane.addRemoveFlashcardHandler(new FVPRemoveFlashcardHandler());
		this.flashcardEditFlashcardPane.addEditFlashcardHandler(new FEFPEditFlashcardHandler());	
		this.flashcardEditSubjectPane.addEditSubjectHandler(new FESPEditSubjectHandler());
	}
	
	private void alertDialogBuilder(AlertType type, String title, String header, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
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
			model.removeSubject(currentSubject);
			
			// For each flashcard within that subject, remove them from the model.
			model.getFlashcards().removeIf(e -> e.getSubject().equals(currentSubject));
			
			// Remove the subject from the subject pane.
			flashcardSubjectPane.removeSubject(currentSubject.toString());
			
			// Set the footer's remove and edit subject button invisible as well as the central view. Also set header blank.
			flashcardFooterPane.setBtnRemoveSubjectVisible(false);
			flashcardFooterPane.setBtnEditSubjectVisible(false);
			flashcardHeaderPane.setCurrentSubject("");
			flashcardPane.hideAll();
		}
	}
	
	private class FASPCreateSubjectHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			// Check if the subject being added already exists within the model.
			if(!model.getSubjects().contains(new Subject(flashcardAddSubjectPane.getFlashcardSubject())) && !flashcardAddSubjectPane.getFlashcardSubject().equals("")) {
				model.addSubject(new Subject(flashcardAddSubjectPane.getFlashcardSubject()));
				
				flashcardSubjectPane.addButton(flashcardAddSubjectPane.getFlashcardSubject());
				
				flashcardPane.clearFlashcards();
				
				flashcardAddSubjectPane.clearText();
				
				view.setCenter(flashcardPane);
				
				flashcardSubjectPane.addCreateSubjectsHandler(new FSPCreateSubjectsHandler()); 
				
				// Set the subject into focus.
				flashcardSubjectPane.getButtonsArray().get(flashcardSubjectPane.getButtonsArray().size() - 1).fire();

				// Set the footer's remove and edit subject button visible as well as the central view.
				flashcardFooterPane.setBtnRemoveSubjectVisible(true);
				flashcardFooterPane.setBtnEditSubjectVisible(true);
				flashcardPane.showAll();
			}
		}
	}
	
	private class FSPCreateSubjectsHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			view.setCenter(flashcardPane); // Allows user to click on other subjects if creating one.
			
			flashcardPane.clearFlashcards();
			
			flashcardPane.setupFlashcards();
			
			// Set the currently selected subject.
			currentSubject = new Subject(((Button) ae.getSource()).getText());
			
			flashcardHeaderPane.setCurrentSubject(currentSubject.toString());
			
			flashcardFooterPane.setBtnRemoveSubjectVisible(true);
			flashcardFooterPane.setBtnEditSubjectVisible(true);
			flashcardPane.showAll();
			
			// Show cards in subject.
			model.getFlashcards().forEach(e -> {
				flashcardPane.getButtonsArray().clear();
				if(e.getSubject().equals(currentSubject)) {
					flashcardPane.addButton(e.getFlashcardTitle());
					flashcardPane.addCreateFlashcardsHandler(new FPCreateFlashcardsHandler());
				}
			});
			
			flashcardPane.addCreateFlashcardHandler(e -> view.setCenter(flashcardAddFlashcardPane));
		}
	}
	
	private class FAFPCreateFlashcardHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			if(!model.getFlashcards().contains(new Flashcard(flashcardAddFlashcardPane.getFlashcardTitle(), "", currentSubject)) && !flashcardAddFlashcardPane.getFlashcardTitle().equals("")) {
				model.addFlashcard(new Flashcard(flashcardAddFlashcardPane.getFlashcardTitle(), flashcardAddFlashcardPane.getFlashcardText(), currentSubject));
				
				flashcardPane.addButton(flashcardAddFlashcardPane.getFlashcardTitle());
				
				flashcardPane.addCreateFlashcardsHandler(new FPCreateFlashcardsHandler());
				
				flashcardAddFlashcardPane.clearText();
				
				view.setCenter(flashcardPane);
			}	
		}
	}
	
	private class FPCreateFlashcardsHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			currentFlashcard = new Flashcard(((Button) ae.getSource()).getText(), null, currentSubject);
			
			model.getFlashcards().forEach(e -> {
				if(e.equals(currentFlashcard) && e.getSubject().equals(currentSubject)) {
					flashcardViewPane.setFlashcardText(e.getFlashcardText());
					view.setCenter(flashcardViewPane);
				}
			});			
		}
	}
	
	private class FVPEditFlashcardHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			view.setCenter(flashcardEditFlashcardPane);

			Flashcard flashcard = model.getFlashcard(currentFlashcard.getFlashcardTitle(), currentSubject);
			
			flashcardEditFlashcardPane.setFlashcardTitle(flashcard.getFlashcardTitle());
			flashcardEditFlashcardPane.setFlashcardText(flashcard.getFlashcardText());
		}
	}
	
	private class FEFPEditFlashcardHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			Flashcard flashcard = model.getFlashcard(currentFlashcard.getFlashcardTitle(), currentSubject);

			model.editFlashcard(flashcard, flashcardEditFlashcardPane.getFlashcardTitle(), flashcardEditFlashcardPane.getFlashcardText(), currentSubject);
			
			flashcardPane.updateFlashcard(currentFlashcard.getFlashcardTitle(), flashcard.getFlashcardTitle());
			
			view.setCenter(flashcardPane);
		}
	}
		
	private class FPFEditSubjectHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			view.setCenter(flashcardEditSubjectPane);

			flashcardEditSubjectPane.setSubjectText(currentSubject.toString());
		}
	}
	
	private class FESPEditSubjectHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			if(!model.getSubjects().contains(new Subject(flashcardEditSubjectPane.getSubjectText())) && !flashcardEditSubjectPane.getSubjectText().equals("")) {	
				model.editSubject(currentSubject, flashcardEditSubjectPane.getSubjectText());
				
				flashcardSubjectPane.updateSubject(currentSubject.getSubjectName(), flashcardEditSubjectPane.getSubjectText());
				flashcardHeaderPane.setCurrentSubject(flashcardEditSubjectPane.getSubjectText());
				
				currentSubject = new Subject(flashcardEditSubjectPane.getSubjectText());
				
				view.setCenter(flashcardPane);
			}
		}
	}
	
	private class FVPRemoveFlashcardHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			Flashcard flashcard = model.getFlashcard(currentFlashcard.getFlashcardTitle(), currentSubject);
			
			model.removeFlashcard(flashcard);
			flashcardPane.removeFlashcard(currentFlashcard.getFlashcardTitle());
			
			view.setCenter(flashcardPane);
		}
	}
	
	private class FMBSaveHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Flashcard.dat"))) {
				oos.writeObject(model);
				oos.flush();
			} catch(IOException e) {
				alertDialogBuilder(AlertType.ERROR, "Save Error", "Unable to save file", e.toString());
			}
		}
	}
	
	private class FMBLoadHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			view.setCenter(flashcardPane);
			flashcardHeaderPane.setCurrentSubject("");
			
			try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Flashcard.dat"))) {
				model = (UserFlashcards) ois.readObject();

				flashcardSubjectPane.removeAllSubjects();

				flashcardFooterPane.setBtnRemoveSubjectVisible(false);
				flashcardFooterPane.setBtnEditSubjectVisible(false);
				flashcardPane.hideAll();
				
				model.getSubjects().forEach(e -> {
					flashcardSubjectPane.addButton(e.getSubjectName());
					flashcardSubjectPane.addCreateSubjectsHandler(new FSPCreateSubjectsHandler()); 
				});
			} catch(IOException | ClassNotFoundException e) {
				alertDialogBuilder(AlertType.ERROR, "Load Error", "There is no file to load data from", e.toString());
			}
		}
	}
}
