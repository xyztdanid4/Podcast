package szakdolgozat.podcast.gui.tabpane;

import javafx.geometry.Side;
import javafx.scene.control.TabPane;
import szakdolgozat.podcast.gui.tab.NotificationTab;
import szakdolgozat.podcast.gui.tab.PlayListTab;
import szakdolgozat.podcast.gui.tab.PodcastListTab;
import szakdolgozat.podcast.gui.tab.SearchTab;
import szakdolgozat.podcast.gui.tab.SettingsTab;

/**
 * The Class ApplicationTabPane.
 * 
 * * @author Daniel Toth
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public class ApplicationTabPane extends TabPane {

	/** The Constant HEIGHT. */
	private static final int HEIGHT = 300;

	/** The Constant WEIGHT. */
	private static final int WEIGHT = 300;

	/** The instance. */
	private static ApplicationTabPane instance = null;

	/**
	 * Instantiates a new application tab pane.
	 */
	private ApplicationTabPane() {
		super(SearchTab.getInstance(), new PodcastListTab(), new PlayListTab(), new NotificationTab(),
				SettingsTab.getInstance());
		setSide(Side.LEFT);
		setTabMaxHeight(HEIGHT);
		setTabMaxWidth(WEIGHT);
	}

	/**
	 * Gets the single instance of ApplicationTabPane.
	 *
	 * @return single instance of ApplicationTabPane
	 */
	public static ApplicationTabPane getInstance() {
		if (instance == null) {
			instance = new ApplicationTabPane();
		}
		return instance;
	}

}
