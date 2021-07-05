package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**
 * A view which allows the user to edit a subject.
 * 
 * @author Max
 * @author MaxHarrisMJH@gmail.com
 * @version 0.2
 * @since 0.1
 */
public class FlashcardEditSubjectPane extends GridPane {
	/*---- Fields ----*/
	private TextField txtSubjectText;
	private Button btnEditSubject;

	/*---- Constructor ----*/
	/**
	 * Initialises the view and necessary fields.
	 */
	public FlashcardEditSubjectPane() {
		// Style GridPane.
		this.setVgap(15);
		this.setHgap(20);
		this.setAlignment(Pos.CENTER);

		// Setup column constraints.
		ColumnConstraints column = new ColumnConstraints();
		column.setHalignment(HPos.RIGHT);
		this.getColumnConstraints().add(column);
		
		// Setup text fields.
		this.txtSubjectText = new TextField();
		
		// Initialise edit subject button.
		this.btnEditSubject = new Button("Edit Subject");
		this.btnEditSubject.setId("edit-subject-button");
		
		// Add controls and labels to container.
		this.add(new Label("Subject: "), 0, 0);
		this.add(this.txtSubjectText, 1, 0);
		
		this.add(this.btnEditSubject, 1, 1);
	}
	
	/*---- Methods ----*/
	/**
	 * Clears text fields within the view.
	 */
	public void clearText() {
		this.txtSubjectText.clear();
	}
	
	/*---- Handler ----*/
	/**
	 * Adds an event handler to btnEditSubject.
	 * 
	 * @param handler The event handler.
	 */
	public void addEditSubjectHandler(EventHandler<ActionEvent> handler) {
		this.btnEditSubject.setOnAction(handler);
	}
	
	/*---- Getters and Setters ----*/
	/**
	 * Gets the current text of txtSubjectText (TextField).
	 * 
	 * @return A String representation of txtSubjectText.
	 */
	public String getSubjectText() {
		return this.txtSubjectText.getText();
	}
	
	/**
	 * Sets the current text of txtSubjectText (TextField).
	 * 
	 * @param flashcardText A subject's text.
	 */
	public void setSubjectText(String flashcardText) {
		this.txtSubjectText.setText(flashcardText);
	}
}
