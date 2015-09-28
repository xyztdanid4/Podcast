package szakdolgozat.podcast.tabpane;

import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import szakdolgozat.podcast.gui.tab.NewUserLoginTab;
import szakdolgozat.podcast.gui.tab.UserLoginTab;

public class LoginTabPane extends TabPane {
	private NewUserLoginTab newUserloginTab;
	private UserLoginTab userLoginTab;
	private final String NEWUSERLOGINTABTEXT = "New User";
	private final String USERLOGINTABTEXT = "Registered User";
	private final String NEWUSERLOGINTAB_TOOLTIP = "Click here for register!";
	private final String USERLOGINTAB_TOOLTIP = "Click here if you have an account!";

	public LoginTabPane() {
		newUserloginTab = new NewUserLoginTab();
		userLoginTab = new UserLoginTab();
		newUserloginTab.setText(NEWUSERLOGINTABTEXT);
		userLoginTab.setText(USERLOGINTABTEXT);
		newUserloginTab.setTooltip(new Tooltip(NEWUSERLOGINTAB_TOOLTIP));
		userLoginTab.setTooltip(new Tooltip(USERLOGINTAB_TOOLTIP));
		getTabs().add(newUserloginTab);
		getTabs().add(userLoginTab);
	}
}
