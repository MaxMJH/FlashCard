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

public class FlashcardAddSubjectPane extends GridPane {
	private TextField txtFlashcardSubject;
	private Button btnCreateSubject;

	/*---- Constructor ----*/
	public FlashcardAddSubjectPane() {
		// Styling.
		this.setVgap(15);
		this.setHgap(20);
		this.setAlignment(Pos.CENTER);

		ColumnConstraints column0 = new ColumnConstraints();
		column0.setHalignment(HPos.RIGHT);

		this.getColumnConstraints().add(column0);
		
		// Create labels.
		Label lblFlashcardSubject = new Label("Subject: ");

		// Setup text fields.
		this.txtFlashcardSubject = new TextField();

		// Initialise create profile button.
		this.btnCreateSubject = new Button("Create Subject");

		// Add controls and labels to container.
		this.add(lblFlashcardSubject, 0, 0);
		this.add(this.txtFlashcardSubject, 1, 0);

		this.add(new HBox(), 0, 1);
		this.add(this.btnCreateSubject, 1, 1);
	}
	
	public void addAddSubjectHandler(EventHandler<ActionEvent> handler) {
		this.btnCreateSubject.setOnAction(handler);
	}
	
	public void clearText() {
		this.txtFlashcardSubject.clear();
	}
	
	// Getters and Setters.
	public String getFlashcardSubject() {
		return this.txtFlashcardSubject.getText();
	}
}
