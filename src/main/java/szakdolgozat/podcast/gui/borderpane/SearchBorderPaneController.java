package szakdolgozat.podcast.gui.borderpane;

import java.util.ArrayList;

import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.PodcastContainer;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.jsonparser.PodcastJsonParser;
import szakdolgozat.podcast.morphia.MorphiaConnector;
import szakdolgozat.podcast.threads.PodcastListener;
import szakdolgozat.podcast.xmlparser.XmlParser;

public class SearchBorderPaneController {
	private PodcastContainer searchPodcastContainer;

	public PodcastContainer getSearchPodcastContainer() {
		return this.searchPodcastContainer;
	}

	public void setSearchPodcastContainer(final PodcastContainer searchPodcastContainer) {
		this.searchPodcastContainer = searchPodcastContainer;
	}

	public SearchBorderPaneController() {

	}

	public void startSearchPodcast(final String searchText) {
		final PodcastJsonParser jsonParser = new PodcastJsonParser(new String(
				"https://itunes.apple.com/search?term=" + searchText + "&entity=podcast&media=podcast&limit=5"));
		this.searchPodcastContainer = jsonParser.getSearchResult();
	}

	public boolean isPodcastSubscribed(final String name) {
		return !(MorphiaConnector.getDataStore().createQuery(Podcast.class).filter("artistName = ", name).asList()
				.isEmpty());
	}

	public void subscribe(final Podcast podcast) {
		final XmlParser xmlParser = new XmlParser(podcast.getFeedUrl());
		podcast.setPodcastEpisode(new ArrayList<PodcastEpisode>(xmlParser.readFeed()));
		MorphiaConnector.getInstance();
		MorphiaConnector.getDataStore().save(podcast);
		// PodcastListener.getInstance().startNewListener();
	}

	public void startNewListener() {
		PodcastListener.getInstance().startNewListener();
	}

}
