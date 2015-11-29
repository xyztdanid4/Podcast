package szakdolgozat.podcast.gui.tab;

import javafx.beans.InvalidationListener;
import javafx.scene.control.Tab;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import szakdolgozat.podcast.gui.gridpane.NewLoginGridPaneView;

/**
 * The Class NewUserLoginTab.
 */
public class NewUserLoginTab extends Tab {

	/** The Constant NEWUSERLOGINTABTEXT. */
	private static final String NEWUSERLOGINTABTEXT = "New User";

	/** The Constant NEWUSERLOGINTAB_TOOLTIP. */
	private static final String NEWUSERLOGINTAB_TOOLTIP = "Click here for register!";

	/**
	 * Instantiates a new new user login tab.
	 */
	public NewUserLoginTab() {
		super();
		final Text text = new Text(NEWUSERLOGINTABTEXT);
		text.setFill(Color.web("#FFFFFF"));
		setGraphic(text);
		setStyle("-fx-background-color: #808080; -fx-border-radius:3;");
		selectedProperty().addListener((InvalidationListener) observable -> {
			if (isSelected()) {
				setStyle("-fx-background-color: #191919; -fx-border-radius:3;");
			} else {
				setStyle("-fx-background-color: #808080; -fx-border-radius:3;");
			}
		});

		setTooltip(new Tooltip(NEWUSERLOGINTAB_TOOLTIP));
		setClosable(false);
		setContent(new NewLoginGridPaneView());
	}
}
