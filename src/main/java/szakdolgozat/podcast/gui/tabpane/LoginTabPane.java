package szakdolgozat.podcast.gui.tabpane;

import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import szakdolgozat.podcast.gui.tab.NewUserLoginTab;
import szakdolgozat.podcast.gui.tab.UserLoginTab;

/**
 * The Class LoginTabPane.
 * 
 * * @author Daniel Toth
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public class LoginTabPane extends TabPane {

	/** The Constant TOOLTIP. */
	private static final String TOOLTIP = "This is LoginTabPane!";

	/**
	 * Instantiates a new login tab pane.
	 */
	public LoginTabPane() {
		super(new NewUserLoginTab(), new UserLoginTab());
		setTooltip(new Tooltip(TOOLTIP));
	}
}
