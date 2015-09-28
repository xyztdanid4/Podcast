package szakdolgozat.podcast.gui.tab;

import javafx.scene.control.Tab;
import szakdolgozat.podcast.gui.gridpane.LoginGridPane;

public class UserLoginTab extends Tab {
	private LoginGridPane loginGridPane;

	public UserLoginTab() {
		loginGridPane = new LoginGridPane();
		setClosable(false);
		setContent(loginGridPane);
	}
}
