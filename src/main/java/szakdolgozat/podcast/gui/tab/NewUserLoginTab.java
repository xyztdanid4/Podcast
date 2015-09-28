package szakdolgozat.podcast.gui.tab;

import javafx.scene.control.Tab;
import szakdolgozat.podcast.gui.gridpane.NewLoginGridPane;

public class NewUserLoginTab extends Tab {
	private NewLoginGridPane newLoginGridPane;

	public NewUserLoginTab() {
		newLoginGridPane = new NewLoginGridPane();
		setClosable(false);
		setContent(newLoginGridPane);
	}
}
