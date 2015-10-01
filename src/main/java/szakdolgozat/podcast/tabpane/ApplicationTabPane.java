package szakdolgozat.podcast.tabpane;

import javafx.geometry.Side;
import javafx.scene.control.TabPane;
import szakdolgozat.podcast.gui.tab.DownloadTab;
import szakdolgozat.podcast.gui.tab.PlayListTab;
import szakdolgozat.podcast.gui.tab.PodcastListTab;
import szakdolgozat.podcast.gui.tab.SearchTab;

public class ApplicationTabPane extends TabPane {
	public ApplicationTabPane() {
		setSide(Side.LEFT);
		setTabMaxHeight(300);
		setTabMaxWidth(300);
		getTabs().addAll(new SearchTab(), new PodcastListTab(), new PlayListTab(), new DownloadTab());
	}

}
