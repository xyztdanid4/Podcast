package szakdolgozat.podcast.gui.borderpane;

import java.util.ArrayList;

import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.PodcastContainer;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.jsonparser.PodcastJsonParser;
import szakdolgozat.podcast.morphia.MorphiaConnector;
import szakdolgozat.podcast.xmlparser.XmlParser;

public class SearchBorderPaneController {
	private PodcastContainer searchPodcastContainer;

	public PodcastContainer getSearchPodcastContainer() {
		return searchPodcastContainer;
	}

	public void setSearchPodcastContainer(PodcastContainer searchPodcastContainer) {
		this.searchPodcastContainer = searchPodcastContainer;
	}

	public SearchBorderPaneController() {

	}

	public void startSearchPodcast(String searchText) {
		PodcastJsonParser jsonParser = new PodcastJsonParser(new String(
				"https://itunes.apple.com/search?term=" + searchText + "&entity=podcast&media=podcast&limit=5"));
		searchPodcastContainer = jsonParser.getSearchResult();
	}

	public boolean isPodcastSubscribed(final String name) {
		return !(MorphiaConnector.getDataStore().createQuery(Podcast.class).filter("artistName = ", name).asList()
				.isEmpty());
	}

	public void subscribe(Podcast podcast) {
		XmlParser xmlParser = new XmlParser(podcast.getFeedUrl());
		podcast.setPodcastEpisode(new ArrayList<PodcastEpisode>(xmlParser.readFeed()));
		MorphiaConnector.getInstance().getDataStore().save(podcast);
	}

}
