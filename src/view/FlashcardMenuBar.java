package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class FlashcardMenuBar extends MenuBar {
	private MenuItem saveItem, loadItem;
	
	public FlashcardMenuBar() {
		Menu menu = new Menu("File");
		
		this.saveItem = new MenuItem("Save");
		menu.getItems().add(this.saveItem);
		
		this.loadItem = new MenuItem("Load");
		menu.getItems().add(this.loadItem);
		
		this.getMenus().add(menu);
	}
	
	public void addSaveHandler(EventHandler<ActionEvent> handler) {
		this.saveItem.setOnAction(handler);
	}
	
	public void addLoadHandler(EventHandler<ActionEvent> handler) {
		this.loadItem.setOnAction(handler);
	}
}
