package szakdolgozat.podcast.gui.tab;

import javafx.scene.control.Tab;
import javafx.scene.control.Tooltip;
import szakdolgozat.podcast.gui.gridpane.NewLoginGridPane;

public class NewUserLoginTab extends Tab {
	private static final String NEWUSERLOGINTABTEXT = "New User";
	private static final String NEWUSERLOGINTAB_TOOLTIP = "Click here for register!";

	public NewUserLoginTab() {
		super(NEWUSERLOGINTABTEXT, new NewLoginGridPane());
		setTooltip(new Tooltip(NEWUSERLOGINTAB_TOOLTIP));
		setClosable(false);
	}
}
