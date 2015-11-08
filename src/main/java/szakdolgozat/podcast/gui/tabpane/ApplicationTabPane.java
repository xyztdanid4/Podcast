package szakdolgozat.podcast.gui.tabpane;

import javafx.geometry.Side;
import javafx.scene.control.TabPane;
import szakdolgozat.podcast.gui.tab.DownloadTab;
import szakdolgozat.podcast.gui.tab.NotificationTab;
import szakdolgozat.podcast.gui.tab.PlayListTab;
import szakdolgozat.podcast.gui.tab.PodcastListTab;
import szakdolgozat.podcast.gui.tab.SearchTab;

public class ApplicationTabPane extends TabPane {
	private static final int HEIGHT = 300;
	private static final int WEIGHT = 300;

	public ApplicationTabPane() {
		super(new SearchTab(), new PodcastListTab(), new PlayListTab(), new DownloadTab(), new NotificationTab());
		setSide(Side.LEFT);
		setTabMaxHeight(HEIGHT);
		setTabMaxWidth(WEIGHT);
	}

}
