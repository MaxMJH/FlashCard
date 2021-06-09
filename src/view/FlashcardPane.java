package view;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;

public class FlashcardPane extends GridPane {
	// Fields.
	private ScrollPane scrollPane;
	private FlowPane flowPane;
	private Button btnAddFlashcard;
	private Button btnRemoveSubject;
	private List<Button> buttonsArray; 
	private Label lblCurrentSubject;
	
	// Constructor.
	public FlashcardPane() {
		this.buttonsArray = new ArrayList<>();
		this.btnAddFlashcard = new Button();
		
		RowConstraints row = new RowConstraints();
		row.setVgrow(Priority.ALWAYS);
		
		ColumnConstraints column = new ColumnConstraints();
		column.setHgrow(Priority.ALWAYS);
		column.setHalignment(HPos.CENTER);
		
		this.getColumnConstraints().add(column);
		this.getRowConstraints().addAll(new RowConstraints(), row, new RowConstraints());
		
		this.scrollPane = new ScrollPane();
		this.scrollPane.setPrefSize(810, 500);
		this.scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.scrollPane.setPadding(new Insets(20, 20, 20, 20));
		this.scrollPane.setFitToWidth(true);
		
		this.flowPane = new FlowPane();
		this.flowPane.setPrefSize(790, 500);	
		this.flowPane.setVgap(20);
		this.flowPane.setHgap(20);
		this.flowPane.setAlignment(Pos.TOP_CENTER);

		this.scrollPane.setContent(this.flowPane);
		
		HBox footer = new HBox();
		
		footer.setId("flashcard-pane-footer");
		
		this.btnRemoveSubject = new Button("Remove Flashcard");
		this.btnRemoveSubject.setWrapText(true);
		this.btnRemoveSubject.setTextAlignment(TextAlignment.CENTER);
		this.btnRemoveSubject.setPrefSize(200, 50);
		
		footer.setAlignment(Pos.CENTER);
		
		footer.getChildren().add(this.btnRemoveSubject);
		
		this.flowPane.getChildren().add(footer);
		
		this.lblCurrentSubject = new Label("");
		this.lblCurrentSubject.setWrapText(true);
		this.lblCurrentSubject.setAlignment(Pos.CENTER);
		this.lblCurrentSubject.setTextAlignment(TextAlignment.CENTER);
		this.lblCurrentSubject.setPrefSize(810, 50);
		
		this.add(this.lblCurrentSubject, 0, 0);
		this.add(this.scrollPane, 0, 1);
		this.add(footer, 0, 2);
	}
	
	// Methods.
	public void setupFlashcards() {
		this.btnAddFlashcard.setText("Add Flashcard");
		this.btnAddFlashcard.setWrapText(true);
		this.btnAddFlashcard.setTextAlignment(TextAlignment.CENTER);
		this.btnAddFlashcard.setPrefSize(200, 200);
		this.flowPane.getChildren().add(this.btnAddFlashcard);
	}
	
	public void updateFlashcard(String oldFlashcardTitle, String newFlashcardTitle) {
		this.buttonsArray.forEach(e -> {
			if(e.getText().equals(oldFlashcardTitle)) {
				e.setText(newFlashcardTitle);
			}
		});
	}
	
	public void removeFlashcard(String flashcardTitle) {
		for(int i = 0; i < this.buttonsArray.size(); i++) {
			Button currentButton = this.buttonsArray.get(i);
			
			if(currentButton.getText().equals(flashcardTitle)) {
				this.flowPane.getChildren().remove(i + 1);
				this.buttonsArray.remove(i);
				break;
			}
		}
	}
	
	public void clearFlashcards() {
		//this.buttonsArray.clear();
		this.flowPane.getChildren().clear();
	}
	
	public void addButton(String title) {
		Button button = new Button(title);
		
		this.buttonsArray.add(button);
		
		button.setWrapText(true);
		button.setTextAlignment(TextAlignment.CENTER);
		button.setPrefSize(200, 200);
		this.flowPane.getChildren().add(button);		
	}
	
	public boolean isFlowPaneEmpty() {
		return this.flowPane.getChildren().isEmpty() ? true : false;
	}
	
	public void setBtnRemoveSubjectVisible(boolean isVisible) {
		this.btnRemoveSubject.setVisible(isVisible);
	}
	
	public void hideAll() {
		this.flowPane.setVisible(false);
		this.btnAddFlashcard.setVisible(false);
		this.btnRemoveSubject.setVisible(false);
		this.lblCurrentSubject.setVisible(false);
	}
	
	public void showAll() {
		this.flowPane.setVisible(true);
		this.btnAddFlashcard.setVisible(true);
		this.btnRemoveSubject.setVisible(true);
		this.lblCurrentSubject.setVisible(true);
	}
	
	// Handlers.
	public void addAddFlashcardHandler(EventHandler<ActionEvent> handler) {
		this.btnAddFlashcard.setOnAction(handler);
	}
	
	public void addPressFlashcardHandler(EventHandler<ActionEvent> handler) {
		this.buttonsArray.forEach(e -> e.setOnAction(handler));
	}
	
	public void addRemoveSubjectButton(EventHandler<ActionEvent> handler) {
		this.btnRemoveSubject.setOnAction(handler);
	}
	
	// Getters and Setters.
	public List<Button> getButtonsArray() {
		return this.buttonsArray;
	}
	
	public void setCurrentSubject(String currentSubject) {
		this.lblCurrentSubject.setText(currentSubject);
	}
}
