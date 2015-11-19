package szakdolgozat.podcast.gui.stage;

import javafx.scene.Scene;
import szakdolgozat.podcast.controller.MainStageController;
import szakdolgozat.podcast.gui.borderpane.MainBorderPaneView;

/**
 * The Class MainStage.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 */
public class MainStage extends BaseStage {

	/** The Constant TITLE. */
	private static final String TITLE = "PodcastApp";

	/** The instance. */
	private static MainStage instance = null;

	/** The Constant DEFAULTHSIZE. */
	private static final int DEFAULTHSIZE = 1300;

	/** The Constant DEFAULTVSIZE. */
	private static final int DEFAULTVSIZE = 700;

	/**
	 * Instantiates a new main stage.
	 *
	 * @param title
	 *            the title
	 */
	private MainStage(final String title) {
		super(title);
		final Scene mainScene = new Scene(MainBorderPaneView.getInstance(), DEFAULTHSIZE, DEFAULTVSIZE);
		mainScene.getStylesheets().add("listcell.css");
		setScene(mainScene);
		MainStageController.getInstance();
	}

	/**
	 * Gets the single instance of MainStage.
	 *
	 * @return single instance of MainStage
	 */
	public static MainStage getInstance() {
		if (instance == null) {
			instance = new MainStage(TITLE);
		}
		return instance;
	}

}
