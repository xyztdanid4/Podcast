package szakdolgozat.podcast.gui.tabpane;

import javafx.geometry.Side;
import javafx.scene.control.TabPane;
import szakdolgozat.podcast.gui.tab.SettingsTab;
import szakdolgozat.podcast.gui.tab.NotificationTab;
import szakdolgozat.podcast.gui.tab.PlayListTab;
import szakdolgozat.podcast.gui.tab.PodcastListTab;
import szakdolgozat.podcast.gui.tab.SearchTab;

public class ApplicationTabPane extends TabPane {
	private static final int HEIGHT = 300;
	private static final int WEIGHT = 300;
	private static ApplicationTabPane instance = null;

	private ApplicationTabPane() {
		super(SearchTab.getInstance(), new PodcastListTab(), new PlayListTab(), new SettingsTab(),
				new NotificationTab());
		setSide(Side.LEFT);
		setTabMaxHeight(HEIGHT);
		setTabMaxWidth(WEIGHT);
	}

	public static ApplicationTabPane getInstance() {
		if (instance == null) {
			instance = new ApplicationTabPane();
		}
		return instance;
	}

}
