package szakdolgozat.podcast.controller;

import java.util.ArrayList;

import javafx.application.Platform;
import szakdolgozat.podcast.builder.HBoxBuilder;
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.PodcastContainer;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.borderpane.MainBorderPaneView;
import szakdolgozat.podcast.gui.borderpane.SearchBorderPaneView;
import szakdolgozat.podcast.gui.mediaplayer.MediaControlPodcast;
import szakdolgozat.podcast.jsonparser.PodcastJsonParser;
import szakdolgozat.podcast.morphia.MorphiaConnector;
import szakdolgozat.podcast.threads.PodcastListener;
import szakdolgozat.podcast.xmlparser.XmlParser;

/**
 * The Class SearchBorderPaneController.
 *
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 *
 *        This class is represent the data and control actions for the
 *        {@link SearchBorderPaneView}
 *
 */
public class SearchBorderPaneController {
	/**
	 * searchPodcastContainer, stores data which is connected by search.
	 */
	private PodcastContainer searchPodcastContainer;

	/**
	 * Getter for searchPodcastContainer.
	 * 
	 * @return searchPodcastContainer
	 */
	public PodcastContainer getSearchPodcastContainer() {
		return this.searchPodcastContainer;
	}

	/**
	 * Setter for searchPodcastContainer.
	 * 
	 * @param searchPodcastContainer
	 */
	public void setSearchPodcastContainer(final PodcastContainer searchPodcastContainer) {
		this.searchPodcastContainer = searchPodcastContainer;
	}

	public SearchBorderPaneController() {

	}

	/**
	 * This method is responsible for start the search.
	 * 
	 * @param searchText
	 */
	public void startSearchPodcast(final String searchText) {
		final PodcastJsonParser jsonParser = new PodcastJsonParser(new String(
				"https://itunes.apple.com/search?term=" + searchText + "&entity=podcast&media=podcast&limit=5"));
		this.searchPodcastContainer = jsonParser.getSearchResult();
	}

	/**
	 * Decides if the podcast is contained by the DB.
	 * 
	 * @param name
	 * @return if the podcast is contained by the DB.
	 */
	public boolean isPodcastSubscribed(final String name) {
		return !(MorphiaConnector.getDataStore().createQuery(Podcast.class).filter("artistName = ", name).asList()
				.isEmpty());
	}

	/**
	 * This method is responsible for subscribe. This action includes writing to
	 * database.
	 * 
	 * @param podcast
	 */
	public void subscribe(final Podcast podcast) {
		final XmlParser xmlParser = new XmlParser(podcast.getFeedUrl());
		podcast.setPodcastEpisode(new ArrayList<PodcastEpisode>(xmlParser.readFeed()));
		MorphiaConnector.getInstance();
		MorphiaConnector.getDataStore().save(podcast);
	}

	/**
	 * This method is responsible for notify the notification module if the we
	 * would like to subscribe with the parameter podcast.
	 * 
	 * @param podcast
	 */
	//-.-off
		public void notice(final Podcast podcast) {
			Platform.runLater(() -> NotificationBorderPaneController.getInstance().getNotificationContainer()
					.add(HBoxBuilder.create()
									.smallText("SUBSCRIBED: ")
									.artist(podcast.getArtistName())
									.title(podcast.getTrackName())
									.build()));
			Platform.runLater(() -> NotificationBorderPaneController.getInstance().getSubscribeContainer()
					.add(HBoxBuilder.create()
									.smallText("SUBSCRIBED: ")
									.artist(podcast.getArtistName())
									.title(podcast.getTrackName())
									.build()));
		}
		//-.-on
	/**
	 * This method indicates a steaming player. That is because the data is no
	 * downloaded yet.
	 * 
	 * @param podcastEpisode
	 */
	public void startNewMediaPlayer(final PodcastEpisode podcastEpisode) {
		try {
			MediaControlPodcast.getInstance().stop();
			MainBorderPaneView.getInstance().buildBottom(podcastEpisode);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public void startDownload(final Podcast podcast) {
		PodcastListener.getInstance().startDownload(podcast);
	}
}
