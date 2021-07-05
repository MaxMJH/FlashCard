package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

/**
 * A view which sets the hooder.
 * 
 * @author Max
 * @author MaxHarrisMJH@gmail.com
 * @version 0.2
 * @since 0.1
 */
public class FlashcardHeaderPane extends VBox {
	/*---- Fields ----*/
	private Label lblCurrentSubject;
	
	/*---- Constructor ----*/
	/**
	 * Initialises the view and necessary fields.
	 */
	public FlashcardHeaderPane() {
		// Style VBox.
		this.setAlignment(Pos.CENTER);
		
		// Set CSS Id for VBox.
		this.setId("flashcard-pane-header");
		
		// Initialise current subject label.
		this.lblCurrentSubject = new Label("");
		this.lblCurrentSubject.setWrapText(true);
		this.lblCurrentSubject.setAlignment(Pos.CENTER);
		this.lblCurrentSubject.setTextAlignment(TextAlignment.CENTER);
		this.lblCurrentSubject.setPrefSize(810, 50);
		
		// Add label to container.
		this.getChildren().add(lblCurrentSubject);
	}
	
	/*---- Getters and Setters ----*/
	/**
	 * Sets the current text of lblCurrentSubject (Label).
	 * 
	 * @param currentSubject A subject
	 */
	public void setCurrentSubject(String currentSubject) {
		this.lblCurrentSubject.setText(currentSubject);
	}
}
