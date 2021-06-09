package view;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

public class FlashcardSubjectPane extends ScrollPane {
	private GridPane grid;
	private Button btnAddSubject;
	private List<Button> buttonsArray;
	
	public FlashcardSubjectPane() {
		this.setId("subject-scroll-pane");
		
		this.buttonsArray = new ArrayList<>();
		
		this.setPrefSize(190, 500);
		this.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.setFitToWidth(true);
		
		this.grid = new GridPane();
		this.grid.setAlignment(Pos.TOP_LEFT);
		this.grid.setVgap(0.5);
		
		this.setContent(grid);

		this.btnAddSubject = new Button("Add Subject");
		this.btnAddSubject.setWrapText(true);
		this.btnAddSubject.setTextAlignment(TextAlignment.CENTER);
		this.btnAddSubject.setPrefSize(Integer.MAX_VALUE, 100);
		this.btnAddSubject.setId("subject-button");
		
		this.grid.add(btnAddSubject, 0, 0);
	}
	
	// Methods.
	public void addButton(String title) {
		Button button = new Button(title);
		
		this.buttonsArray.add(button);
		
		button.setWrapText(true);
		button.setTextAlignment(TextAlignment.CENTER);
		button.setPrefSize(Integer.MAX_VALUE, 100);
		button.setId("subject-button");
		this.grid.add(button, 0, this.buttonsArray.size() + 1);		
	}
	
	public void fireLastButton(Button button) {
		button.fire();
	}
	
	public Button getLastButton() {
		return this.buttonsArray.get(this.buttonsArray.size() - 1);
	}
	
	public void removeSubject(String subject) {
		for(int i = 0; i < this.buttonsArray.size(); i++) {
			Button currentButton = this.buttonsArray.get(i);
			
			if(currentButton.getText().equals(subject)) {
				this.grid.getChildren().remove(i + 1);
				this.buttonsArray.remove(i);
				break;
			}
		}
	}
	
	public void removeAllSubjects() {
		this.buttonsArray.clear();
		
		this.grid.getChildren().remove(1, this.grid.getChildren().size());
	}
	
	// Handlers.
	public void addAddSubjectHandler(EventHandler<ActionEvent> handler) {
		this.btnAddSubject.setOnAction(handler);
	}
	
	public void addPressSubjectHandler(EventHandler<ActionEvent> handler) {
		this.buttonsArray.forEach(e -> e.setOnAction(handler));
	}
	
	// Getters and Setters.
	public List<Button> getButtonsArray() {
		return this.buttonsArray;
	}
}
