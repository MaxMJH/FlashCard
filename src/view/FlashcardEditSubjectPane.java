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

public class FlashcardEditSubjectPane extends GridPane {
	/*---- Fields ----*/
	private TextField txtSubjectText;
	private Button btnEditSubject;

	/*---- Constructor ----*/
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
	public void clearText() {
		this.txtSubjectText.clear();
	}
	
	/*---- Handler ----*/
	public void addEditSubjectHandler(EventHandler<ActionEvent> handler) {
		this.btnEditSubject.setOnAction(handler);
	}
	
	/*---- Getters and Setters ----*/
	public String getSubjectText() {
		return this.txtSubjectText.getText();
	}
	
	public void setSubjectText(String flashcardText) {
		this.txtSubjectText.setText(flashcardText);
	}
}
