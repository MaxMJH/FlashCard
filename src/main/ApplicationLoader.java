package main;

import controller.FlashcardController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.UserFlashcards;
import view.FlashcardRootPane;

public class ApplicationLoader extends Application {
	/*---- Fields ----*/
	private FlashcardRootPane view;
	
	@Override
	public void init() {
		this.view = new FlashcardRootPane();
		UserFlashcards model = new UserFlashcards();
		new FlashcardController(this.view, model);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setMinWidth(1200);
		stage.setMinHeight(570);

		Scene scene = new Scene(view);
		scene.getStylesheets().add(getClass().getResource("/resources/css/Application.css").toExternalForm());
		
		stage.setTitle("Flashcards");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
