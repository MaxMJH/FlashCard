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

/**
 * A view which allows the user to add subjects.
 * 
 * @author Max
 * @author MaxHarrisMJH@gmail.com
 * @version 0.2
 * @since 0.1
 */
public class FlashcardSubjectPane extends ScrollPane {
	/*---- Fields ----*/
	private GridPane grid;
	private Button btnCreateSubject;
	private List<Button> buttonsArray;
	
	/*---- Constructor ----*/
	/**
	 * Initialises the view and necessary fields.
	 */
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
	/**
	 * Adds a subject (Button) to the view.
	 * 
	 * @param title Title of the subject.
	 */
	public void addButton(String title) {
		Button button = new Button(title);
		
		button.setWrapText(true);
		button.setTextAlignment(TextAlignment.CENTER);
		button.setPrefSize(Integer.MAX_VALUE, 100);
		button.setId("subject-button");
		
		this.buttonsArray.add(button);
		this.grid.add(button, 0, this.buttonsArray.size() + 1);		
	}
	
	/**
	 * Removes a subject by specifying an existing subject.
	 * 
	 * @param subject Title of subject to be removed.
	 */
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
	
	/**
	 * Removes all subjects from the view.
	 */
	public void removeAllSubjects() {
		this.buttonsArray.clear();
		
		this.grid.getChildren().remove(1, this.grid.getChildren().size());
	}
	
	/**
	 * Updates a subject's title by passing an existing subject title as well as the new subject title.
	 * 
	 * @param oldSubject Title of subject that the user wants to replace.
	 * @param newSubject Title in which the subject will replace.
	 */
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
	/**
	 * Adds an event handler to btnCreateSubject.
	 * 
	 * @param handler
	 */
	public void addCreateSubjectHandler(EventHandler<ActionEvent> handler) {
		this.btnCreateSubject.setOnAction(handler);
	}
	
	/**
	 * Adds an event handler to newly created subjects (Button).
	 * 
	 * @param handler The event handler.
	 */
	public void addCreateSubjectsHandler(EventHandler<ActionEvent> handler) {
		this.buttonsArray.forEach(e -> e.setOnAction(handler));
	}
	
	/*---- Getters and Setters ----*/
	/**
	 * Returns the array in which all subjects (Button) are stored.
	 * 
	 * @return The array in which all subjects (Button) are stored.
	 */
	public List<Button> getButtonsArray() {
		return this.buttonsArray;
	}
}
