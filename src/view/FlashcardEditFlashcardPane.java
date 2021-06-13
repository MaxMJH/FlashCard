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

public class FlashcardEditFlashcardPane extends GridPane {
	/*---- Fields ----*/
	private TextField txtFlashcardTitle;
	private TextArea txtFlashcardText;
	private Button btnEditFlashcard;

	/*---- Constructor ----*/
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

		// Add controls and labels to container.
		this.add(new Label("Title: "), 0, 0);
		this.add(this.txtFlashcardTitle, 1, 0);

		this.add(new Label("Text: "), 0, 1);
		this.add(this.txtFlashcardText, 1, 1);
		
		this.add(this.btnEditFlashcard, 1, 2);
	}
	
	/*---- Methods ----*/
	public void clearText() {
		this.txtFlashcardTitle.clear();
		this.txtFlashcardText.clear();
	}
	
	/*---- Handler ----*/
	public void addEditFlashcardHandler(EventHandler<ActionEvent> handler) {
		this.btnEditFlashcard.setOnAction(handler);
	}
	
	/*---- Getters and Setters ----*/
	public String getFlashcardTitle() {
		return this.txtFlashcardTitle.getText();
	}
	
	public void setFlashcardTitle(String flashcardTitle) {
		this.txtFlashcardTitle.setText(flashcardTitle);
	}
	
	public String getFlashcardText() {
		return this.txtFlashcardText.getText();
	}
	
	public void setFlashcardText(String flashcardText) {
		this.txtFlashcardText.setText(flashcardText);
	}
}
