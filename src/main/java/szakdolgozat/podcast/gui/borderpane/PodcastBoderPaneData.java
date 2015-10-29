package szakdolgozat.podcast.gui.borderpane;

import java.util.List;

import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.morphia.MorphiaConnector;

public class PodcastBoderPaneData {
	private List<Podcast> podcastsFromDBList;

	public PodcastBoderPaneData() {

	}

	private void readfromDB() {
		podcastsFromDBList = MorphiaConnector.getDataStore().createQuery(Podcast.class).asList();
	}

}
