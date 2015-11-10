package szakdolgozat.podcast.gui.gridpane;

import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import szakdolgozat.podcast.gui.stage.LoginStage;
import szakdolgozat.podcast.gui.stage.MainStage;

public class BaseLoginView extends GridPane {
	static final private int DEFAULTHSPACING = 10;
	static final private int DEFAULTVSPACING = 10;

	public BaseLoginView() {
		setHgap(DEFAULTHSPACING);
		setVgap(DEFAULTVSPACING);
	}

	protected void showMainStage() {
		MainStage.getInstance().show();
	}

	protected void hideLoginStage() {
		LoginStage.getInstance().hide();
	}

	protected void exit() {
		Platform.exit();
	}
}
