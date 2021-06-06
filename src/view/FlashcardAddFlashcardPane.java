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
import javafx.scene.layout.HBox;

public class FlashcardAddFlashcardPane extends GridPane {
	private TextField txtFlashcardTitle;
	private TextField txtFlashcardText;
	private Button btnCreateFlashcard;

	/*---- Constructor ----*/
	public FlashcardAddFlashcardPane() {
		// Styling.
		this.setVgap(15);
		this.setHgap(20);
		this.setAlignment(Pos.CENTER);

		ColumnConstraints column0 = new ColumnConstraints();
		column0.setHalignment(HPos.RIGHT);

		this.getColumnConstraints().add(column0);
		
		// Create labels.
		Label lblFlashcardTitle = new Label("Title: ");
		Label lblFlashcardText = new Label("Text: ");
		
		// Setup text fields.
		this.txtFlashcardTitle = new TextField();
		this.txtFlashcardText = new TextField();
		
		// Initialise create profile button.
		this.btnCreateFlashcard = new Button("Create Flashcard");

		// Add controls and labels to container.
		this.add(lblFlashcardTitle, 0, 0);
		this.add(this.txtFlashcardTitle, 1, 0);

		this.add(lblFlashcardText, 0, 1);
		this.add(this.txtFlashcardText, 1, 1);
		
		this.add(new HBox(), 0, 2);
		this.add(this.btnCreateFlashcard, 1, 2);
	}
	
	public void addAddFlashcardHandler(EventHandler<ActionEvent> handler) {
		this.btnCreateFlashcard.setOnAction(handler);
	}
	
	// Getters and Setters.
	public String getFlashcardTitle() {
		return this.txtFlashcardTitle.getText();
	}
	
	public String getFlashcardText() {
		return this.txtFlashcardText.getText();
	}
}