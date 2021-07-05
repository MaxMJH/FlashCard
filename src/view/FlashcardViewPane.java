package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

/**
 * A view which shows the content of a flashcard.
 * 
 * @author Max
 * @author MaxHarrisMJH@gmail.com
 * @version 0.2
 * @since 0.1
 */
public class FlashcardViewPane extends GridPane {
	/*---- Fields ----*/
	private Label lblFlashcardText;
	private ScrollPane scrollPane;
	private Button btnBack;
	private Button btnEdit;
	private Button btnRemove;
	
	/*---- Constructor ----*/
	/**
	 * Initialises the view and necessary fields.
	 */
	public FlashcardViewPane() {
		// Setup GridPane.
		this.setAlignment(Pos.CENTER);
		
		// Setup row constraints.
		RowConstraints row = new RowConstraints();
		row.setVgrow(Priority.ALWAYS);
		this.getRowConstraints().addAll(row, new RowConstraints(), new RowConstraints());
		
		// Setup column constraints.
		ColumnConstraints column = new ColumnConstraints();
		column.setHgrow(Priority.ALWAYS);
		column.setHalignment(HPos.CENTER);
		this.getColumnConstraints().addAll(column, column, column);
		
		// Initialise and setup flashcard text label.
		this.lblFlashcardText = new Label("");
		this.lblFlashcardText.setAlignment(Pos.CENTER);
		this.lblFlashcardText.setWrapText(true);
		
		// Setup ScrollPane.
		this.scrollPane = new ScrollPane();
		this.scrollPane.setPrefSize(810, 500);
		this.scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.scrollPane.setPadding(new Insets(20, 20, 20, 20));
		this.scrollPane.setFitToWidth(true);
				
		// Set CSS Id for ScrollPane.
		this.scrollPane.setId("flashcard-scroll-pane");

		// HBox to store the label in, as well as an image.
		HBox box = new HBox();
		box.setAlignment(Pos.CENTER);
		box.setMinSize(810, 500);
		box.getChildren().add(this.lblFlashcardText);
		
		// Sets the ScrollPane content to the HBox.
		this.scrollPane.setContent(box);
		
		// Initialise and setup back button.
		this.btnBack = new Button("Back");
		this.btnBack.setMinSize(200, 50);
		this.btnBack.setId("back-button");
		
		// Initialise and setup edit button.
		this.btnEdit = new Button("Edit");
		this.btnEdit.setMinSize(200, 50);
		this.btnEdit.setId("edit-flashcard-button");
		
		// Initialise and setup remove button.
		this.btnRemove = new Button("Remove");
		this.btnRemove.setMinSize(200, 50);
		this.btnRemove.setId("remove-flashcard-button");
		
		// Add controls and label to container.
		this.add(this.scrollPane, 0, 0, 3, 1);
		this.add(this.btnBack, 0, 1);
		this.add(this.btnEdit, 1, 1);
		this.add(this.btnRemove, 2, 1);
	}
	
	/*---- Handlers ----*/
	/**
	 * Adds an event handler to btnBack.
	 * 
	 * @param handler The event handler.
	 */
	public void addBackFlashcardHandler(EventHandler<ActionEvent> handler) {
		this.btnBack.setOnAction(handler);
	}
	
	/**
	 * Adds an event handler to btnEdit.
	 * 
	 * @param handler The event handler.
	 */
	public void addEditFlashcardHandler(EventHandler<ActionEvent> handler) {
		this.btnEdit.setOnAction(handler);
	}
	
	/**
	 * Adds an event handler to btnRemove.
	 * 
	 * @param handler The event handler.
	 */
	public void addRemoveFlashcardHandler(EventHandler<ActionEvent> handler) {
		this.btnRemove.setOnAction(handler);
	}
	
	/*---- Getters and Setters ----*/
	/**
	 * Gets the current text of lblFlashcardText (Label).
	 * 
	 * @return A String representation of lblFlashcardText.
	 */
	public String getFlashcardText() {
		return this.lblFlashcardText.getText();
	}
	
	/**
	 * Sets the current text of lblFlashcardText (Label).
	 * 
	 * @param text The flashcard's text.
	 */
	public void setFlashcardText(String text) {
		this.lblFlashcardText.setText(text);
	}
	
	/**
	 * Allows the ability to add an image to the flashcard.
	 * 
	 * @param imageURL The path directing to the image.
	 */
	public void setFlashcardImage(String imageURL) {
		if(imageURL != null) {
			Image image = new Image(imageURL);
			ImageView view = new ImageView(image);

			if(image.getHeight() < 1080 && image.getWidth() < 1920) {
				view.setFitHeight(image.getHeight());
				view.setFitWidth(image.getWidth());
			} else {
				view.setFitHeight(1080);
				view.setFitWidth(1920);
			}
			
			this.lblFlashcardText.setGraphic(view);
			
			this.lblFlashcardText.setContentDisplay(ContentDisplay.BOTTOM);
		} else {
			this.lblFlashcardText.setGraphic(null);
		}
	}
}
