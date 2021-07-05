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

/**
 * A view which allows the user to create a subject.
 * 
 * @author Max
 * @author MaxHarrisMJH@gmail.com
 * @version 0.2
 * @since 0.1
 */
public class FlashcardAddSubjectPane extends GridPane {
	/*---- Fields ----*/
	private TextField txtFlashcardSubject;
	private Button btnCreateSubject;

	/*---- Constructor ----*/
	/**
	 * Initialises the view and necessary fields.
	 */
	public FlashcardAddSubjectPane() {
		// Style GridPane.
		this.setVgap(15);
		this.setHgap(20);
		this.setAlignment(Pos.CENTER);

		// Setup column constraints.
		ColumnConstraints column = new ColumnConstraints();
		column.setHalignment(HPos.CENTER);
		this.getColumnConstraints().addAll(column, column);

		// Setup text field.
		this.txtFlashcardSubject = new TextField();
		this.txtFlashcardSubject.setPrefSize(300, 10);
		
		// Initialise create subject button.
		this.btnCreateSubject = new Button("Create Subject");
		this.btnCreateSubject.setId("create-subject-button");
		
		// Add controls and labels to container.
		this.add(new Label("Subject: "), 0, 0);
		this.add(this.txtFlashcardSubject, 1, 0);

		this.add(this.btnCreateSubject, 1, 1);
	}
	
	/*---- Methods ----*/
	/**
	 * Clears text field within the view.
	 */
	public void clearText() {
		this.txtFlashcardSubject.clear();
	}
	
	/*---- Handlers ----*/
	/**
	 * Adds an event handler to btnCreateSubject.
	 * @param handler The event handler.
	 */
	public void addCreateSubjectHandler(EventHandler<ActionEvent> handler) {
		this.btnCreateSubject.setOnAction(handler);
	}
	
	/*---- Getters and Setters ----*/
	/**
	 * Gets the current text of txtFlashcardSubject (TextField).
	 * 
	 * @return A String representation of txtFlashcardSubject.
	 */
	public String getFlashcardSubject() {
		return this.txtFlashcardSubject.getText();
	}
}
