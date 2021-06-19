package view;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;

public class FlashcardPane extends GridPane {
	/*---- Fields ----*/
	private ScrollPane scrollPane;
	private FlowPane flowPane;
	private Button btnCreateFlashcard;
	private List<Button> buttonsArray; 

	/*---- Constructor ----*/
	public FlashcardPane() {
		// Initialise ArrayList that will store buttons (flashcards).
		this.buttonsArray = new ArrayList<>();
		this.btnCreateFlashcard = new Button();
		
		// Setup row constraints.
		RowConstraints row = new RowConstraints();
		row.setVgrow(Priority.ALWAYS);
		this.getRowConstraints().addAll(new RowConstraints(), row, new RowConstraints());
		
		// Setup column constraints.
		ColumnConstraints column = new ColumnConstraints();
		column.setHgrow(Priority.ALWAYS);
		column.setHalignment(HPos.CENTER);
		this.getColumnConstraints().add(column);
		
		// Setup ScrollPane.
		this.scrollPane = new ScrollPane();
		this.scrollPane.setPrefSize(810, 500);
		this.scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.scrollPane.setPadding(new Insets(20, 20, 20, 20));
		this.scrollPane.setFitToWidth(true);
		
		// Set CSS Id for ScrollPane.
		this.scrollPane.setId("flashcard-scroll-pane");
		
		// Setup FlowPane.
		this.flowPane = new FlowPane();
		this.flowPane.setPrefSize(790, 500);	
		this.flowPane.setVgap(20);
		this.flowPane.setHgap(20);
		this.flowPane.setAlignment(Pos.TOP_CENTER);
		
		// Set the ScrollPane's content to the FlowPane that contains flashcards.
		this.scrollPane.setContent(this.flowPane);
		
		// Add controls and labels to container.
		this.add(this.scrollPane, 0, 1);
	}
	
	/*---- Methods ----*/
	public void setupFlashcards() {
		this.btnCreateFlashcard.setWrapText(true);
		this.btnCreateFlashcard.setTextAlignment(TextAlignment.CENTER);
		this.btnCreateFlashcard.setPrefSize(200, 200);
		this.btnCreateFlashcard.setId("default-create-flashcard-button");
		this.flowPane.getChildren().add(this.btnCreateFlashcard);
	}
	
	public void updateFlashcard(String oldFlashcardTitle, String newFlashcardTitle) {
		if(!oldFlashcardTitle.equals(newFlashcardTitle)) {
			this.buttonsArray.forEach(e -> {
				if(e.getText().equals(oldFlashcardTitle)) {
					e.setText(newFlashcardTitle);
				}
			});
		}
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
		this.flowPane.getChildren().clear();
	}
	
	public void addButton(String title) {
		Button button = new Button(title);
		
		this.buttonsArray.add(button);
		
		button.setWrapText(true);
		button.setTextAlignment(TextAlignment.CENTER);
		button.setPrefSize(200, 200);
		button.setId("flashcard");
		this.flowPane.getChildren().add(button);		
	}
	
	public boolean isFlowPaneEmpty() {
		return this.flowPane.getChildren().isEmpty() ? true : false;
	}
	
	public void hideAll() {
		this.flowPane.setVisible(false);
		this.btnCreateFlashcard.setVisible(false);
	}
	
	public void showAll() {
		this.flowPane.setVisible(true);
		this.btnCreateFlashcard.setVisible(true);
	}
	
	public void setHeader(Node flashcardPaneHeader) {
		this.add(flashcardPaneHeader, 0, 0);
	}
	
	public void setFooter(Node flashcardPaneFooter) {
		this.add(flashcardPaneFooter, 0, 2);
	}
	
	/*---- Handlers ----*/
	public void addCreateFlashcardHandler(EventHandler<ActionEvent> handler) {
		this.btnCreateFlashcard.setOnAction(handler);
	}
	
	public void addCreateFlashcardsHandler(EventHandler<ActionEvent> handler) {
		this.buttonsArray.forEach(e -> e.setOnAction(handler));
	}
	
	/*---- Getters and Setters ----*/
	public List<Button> getButtonsArray() {
		return this.buttonsArray;
	}
}
