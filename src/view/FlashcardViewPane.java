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
	private Label lblText;
	private Button btnBack;
	private Button btnEdit;
	private Button btnRemove;
	
	public FlashcardViewPane() {
		this.setAlignment(Pos.CENTER);
		
		RowConstraints row = new RowConstraints();
		row.setVgrow(Priority.ALWAYS);
		
		ColumnConstraints column = new ColumnConstraints();
		column.setHgrow(Priority.ALWAYS);
		column.setHalignment(HPos.CENTER);
		
		this.getColumnConstraints().addAll(column, column, column);
		this.getRowConstraints().addAll(row, new RowConstraints(), new RowConstraints());
		
		this.lblText = new Label("");
		this.lblText.setAlignment(Pos.CENTER);
		this.lblText.setWrapText(true);
		
		this.add(this.lblText, 0, 0, 3, 1);
		
		this.btnBack = new Button("Back");
		this.btnBack.setMinSize(200, 50);
		
		this.btnEdit = new Button("Edit");
		this.btnEdit.setMinSize(200, 50);
		
		this.btnRemove = new Button("Remove");
		this.btnRemove.setMinSize(200, 50);
		
		this.add(this.btnBack, 0, 1);
		this.add(this.btnEdit, 1, 1);
		this.add(this.btnRemove, 2, 1);
	}
	
	// Handlers.
	public void addBackButtonHandler(EventHandler<ActionEvent> handler) {
		this.btnBack.setOnAction(handler);
	}
	
	public void addEditButtonHandler(EventHandler<ActionEvent> handler) {
		this.btnEdit.setOnAction(handler);
	}
	
	public void addRemoveButtonHandler(EventHandler<ActionEvent> handler) {
		this.btnRemove.setOnAction(handler);
	}
	
	// Getters and Setters.
	public String getFlashcardText() {
		return this.lblText.getText();
	}
	
	public void setFlashcardText(String text) {
		this.lblText.setText(text);
	}
}
