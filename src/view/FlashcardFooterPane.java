package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class FlashcardFooterPane extends VBox {
	/*---- Fields ----*/
	private Button btnRemoveSubject;
	
	/*---- Constructor ----*/
	public FlashcardFooterPane() {
		// Style VBox.
		this.setAlignment(Pos.CENTER);
		
		// Set CSS Id for VBox.
		this.setId("flashcard-pane-footer");
		
		// Initialise and setup remove subject button.
		this.btnRemoveSubject = new Button("Remove Subject");
		this.btnRemoveSubject.setWrapText(true);
		this.btnRemoveSubject.setTextAlignment(TextAlignment.CENTER);
		this.btnRemoveSubject.setPrefSize(200, 50);
		this.btnRemoveSubject.setId("remove-subject-button");
		
		// Add control to container.
		this.getChildren().add(this.btnRemoveSubject);
	}
	
	/*---- Methods ----*/
	public void setBtnRemoveSubjectVisible(boolean isVisible) {
		this.btnRemoveSubject.setVisible(isVisible);
	}
	
	/*---- Handler ----*/
	public void addRemoveSubjectButton(EventHandler<ActionEvent> handler) {
		this.btnRemoveSubject.setOnAction(handler);
	}
}
