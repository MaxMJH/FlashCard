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
	/*---- Fields ----*/
	private TextField txtFlashcardSubject;
	private Button btnCreateSubject;

	/*---- Constructor ----*/
	public FlashcardAddSubjectPane() {
		// Style GridPane.
		this.setVgap(15);
		this.setHgap(20);
		this.setAlignment(Pos.CENTER);

		// Setup column constraints.
		ColumnConstraints column = new ColumnConstraints();
		column.setHalignment(HPos.RIGHT);
		this.getColumnConstraints().add(column);

		// Setup text fields.
		this.txtFlashcardSubject = new TextField();

		// Initialise create subject button.
		this.btnCreateSubject = new Button("Create Subject");

		// Add controls and labels to container.
		this.add(new Label("Subject: "), 0, 0);
		this.add(this.txtFlashcardSubject, 1, 0);

		this.add(this.btnCreateSubject, 1, 1);
	}
	
	/*---- Methods ----*/
	public void clearText() {
		this.txtFlashcardSubject.clear();
	}
	
	/*---- Handlers ----*/
	public void addCreateSubjectHandler(EventHandler<ActionEvent> handler) {
		this.btnCreateSubject.setOnAction(handler);
	}
	
	/*---- Getters and Setters ----*/
	public String getFlashcardSubject() {
		return this.txtFlashcardSubject.getText();
	}
}
