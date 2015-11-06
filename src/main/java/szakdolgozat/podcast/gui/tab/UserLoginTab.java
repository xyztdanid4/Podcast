package szakdolgozat.podcast.gui.tab;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Tab;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import szakdolgozat.podcast.gui.gridpane.LoginGridPaneView;

public class UserLoginTab extends Tab {
	private static final String USERLOGINTABTEXT = "Registered User";
	private static final String USERLOGINTAB_TOOLTIP = "Click here if you have an account!";

	public UserLoginTab() {
		super();
		Text text = new Text(USERLOGINTABTEXT);
		text.setFill(Color.web("#FFFFFF"));
		setGraphic(text);
		// setStyle("-fx-background-color: #808080; -fx-border-color: #006666;
		// -fx-border-width: 3px;");
		setStyle("-fx-background-color: #808080;");
		selectedProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable observable) {
				if (isSelected()) {
					setStyle("-fx-background-color: #191919; -fx-border-radius:3;");
				} else {
					setStyle("-fx-background-color: #808080; -fx-border-radius:3;");
				}
			}
		});
		setTooltip(new Tooltip(USERLOGINTAB_TOOLTIP));
		setClosable(false);
		setContent(new LoginGridPaneView());
	}
}
