package szakdolgozat.podcast.gui.stage;

import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * The Class BaseStage.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 */
public class BaseStage extends Stage {

	/**
	 * Instantiates a new base stage.
	 *
	 * @param title
	 *            the title
	 */
	public BaseStage(final String title) {
		setTitle(title);
		setResizable(false);
		setOnCloseAction();
	}

	/**
	 * Sets the on close action.
	 */
	private void setOnCloseAction() {
		setOnCloseRequest(event -> {
			Platform.exit();
			System.exit(0);
		});
	}

}
