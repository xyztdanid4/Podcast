package szakdolgozat.podcast.gui.gridpane;

import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import szakdolgozat.podcast.gui.stage.LoginStage;
import szakdolgozat.podcast.gui.stage.MainStage;

/**
 * The Class BaseLoginView.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 */
public class BaseLoginView extends GridPane {

	/** The Constant DEFAULTHSPACING. */
	static final private int DEFAULTHSPACING = 10;

	/** The Constant DEFAULTVSPACING. */
	static final private int DEFAULTVSPACING = 10;

	/**
	 * Instantiates a new base login view.
	 */
	public BaseLoginView() {
		setHgap(DEFAULTHSPACING);
		setVgap(DEFAULTVSPACING);
	}

	/**
	 * Show main stage.
	 */
	protected void showMainStage() {
		MainStage.getInstance().show();
	}

	/**
	 * Hide login stage.
	 */
	protected void hideLoginStage() {
		LoginStage.getInstance().hide();
	}

	/**
	 * Exit.
	 */
	protected void exit() {
		Platform.exit();
	}
}
