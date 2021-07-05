package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Window;
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
	private String currentFlashcardImageURL;
	
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
	// Attaches event handlers to the buttons within the view.
	public void attachHandlers() {
		this.flashcardMenuBar.addSaveHandler(new FMBSaveHandler());
		this.flashcardMenuBar.addLoadHandler(new FMBLoadHandler());
		
		this.flashcardSubjectPane.addCreateSubjectHandler(new FSPCreateSubjectHandler());
		this.flashcardFooterPane.addRemoveSubjectButton(new FFPRemoveSubjectHandler());
		this.flashcardFooterPane.addEditSubjectButton(new FFPEditSubjectHandler());
		this.flashcardAddSubjectPane.addCreateSubjectHandler(new FASPCreateSubjectHandler());
		this.flashcardAddFlashcardPane.addCreateFlashcardHandler(new FAFPCreateFlashcardHandler());
		this.flashcardViewPane.addBackFlashcardHandler(e -> view.setCenter(this.flashcardPane));
		this.flashcardViewPane.addEditFlashcardHandler(new FVPEditFlashcardHandler());
		this.flashcardViewPane.addRemoveFlashcardHandler(new FVPRemoveFlashcardHandler());
		this.flashcardEditFlashcardPane.addEditFlashcardHandler(new FEFPEditFlashcardHandler());	
		this.flashcardEditSubjectPane.addEditSubjectHandler(new FESPEditSubjectHandler());
		this.flashcardAddFlashcardPane.addAddFlashcardImageHandler(e -> imageFileChooser(e));
		this.flashcardEditFlashcardPane.addAddFlashcardImageHandler(e -> imageFileChooser(e));
		this.flashcardEditFlashcardPane.addRemoveFlashcardImageHandler(new FEFPRemoveFlashcardImageHandler());
	}
	
	// Opens an Alert dialogue informing the user about an error.
	private void alertDialogueBuilder(AlertType type, String title, String header, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	// Opens a FileChooser dialogue allowing the user to set an image for a flashcard.
	private void imageFileChooser(ActionEvent ae) {
		FileChooser fileChooser = new FileChooser();
		
		// Get the current stage on which the stage acts on.
	    Window stage = ((Node) ae.getSource()).getScene().getWindow();
		
	    // Get the selected file (if any).
		File file = fileChooser.showOpenDialog(stage);
		
		if(file != null && (file.toString().endsWith(".png") || file.toString().endsWith(".jpg"))) {
			currentFlashcardImageURL = "file:///" + file.toString();
		} else {
			// Indicate that the file was either null or was not an extension of .png or .jpg.
			currentFlashcardImageURL = null;
		}
	}
	
	/*---- Event Handlers ----*/
	// Directs to the FlashcardAddSubjectPane view.
	private class FSPCreateSubjectHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			// Sets the flashcardAddSubjectPane view to the centre of the programme.
			view.setCenter(flashcardAddSubjectPane);
			
			// Adds an event handler to the create subject button.
			flashcardAddSubjectPane.addCreateSubjectHandler(new FASPCreateSubjectHandler()); // -> Might be better to just set handler in con.
		}
	}
	
	// Adds the subject to the model.
	private class FASPCreateSubjectHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			Subject subject = new Subject(flashcardAddSubjectPane.getFlashcardSubject());
			
			// Check if the subject being added already exists within the model and contains characters.
			if(!model.getSubjects().contains(subject) && !subject.getSubjectName().equals("")) {
				model.addSubject(subject);
				
				flashcardSubjectPane.addButton(subject.getSubjectName());
				
				// Need to clear all views used to create subject so programme doesn't get "clogged" with incorrect data.
				flashcardPane.clearFlashcards();
				flashcardAddSubjectPane.clearText();
				
				view.setCenter(flashcardPane);
				
				// Add event handler to newly created subject.
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
	
	// Directs to the FlashcardPane view.
	private class FSPCreateSubjectsHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			view.setCenter(flashcardPane); // Allows user to click on other subjects if creating one.
			
			// Need to clear all views when subject clicked so programme doesn't get "clogged" with incorrect data.
			flashcardPane.clearFlashcards();
			flashcardPane.getButtonsArray().clear();
			flashcardPane.setupFlashcards();
			
			// Set the currently selected subject.
			currentSubject = new Subject(((Button) ae.getSource()).getText());
			
			flashcardHeaderPane.setCurrentSubject(currentSubject.toString());
			flashcardFooterPane.setBtnRemoveSubjectVisible(true);
			flashcardFooterPane.setBtnEditSubjectVisible(true);
			flashcardPane.showAll();
			
			// Show flashcards in subject.
			model.getFlashcards().forEach(e -> {
				if(e.getSubject().equals(currentSubject)) {
					flashcardPane.addButton(e.getFlashcardTitle());
					
					// Add event handler to each flashcard.
					flashcardPane.addCreateFlashcardsHandler(new FPCreateFlashcardsHandler());
				}
			});
			
			// Adds event handler to the add flashcard button (the plus).
			flashcardPane.addCreateFlashcardHandler(e -> view.setCenter(flashcardAddFlashcardPane));
		}
	}
	
	// Adds the flashcard to the model.
	private class FAFPCreateFlashcardHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			// Get all data inputted into the flashcardAddFlashcardPane view.
			Flashcard flashcard = new Flashcard(flashcardAddFlashcardPane.getFlashcardTitle(), flashcardAddFlashcardPane.getFlashcardText(), currentFlashcardImageURL, currentSubject); 
			
			// Check if the flashcard being added already exists within the model and contains characters.
			if(!model.getFlashcards().contains(flashcard) && !flashcard.getFlashcardTitle().equals("")) {
				model.addFlashcard(flashcard);
				
				flashcardPane.addButton(flashcard.getFlashcardTitle());
				
				// Add event handler to the newly created flashcard.
				flashcardPane.addCreateFlashcardsHandler(new FPCreateFlashcardsHandler());
		
				flashcardAddFlashcardPane.clearText();
				
				view.setCenter(flashcardPane);
				
				// Ensures that the newly created flashcard does not have the same image as the flashcard created before it.
				currentFlashcardImageURL = null;
			}	
		}
	}
	
	// Directs to the FlashcardViewPane view.
	private class FPCreateFlashcardsHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			currentFlashcard = new Flashcard(((Button) ae.getSource()).getText(), null, null, currentSubject); 
			
			model.getFlashcards().forEach(e -> {
				if(e.equals(currentFlashcard) && e.getSubject().equals(currentSubject)) {
					flashcardViewPane.setFlashcardText(e.getFlashcardText());
					flashcardViewPane.setFlashcardImage(e.getFlashcardImageURL());
					view.setCenter(flashcardViewPane);
				}
			});		
		}
	}
	
	// Directs to the FlashcardEditFlashcardPane view.
	private class FVPEditFlashcardHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			view.setCenter(flashcardEditFlashcardPane);

			Flashcard flashcard = model.getFlashcard(currentFlashcard.getFlashcardTitle(), currentSubject);
			
			// Checks to see if the flashcard has an image attached to it.
			if(flashcard.getFlashcardImageURL() != null) {
				flashcardEditFlashcardPane.setBtnRemoveFlashcardImageVisibility(true);
			}
			
			flashcardEditFlashcardPane.setFlashcardTitle(flashcard.getFlashcardTitle());
			flashcardEditFlashcardPane.setFlashcardText(flashcard.getFlashcardText());
		}
	}
	
	// Edits the flashcard for both the model and the view.
	private class FEFPEditFlashcardHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			Flashcard tempFlashcard = new Flashcard(flashcardEditFlashcardPane.getFlashcardTitle(), flashcardEditFlashcardPane.getFlashcardText(), null, currentSubject); 
			
			if((!model.getFlashcards().contains(tempFlashcard) && !tempFlashcard.getFlashcardText().equals("")) || currentFlashcard.equals(tempFlashcard)) {
				Flashcard flashcard = model.getFlashcard(currentFlashcard.getFlashcardTitle(), currentSubject);
	
				if(currentFlashcardImageURL == null && flashcard.getFlashcardImageURL() != null) {
					currentFlashcardImageURL = flashcard.getFlashcardImageURL();
				}
			
				model.editFlashcard(flashcard, tempFlashcard.getFlashcardTitle(), tempFlashcard.getFlashcardText(), currentFlashcardImageURL, currentSubject);	
				flashcardPane.updateFlashcard(currentFlashcard.getFlashcardTitle(), flashcard.getFlashcardTitle());
				
				view.setCenter(flashcardPane);
				
				currentFlashcardImageURL = null;
				flashcardEditFlashcardPane.setBtnRemoveFlashcardImageVisibility(false);
			}	
		}
	}
	
	// Removes the image from the flashcard (Only available if an image URL has been set). 
	private class FEFPRemoveFlashcardImageHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			model.getFlashcards().forEach(e -> {
				if(e.equals(currentFlashcard)) {
					// Indicate to the model that the flashcard no longer has an image attached to it.
					e.setFlashcardImageURL(null);
				}
			});
			
			currentFlashcardImageURL = null;
		}
	}
	
	// Removes the subject from both the model and the view.
	private class FFPRemoveSubjectHandler implements EventHandler<ActionEvent> {
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
	
	// Directs to the FlashcardEditSubjectPane view. 
	private class FFPEditSubjectHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			view.setCenter(flashcardEditSubjectPane);

			flashcardEditSubjectPane.setSubjectText(currentSubject.toString());
		}
	}
	
	// Edits the name of the subject as well as the flashcards under it.
	private class FESPEditSubjectHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			Subject subject = new Subject(flashcardEditSubjectPane.getSubjectText());
			
			if((!model.getSubjects().contains(subject) && !subject.getSubjectName().equals("")) || subject.equals(currentSubject)) {	
				model.editSubject(currentSubject, flashcardEditSubjectPane.getSubjectText());
				
				flashcardSubjectPane.updateSubject(currentSubject.getSubjectName(), flashcardEditSubjectPane.getSubjectText());
				flashcardHeaderPane.setCurrentSubject(flashcardEditSubjectPane.getSubjectText());
				
				currentSubject = subject;
				
				view.setCenter(flashcardPane);
			}
		}
	}
	
	// Removes the flashcard from both the model and the view.
	private class FVPRemoveFlashcardHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			Flashcard flashcard = model.getFlashcard(currentFlashcard.getFlashcardTitle(), currentSubject);
			
			model.removeFlashcard(flashcard);
			flashcardPane.removeFlashcard(currentFlashcard.getFlashcardTitle());
			
			view.setCenter(flashcardPane);
		}
	}
	
	// Saves data from the model to a file called "Flashcard.dat".
	private class FMBSaveHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Flashcard.dat"))) {
				oos.writeObject(model);
				oos.flush();
			} catch(IOException e) {
				alertDialogueBuilder(AlertType.ERROR, "Save Error", "Unable to save file", e.toString());
			}
		}
	}
	
	// Loads data from "Flashcard.dat" and populates the model.
	private class FMBLoadHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			view.setCenter(flashcardPane);
			flashcardHeaderPane.setCurrentSubject("");
			
			try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Flashcard.dat"))) {
				model = (UserFlashcards) ois.readObject();

				// Resets the whole stage.
				flashcardSubjectPane.removeAllSubjects();
				flashcardFooterPane.setBtnRemoveSubjectVisible(false);
				flashcardFooterPane.setBtnEditSubjectVisible(false);
				flashcardPane.hideAll();
				
				// Re-populates the flashcardSubjectPane view with all subjects within the model.
				model.getSubjects().forEach(e -> {
					flashcardSubjectPane.addButton(e.getSubjectName());
					flashcardSubjectPane.addCreateSubjectsHandler(new FSPCreateSubjectsHandler()); 
				});
			} catch(IOException | ClassNotFoundException e) {
				alertDialogueBuilder(AlertType.ERROR, "Load Error", "There is no file to load data from", e.toString());
			}
		}
	}
}
