package szakdolgozat.podcast.gui.tab;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Tab;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import szakdolgozat.podcast.gui.gridpane.NewLoginGridPane;

public class NewUserLoginTab extends Tab {
	private static final String NEWUSERLOGINTABTEXT = "New User";
	private static final String NEWUSERLOGINTAB_TOOLTIP = "Click here for register!";

	public NewUserLoginTab() {
		super();
		Text text = new Text(NEWUSERLOGINTABTEXT);
		text.setFill(Color.web("#FFFFFF"));
		setGraphic(text);
		// setStyle("-fx-background-color: #808080; -fx-border-color: #006666;
		// -fx-border-width: 4px; -fx-border-radius:3; ;");
		setStyle("-fx-background-color: #808080; -fx-border-radius:3;");
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

		setTooltip(new Tooltip(NEWUSERLOGINTAB_TOOLTIP));
		setClosable(false);
		setContent(new NewLoginGridPane());
	}
}