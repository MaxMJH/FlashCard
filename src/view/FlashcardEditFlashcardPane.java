package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * A view which allows the user to edit a flashcard.
 * 
 * @author Max
 * @author MaxHarrisMJH@gmail.com
 * @version 0.2
 * @since 0.1
 */
public class FlashcardEditFlashcardPane extends GridPane {
	/*---- Fields ----*/
	private TextField txtFlashcardTitle;
	private TextArea txtFlashcardText;
	private Button btnEditFlashcard;
	private Button btnAddFlashcardImage;
	private Button btnRemoveFlashcardImage;

	/*---- Constructor ----*/
	/**
	 * Initialises the view and necessary fields.
	 */
	public FlashcardEditFlashcardPane() {
		// Style GridPane.
		this.setVgap(15);
		this.setHgap(20);
		this.setAlignment(Pos.CENTER);

		// Setup column constraints.
		ColumnConstraints column = new ColumnConstraints();
		column.setHalignment(HPos.RIGHT);
		this.getColumnConstraints().add(column);
		
		// Setup text fields / areas.
		this.txtFlashcardTitle = new TextField();
		this.txtFlashcardText = new TextArea();
		this.txtFlashcardText.setWrapText(true);
		
		// Initialise edit flashcard button.
		this.btnEditFlashcard = new Button("Edit Flashcard");
		this.btnEditFlashcard.setId("edit-flashcard-button");
		
		// Initialises add flashcard image button.
		this.btnAddFlashcardImage = new Button();
		this.btnAddFlashcardImage.setMinSize(40, 40);
		this.btnAddFlashcardImage.setId("add-flashcardimage-button");
		
		// Initialises remove flashcard image button.
		this.btnRemoveFlashcardImage = new Button();
		this.btnRemoveFlashcardImage.setMinSize(40, 40);
		this.btnRemoveFlashcardImage.setId("remove-flashcardimage-button");
		this.btnRemoveFlashcardImage.setVisible(false);
		
		// Initialises a box that contains both flashcard image buttons.
		HBox box = new HBox();
		box.setSpacing(20);
		box.getChildren().addAll(this.btnAddFlashcardImage, this.btnRemoveFlashcardImage);
		
		// Add controls and labels to container.
		this.add(new Label("Title: "), 0, 0);
		this.add(this.txtFlashcardTitle, 1, 0);

		this.add(new Label("Text: "), 0, 1);
		this.add(this.txtFlashcardText, 1, 1);
		
		this.add(box, 0, 2);
		
		this.add(this.btnEditFlashcard, 1, 2);
	}
	
	/*---- Methods ----*/
	/**
	 * Clears both text fields within the view.
	 */
	public void clearText() {
		this.txtFlashcardTitle.clear();
		this.txtFlashcardText.clear();
	}
	
	/**
	 * Sets the visibility of btnRemoveFlashcardImage.
	 * 
	 * @param isVisible true to set button visible, false to set button invisible.
	 */
	public void setBtnRemoveFlashcardImageVisibility(boolean isVisible) {
		this.btnRemoveFlashcardImage.setVisible(isVisible);
	}
	
	/*---- Handler ----*/
	/**
	 * Adds an event handler to btnEditFlashcard.
	 * 
	 * @param handler The event handler.
	 */
	public void addEditFlashcardHandler(EventHandler<ActionEvent> handler) {
		this.btnEditFlashcard.setOnAction(handler);
	}
	
	/**
	 * Adds an event handler to btnAddFlashcardImage.
	 * 
	 * @param handler The event handler.
	 */
	public void addAddFlashcardImageHandler(EventHandler<ActionEvent> handler) {
		this.btnAddFlashcardImage.setOnAction(handler);
	}
	
	/**
	 * Adds an event handler to btnRemoveFlashcardImage.
	 * 
	 * @param handler The event handler.
	 */
	public void addRemoveFlashcardImageHandler(EventHandler<ActionEvent> handler) {
		this.btnRemoveFlashcardImage.setOnAction(handler);
	}
	
	/*---- Getters and Setters ----*/
	/**
	 * Gets the current text of txtFlashcardTitle (TextField).
	 * 
	 * @return A String representation of txtFlashcardTitle.
	 */
	public String getFlashcardTitle() {
		return this.txtFlashcardTitle.getText();
	}
	
	/**
	 * Sets the current text of txtFlashcardTitle (TextField).
	 * 
	 * @param flashcardTitle A flashcard's title.
	 */
	public void setFlashcardTitle(String flashcardTitle) {
		this.txtFlashcardTitle.setText(flashcardTitle);
	}
	
	/**
	 * Gets the current text of txtFlashcardText (TextArea).
	 * 
	 * @return A String representation of txtFlashcardText.
	 */
	public String getFlashcardText() {
		return this.txtFlashcardText.getText();
	}
	
	/**
	 * Sets the current text of txtFlashcardText (TextArea).
	 * 
	 * @param flashcardText A flashcard's text.
	 */
	public void setFlashcardText(String flashcardText) {
		this.txtFlashcardText.setText(flashcardText);
	}
}
