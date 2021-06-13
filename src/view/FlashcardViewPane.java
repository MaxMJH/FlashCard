package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class FlashcardViewPane extends GridPane {
	/*---- Fields ----*/
	private Label lblFlashcardText;
	private Button btnBack;
	private Button btnEdit;
	private Button btnRemove;
	
	/*---- Constructor ----*/
	public FlashcardViewPane() {
		// Setup GridPane.
		this.setAlignment(Pos.CENTER);
		
		// Setup row constraints.
		RowConstraints row = new RowConstraints();
		row.setVgrow(Priority.ALWAYS);
		this.getRowConstraints().addAll(row, new RowConstraints(), new RowConstraints());
		
		// Setup column constraints.
		ColumnConstraints column = new ColumnConstraints();
		column.setHgrow(Priority.ALWAYS);
		column.setHalignment(HPos.CENTER);
		this.getColumnConstraints().addAll(column, column, column);
		
		// Initialise and setup flashcard text label.
		this.lblFlashcardText = new Label("");
		this.lblFlashcardText.setAlignment(Pos.CENTER);
		this.lblFlashcardText.setWrapText(true);
		
		// Initialise and setup back button.
		this.btnBack = new Button("Back");
		this.btnBack.setMinSize(200, 50);
		
		// Initialise and setup edit button.
		this.btnEdit = new Button("Edit");
		this.btnEdit.setMinSize(200, 50);
		
		// Initialise and setup remove button.
		this.btnRemove = new Button("Remove");
		this.btnRemove.setMinSize(200, 50);
		
		// Add controls and label to container.
		this.add(this.lblFlashcardText, 0, 0, 3, 1);
		this.add(this.btnBack, 0, 1);
		this.add(this.btnEdit, 1, 1);
		this.add(this.btnRemove, 2, 1);
	}
	
	/*---- Handlers ----*/
	public void addBackHandler(EventHandler<ActionEvent> handler) {
		this.btnBack.setOnAction(handler);
	}
	
	public void addEditHandler(EventHandler<ActionEvent> handler) {
		this.btnEdit.setOnAction(handler);
	}
	
	public void addRemoveHandler(EventHandler<ActionEvent> handler) {
		this.btnRemove.setOnAction(handler);
	}
	
	/*---- Getters and Setters ----*/
	public String getFlashcardText() {
		return this.lblFlashcardText.getText();
	}
	
	public void setFlashcardText(String text) {
		this.lblFlashcardText.setText(text);
	}
}
