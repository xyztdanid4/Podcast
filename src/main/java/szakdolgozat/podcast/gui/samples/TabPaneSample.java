package szakdolgozat.podcast.gui.samples;

import javafx.geometry.Side;
import javafx.scene.control.TabPane;
import szakdolgozat.podcast.gui.tab.DownloadTab;
import szakdolgozat.podcast.gui.tab.PlayListTab;
import szakdolgozat.podcast.gui.tab.PodcastListTab;
import szakdolgozat.podcast.gui.tab.SearchTab;

public class TabPaneSample extends TabPane {
	private SearchTab searchTab;
	private TabSample podcastListTab;
	private TabSample playListTab;
	private TabSample downloadTab;

	public TabPaneSample() {
		setSide(Side.LEFT);
		setTabMaxHeight(300);
		setTabMaxWidth(400);
		searchTab = new SearchTab();
		podcastListTab = new PodcastListTab();
		playListTab = new PlayListTab();
		downloadTab = new DownloadTab();
		getTabs().add(searchTab);
		getTabs().add(podcastListTab);
		getTabs().add(playListTab);
		getTabs().add(downloadTab);
	}

}
