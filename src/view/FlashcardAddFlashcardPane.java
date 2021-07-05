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

/**
 * A view which allows the user to create a flashcard.
 * 
 * @author Max
 * @author MaxHarrisMJH@gmail.com
 * @version 0.2
 * @since 0.1
 */
public class FlashcardAddFlashcardPane extends GridPane {
	/*---- Fields ----*/
	private TextField txtFlashcardTitle;
	private TextArea txtFlashcardText;
	private Button btnCreateFlashcard;
	private Button btnAddFlashcardImage;

	/*---- Constructor ----*/
	/**
	 * Initialises the view and necessary fields.
	 */
	public FlashcardAddFlashcardPane() {
		// Style GridPane.
		this.setVgap(15);
		this.setHgap(20);
		this.setAlignment(Pos.CENTER);

		// Setup column constraints.
		ColumnConstraints column = new ColumnConstraints();
		column.setHalignment(HPos.CENTER);
		this.getColumnConstraints().addAll(column, column, column);
		
		// Setup text fields.
		this.txtFlashcardTitle = new TextField();
		this.txtFlashcardText = new TextArea();
		this.txtFlashcardText.setWrapText(true);
		
		// Initialise create flashcard button.
		this.btnCreateFlashcard = new Button("Create Flashcard");
		this.btnCreateFlashcard.setId("create-flashcard-button");
		
		// Initialises add flashcard image button.
		this.btnAddFlashcardImage = new Button();
		this.btnAddFlashcardImage.setMinSize(40, 40);
		this.btnAddFlashcardImage.setId("add-flashcardimage-button");
			
		// Add controls and labels to container.
		this.add(new Label("Title: "), 0, 0);
		this.add(this.txtFlashcardTitle, 1, 0);

		this.add(new Label("Text: "), 0, 1);
		this.add(this.txtFlashcardText, 1, 1);
		
		this.add(this.btnAddFlashcardImage, 0, 2);
		
		this.add(this.btnCreateFlashcard, 1, 2);
	}
	
	/*---- Methods ----*/
	/**
	 * Clears both text fields within the view.
	 */
	public void clearText() {
		this.txtFlashcardTitle.clear();
		this.txtFlashcardText.clear();
	}
	
	/*---- Handlers ----*/
	/**
	 * Adds an event handler to btnCreateFlashcard.
	 * 
	 * @param handler The event handler.
	 */
	public void addCreateFlashcardHandler(EventHandler<ActionEvent> handler) {
		this.btnCreateFlashcard.setOnAction(handler);
	}
	
	/**
	 * Adds an event handler to btnAddFlashcardImage.
	 * 
	 * @param handler The event handler.
	 */
	public void addAddFlashcardImageHandler(EventHandler<ActionEvent> handler) {
		this.btnAddFlashcardImage.setOnAction(handler);
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
	 * Gets the current text of txtFlashcardText (TextArea).
	 * 
	 * @return A String representation of txtFlashcardText.
	 */
	public String getFlashcardText() {
		return this.txtFlashcardText.getText();
	}
}
