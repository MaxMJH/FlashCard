package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;

public class FlashcardFooterPane extends HBox {
	/*---- Fields ----*/
	private Button btnRemoveSubject;
	private Button btnEditSubject;
	
	/*---- Constructor ----*/
	public FlashcardFooterPane() {
		// Style VBox.
		this.setAlignment(Pos.CENTER);
		this.setSpacing(20);
		
		// Set CSS Id for VBox.
		this.setId("flashcard-pane-footer");
		
		// Initialise and setup remove subject button.
		this.btnRemoveSubject = new Button("Remove Subject");
		this.btnRemoveSubject.setWrapText(true);
		this.btnRemoveSubject.setTextAlignment(TextAlignment.CENTER);
		this.btnRemoveSubject.setPrefSize(200, 50);
		this.btnRemoveSubject.setId("remove-subject-button");
		
		// Initialise and setup remove subject button.
		this.btnEditSubject = new Button("Edit Subject");
		this.btnEditSubject.setWrapText(true);
		this.btnEditSubject.setTextAlignment(TextAlignment.CENTER);
		this.btnEditSubject.setPrefSize(200, 50);
		this.btnEditSubject.setId("edit-subject-button");
		
		// Add control to container.
		this.getChildren().addAll(this.btnRemoveSubject, this.btnEditSubject);
	}
	
	/*---- Methods ----*/
	public void setBtnRemoveSubjectVisible(boolean isVisible) {
		this.btnRemoveSubject.setVisible(isVisible);
	}
	
	public void setBtnEditSubjectVisible(boolean isVisible) {
		this.btnEditSubject.setVisible(isVisible);
	}
	
	/*---- Handler ----*/
	public void addRemoveSubjectButton(EventHandler<ActionEvent> handler) {
		this.btnRemoveSubject.setOnAction(handler);
	}
	
	public void addEditSubjectButton(EventHandler<ActionEvent> handler) {
		this.btnEditSubject.setOnAction(handler);
	}
}
