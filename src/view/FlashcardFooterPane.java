package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;

/**
 * A view which sets the footer.
 * 
 * @author Max
 * @author MaxHarrisMJH@gmail.com
 * @version 0.2
 * @since 0.1
 */
public class FlashcardFooterPane extends HBox {
	/*---- Fields ----*/
	private Button btnRemoveSubject;
	private Button btnEditSubject;
	
	/*---- Constructor ----*/
	/**
	 * Initialises the view and necessary fields.
	 */
	public FlashcardFooterPane() {
		// Style VBox.
		this.setAlignment(Pos.CENTER);
		this.setSpacing(20);
		
		// Set CSS Id for VBox.
		this.setId("flashcard-pane-footer");
		
		// Initialise and setup remove subject button.
		this.btnRemoveSubject = new Button("Remove Subject");
		this.btnRemoveSubject.setWrapText(true);
		this.btnRemoveSubject.setTextAlignment(TextAlignment.CENTER);
		this.btnRemoveSubject.setPrefSize(200, 50);
		this.btnRemoveSubject.setId("remove-subject-button");
		
		// Initialise and setup remove subject button.
		this.btnEditSubject = new Button("Edit Subject");
		this.btnEditSubject.setWrapText(true);
		this.btnEditSubject.setTextAlignment(TextAlignment.CENTER);
		this.btnEditSubject.setPrefSize(200, 50);
		this.btnEditSubject.setId("edit-subject-button");
		
		// Add control to container.
		this.getChildren().addAll(this.btnRemoveSubject, this.btnEditSubject);
	}
	
	/*---- Methods ----*/
	/**
	 * Sets the visibility of btnRemoveSubject.
	 * 
	 * @param isVisible true to set button visible, false to set button invisible.
	 */
	public void setBtnRemoveSubjectVisible(boolean isVisible) {
		this.btnRemoveSubject.setVisible(isVisible);
	}
	
	/**
	 * Sets the visibility of btnEditSubjectVisible.
	 * 
	 * @param isVisible true to set button visible, false to set button invisible.
	 */
	public void setBtnEditSubjectVisible(boolean isVisible) {
		this.btnEditSubject.setVisible(isVisible);
	}
	
	/*---- Handler ----*/
	/**
	 * Adds an event handler to btnRemoveSubject.
	 * 
	 * @param handler The event handler.
	 */
	public void addRemoveSubjectButton(EventHandler<ActionEvent> handler) {
		this.btnRemoveSubject.setOnAction(handler);
	}
	
	/**
	 * Adds an event handler to btnEditSubject.
	 * 
	 * @param handler The event handler.
	 */
	public void addEditSubjectButton(EventHandler<ActionEvent> handler) {
		this.btnEditSubject.setOnAction(handler);
	}
}
