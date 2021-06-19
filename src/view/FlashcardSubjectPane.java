package view;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

public class FlashcardSubjectPane extends ScrollPane {
	/*---- Fields ----*/
	private GridPane grid;
	private Button btnCreateSubject;
	private List<Button> buttonsArray;
	
	/*---- Constructor ----*/
	public FlashcardSubjectPane() {
		// Initialise the ArrayList that will store buttons (subjects).
		this.buttonsArray = new ArrayList<>();
		
		// Style ScrollPane.
		this.setPadding(new Insets(0, 0, 0, -6)); // Moves the buttons to the left.
		this.setPrefSize(190, 500);
		this.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.setFitToWidth(true);
		
		// Set CSS Id for ScrollPane.
		this.setId("subject-scroll-pane");
		
		// Setup GridPane.
		this.grid = new GridPane();
		this.grid.setAlignment(Pos.TOP_LEFT);
		this.grid.setVgap(0.5);
		
		// Set the ScrollPane's content to the GridPane that contains subjects.
		this.setContent(this.grid);

		// Initialise and setup add subject button.
		this.btnCreateSubject = new Button("Create Subject");
		this.btnCreateSubject.setWrapText(true);
		this.btnCreateSubject.setTextAlignment(TextAlignment.CENTER);
		this.btnCreateSubject.setPrefSize(Integer.MAX_VALUE, 100);
		this.btnCreateSubject.setId("subject-button");
		
		// Add controls to container.
		this.grid.add(btnCreateSubject, 0, 0);
	}
	
	/*---- Methods ----*/
	public void addButton(String title) {
		Button button = new Button(title);
		
		button.setWrapText(true);
		button.setTextAlignment(TextAlignment.CENTER);
		button.setPrefSize(Integer.MAX_VALUE, 100);
		button.setId("subject-button");
		
		this.buttonsArray.add(button);
		this.grid.add(button, 0, this.buttonsArray.size() + 1);		
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
	
	public void updateSubject(String oldSubject, String newSubject) {
		if(!oldSubject.equals(newSubject)) {
			this.buttonsArray.forEach(e -> {
				if(e.getText().equals(oldSubject)) {
					e.setText(newSubject);
				}
			});
		}
	}
	
	/*---- Handlers ----*/
	public void addCreateSubjectHandler(EventHandler<ActionEvent> handler) {
		this.btnCreateSubject.setOnAction(handler);
	}
	
	public void addCreateSubjectsHandler(EventHandler<ActionEvent> handler) {
		this.buttonsArray.forEach(e -> e.setOnAction(handler));
	}
	
	/*---- Getters and Setters ----*/
	public List<Button> getButtonsArray() {
		return this.buttonsArray;
	}
}
