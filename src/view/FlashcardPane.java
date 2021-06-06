package view;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.TextAlignment;

public class FlashcardPane extends ScrollPane {
	private FlowPane pane;
	private Button btnAddFlashcard;
	private List<Button> buttonsArray; 
	
	public FlashcardPane() {
		this.buttonsArray = new ArrayList<>();
		this.btnAddFlashcard = new Button();
		
		this.setPrefSize(810, 500);
		this.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.setPadding(new Insets(20, 20, 20, 20));
		this.setFitToWidth(true);
		
		pane = new FlowPane();
		pane.setPrefSize(790, 500);	
		pane.setVgap(20);
		pane.setHgap(20);
		pane.setAlignment(Pos.TOP_LEFT);

		this.setContent(pane);
	}
	
	public void setupFlashcards() {
		this.btnAddFlashcard.setText("Add Flashcard");
		this.btnAddFlashcard.setWrapText(true);
		this.btnAddFlashcard.setTextAlignment(TextAlignment.CENTER);
		this.btnAddFlashcard.setPrefSize(200, 200);
		this.pane.getChildren().add(this.btnAddFlashcard);
	}
	
	public void clearFlashcards() {
		//this.buttonsArray.clear();
		this.pane.getChildren().clear();
	}
	
	public void addButton(String title) {
		Button button = new Button(title);
		
		this.buttonsArray.add(button);
		
		button.setWrapText(true);
		button.setTextAlignment(TextAlignment.CENTER);
		button.setPrefSize(200, 200);
		this.pane.getChildren().add(button);		
	}
	
	public void addAddFlashcardHandler(EventHandler<ActionEvent> handler) {
		this.btnAddFlashcard.setOnAction(handler);
	}
	
	// Getters and Setters.
	public List<Button> getButtonsArray() {
		return this.buttonsArray;
	}
	
	/*
	public void addFlashcardsToPane(UserFlashcards flashcards) {
		for(int i = 0; i < flashcards.size(); i++) {
			Button button = new Button(flashcards.getFlashcard(i).getFlashcardTitle());
			button.setWrapText(true);
			button.setTextAlignment(TextAlignment.CENTER);
			button.setPrefSize(200, 200);
			pane.getChildren().add(button);
		}
		
		Button button = new Button("Add Flashcard");
		button.setWrapText(true);
		button.setTextAlignment(TextAlignment.CENTER);
		button.setPrefSize(200, 200);
		pane.getChildren().add(button);
	}
	*/
}
