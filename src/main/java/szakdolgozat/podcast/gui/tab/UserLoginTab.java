package szakdolgozat.podcast.gui.tab;

import javafx.beans.InvalidationListener;
import javafx.scene.control.Tab;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import szakdolgozat.podcast.gui.gridpane.LoginGridPaneView;

/**
 * The Class UserLoginTab.
 * 
 * * @author Daniel Toth
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public class UserLoginTab extends Tab {

	/** The Constant USERLOGINTABTEXT. */
	private static final String USERLOGINTABTEXT = "Registered User";

	/** The Constant USERLOGINTAB_TOOLTIP. */
	private static final String USERLOGINTAB_TOOLTIP = "Click here if you have an account!";

	/**
	 * Instantiates a new user login tab.
	 */
	public UserLoginTab() {
		super();
		final Text text = new Text(USERLOGINTABTEXT);
		text.setFill(Color.web("#FFFFFF"));
		setGraphic(text);
		setStyle("-fx-background-color: #808080;");
		selectedProperty().addListener((InvalidationListener) observable -> {
			if (isSelected()) {
				setStyle("-fx-background-color: #191919; -fx-border-radius:3;");
			} else {
				setStyle("-fx-background-color: #808080; -fx-border-radius:3;");
			}
		});
		setTooltip(new Tooltip(USERLOGINTAB_TOOLTIP));
		setClosable(false);
		setContent(new LoginGridPaneView());
	}
}
