package szakdolgozat.podcast.gui.tab;

import szakdolgozat.podcast.gui.borderpane.PodcastBorderPane;
import szakdolgozat.podcast.gui.samples.TabSample;

public class PodcastListTab extends TabSample {
	private PodcastBorderPane podcastBorderPane;
	private final String PODCASTLISTTAB_IMAGEPATH = "appbar.list.png";
	private final String PODCASTLISTTAB_TITLE = "Click here to manage your podcasts!";

	public PodcastListTab() {
		podcastBorderPane = new PodcastBorderPane();
		super.makeTab((TabSample) this, PODCASTLISTTAB_IMAGEPATH,
				PODCASTLISTTAB_TITLE);
		setContent(podcastBorderPane);
	}

}
