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
		this.buttonsArray = new ArrayList<>();
		
		this.setPrefSize(190, 500);
		this.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.setFitToWidth(true);
		
		this.grid = new GridPane();
		this.grid.setAlignment(Pos.TOP_LEFT);
		this.grid.setVgap(0.5);
			
		this.setContent(grid);
		
		this.btnAddSubject = new Button("Add Subject");
		btnAddSubject.setWrapText(true);
		btnAddSubject.setTextAlignment(TextAlignment.CENTER);
		btnAddSubject.setPrefSize(Integer.MAX_VALUE, 100);
		this.grid.add(btnAddSubject, 0, 0);
	}
	
	// Methods.
	public void addButton(String title) {
		Button button = new Button(title);
		
		this.buttonsArray.add(button);
		
		button.setWrapText(true);
		button.setTextAlignment(TextAlignment.CENTER);
		button.setPrefSize(Integer.MAX_VALUE, 100);
		this.grid.add(button, 0, this.buttonsArray.size() + 1);		
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
	
	/*
	public void addSubjectsToPane(UserFlashcards flashcards) {
		Button button;
		
		for(int i = 0; i < flashcards.size(); i++) {
			button = new Button(flashcards.getFlashcard(i).getSubject().toString());
			button.setWrapText(true);
			button.setTextAlignment(TextAlignment.CENTER);
			button.setPrefSize(Integer.MAX_VALUE, 100);
			this.grid.add(button, 0, i);
		}
		
		button = new Button("Add Subject");
		button.setWrapText(true);
		button.setTextAlignment(TextAlignment.CENTER);
		button.setPrefSize(Integer.MAX_VALUE, 100);
		this.grid.add(button, 0, flashcards.size() + 1);
	}
	*/
}
