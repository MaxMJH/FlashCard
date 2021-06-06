package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class FlashcardMenuBar extends MenuBar {
	public FlashcardMenuBar() {
		Menu menu = new Menu("File");
		this.getMenus().add(menu);
	}
}
