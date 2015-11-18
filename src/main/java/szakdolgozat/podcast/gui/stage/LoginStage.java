package szakdolgozat.podcast.gui.stage;

import javafx.scene.Scene;
import szakdolgozat.podcast.gui.tabpane.LoginTabPane;

/**
 * The Class LoginStage.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 */
public class LoginStage extends BaseStage {

	/** The Constant LOGINSTAGETEXT. */
	private static final String LOGINSTAGETEXT = "Login!";

	/** The instance. */
	private static LoginStage instance = null;

	/** The Constant DEFAULTHSIZE. */
	private static final int DEFAULTHSIZE = 400;

	/** The Constant DEFAULTVSIZE. */
	private static final int DEFAULTVSIZE = 300;

	/**
	 * Instantiates a new login stage.
	 *
	 * @param title
	 *            the title
	 */
	private LoginStage(final String title) {
		super(title);
		setScene(new Scene(new LoginTabPane(), DEFAULTHSIZE, DEFAULTVSIZE));
	}

	/**
	 * Gets the single instance of LoginStage.
	 *
	 * @return single instance of LoginStage
	 */
	public static LoginStage getInstance() {
		if (instance == null) {
			instance = new LoginStage(LOGINSTAGETEXT);
		}
		return instance;
	}

}
