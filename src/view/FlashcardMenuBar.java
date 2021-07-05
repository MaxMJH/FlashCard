package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * A view which displays a menu bar.
 * 
 * @author Max
 * @author MaxHarrisMJH@gmail.com
 * @version 0.2
 * @since 0.1
 */
public class FlashcardMenuBar extends MenuBar {
	/*---- Fields ----*/
	private MenuItem saveItem;
	private MenuItem loadItem;
	
	/*---- Constructor ----*/
	/**
	 * Initialises the view and necessary fields.
	 */
	public FlashcardMenuBar() {
		Menu menu = new Menu("File");
		
		this.saveItem = new MenuItem("Save");
		menu.getItems().add(this.saveItem);
		
		this.loadItem = new MenuItem("Load");
		menu.getItems().add(this.loadItem);
		
		this.getMenus().add(menu);
	}
	
	/*---- Handlers ----*/
	/**
	 * Adds an event handler to saveItem.
	 * 
	 * @param handler The event handler.
	 */
	public void addSaveHandler(EventHandler<ActionEvent> handler) {
		this.saveItem.setOnAction(handler);
	}
	
	/**
	 * Adds an event handler to loadItem.
	 * 
	 * @param handler The event handler.
	 */
	public void addLoadHandler(EventHandler<ActionEvent> handler) {
		this.loadItem.setOnAction(handler);
	}
}
