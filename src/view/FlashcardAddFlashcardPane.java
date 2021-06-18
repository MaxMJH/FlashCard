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

public class FlashcardAddFlashcardPane extends GridPane {
	/*---- Fields ----*/
	private TextField txtFlashcardTitle;
	private TextArea txtFlashcardText;
	private Button btnCreateFlashcard;

	/*---- Constructor ----*/
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
			
		// Add controls and labels to container.
		this.add(new Label("Title: "), 0, 0);
		this.add(this.txtFlashcardTitle, 1, 0);

		this.add(new Label("Text: "), 0, 1);
		this.add(this.txtFlashcardText, 1, 1);
		
		this.add(this.btnCreateFlashcard, 1, 2);
	}
	
	/*---- Methods ----*/
	public void clearText() {
		this.txtFlashcardTitle.clear();
		this.txtFlashcardText.clear();
	}
	
	/*---- Handlers ----*/
	public void addCreateFlashcardHandler(EventHandler<ActionEvent> handler) {
		this.btnCreateFlashcard.setOnAction(handler);
	}
	
	/*---- Getters and Setters ----*/
	public String getFlashcardTitle() {
		return this.txtFlashcardTitle.getText();
	}
	
	public String getFlashcardText() {
		return this.txtFlashcardText.getText();
	}
}
