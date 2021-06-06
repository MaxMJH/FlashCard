package main;

import controller.FlashcardController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.UserFlashcards;
import view.FlashcardRootPane;

public class ApplicationLoader extends Application {
	private FlashcardRootPane view;
	
	@Override
	public void init() {
		this.view = new FlashcardRootPane();
		UserFlashcards model = new UserFlashcards();
		new FlashcardController(this.view, model);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setMinWidth(1000);
		stage.setMinHeight(570);

		stage.setTitle("Flashcards");
		stage.setScene(new Scene(view));
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
