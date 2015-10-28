package szakdolgozat.podcast.tabpane;

import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import szakdolgozat.podcast.gui.tab.NewUserLoginTab;
import szakdolgozat.podcast.gui.tab.UserLoginTab;

public class LoginTabPane extends TabPane {
	private static final String TOOLTIP = "This is LoginTabPane!";

	public LoginTabPane() {
		super(new NewUserLoginTab(), new UserLoginTab());
		setTooltip(new Tooltip(TOOLTIP));
	}
}
