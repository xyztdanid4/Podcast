package szakdolgozat.podcast.gui.tab;

import javafx.scene.control.Tab;
import javafx.scene.control.Tooltip;
import szakdolgozat.podcast.gui.gridpane.LoginGridPane;

public class UserLoginTab extends Tab {
	private static final String USERLOGINTABTEXT = "Registered User";
	private static final String USERLOGINTAB_TOOLTIP = "Click here if you have an account!";

	public UserLoginTab() {
		super(USERLOGINTABTEXT, new LoginGridPane());
		setTooltip(new Tooltip(USERLOGINTAB_TOOLTIP));
		setClosable(false);
	}
}
