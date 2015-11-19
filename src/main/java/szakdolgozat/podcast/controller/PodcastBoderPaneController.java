package szakdolgozat.podcast.controller;

import java.util.List;

import org.mongodb.morphia.query.Query;

import javafx.application.Platform;
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.borderpane.MainBorderPaneView;
import szakdolgozat.podcast.gui.borderpane.PodcastBorderPaneView;
import szakdolgozat.podcast.gui.builder.HBoxBuilder;
import szakdolgozat.podcast.gui.mediaplayer.MediaControlPodcast;
import szakdolgozat.podcast.morphia.MorphiaConnector;

/**
 * The PodcastBoderPaneController class
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 * 
 *        This class controls, and store data for {@link PodcastBorderPaneView}
 *        And it is responsible for starting a new media player.
 * 
 * @see MediaControlPodcast
 *
 */

public class PodcastBoderPaneController {
	private static final String UNSUBSCRIBED = "UNSUBSCRIBED: ";
	/**
	 * podcastsFromDBList, Stores data from the database.
	 */
	private List<Podcast> podcastsFromDBList;

	public PodcastBoderPaneController() {

	}

	/**
	 * reads data from the DB.
	 */
	public void readfromDB() {
		this.podcastsFromDBList = MorphiaConnector.getInstance().getDataStore().createQuery(Podcast.class).asList();
	}

	/**
	 * Getter for podcastsFromDBList
	 * 
	 * @return podcastsFromDBList
	 */
	public List<Podcast> getPodcastsFromDBList() {
		return this.podcastsFromDBList;
	}

	/**
	 * Remove the given named object from DB
	 * 
	 * @param name
	 *            parameter, we recognize items in DB by name
	 */
	public void removefromDB(final String name) {
		final Query<Podcast> deletePodcast = MorphiaConnector.getInstance().getDataStore().createQuery(Podcast.class)
				.filter("artistName =", name);
		MorphiaConnector.getInstance().getDataStore().delete(deletePodcast);
	}

	/**
	 * Notice the notificationcontainer with unsubscribe action.
	 * 
	 * @see NotificationBorderPaneController#getNotificationContainer()
	 * @see NotificationBorderPaneController#getUnsubscribeContainer()
	 * @param podcast
	 */
	//-.-off
	public void notice(final Podcast podcast) {
		Platform.runLater(() -> NotificationBorderPaneController.getInstance().getNotificationContainer()
				.add(HBoxBuilder.create()
								.smallText(UNSUBSCRIBED)
								.artist(podcast.getArtistName())
								.title(podcast.getTrackName())
								.build()));
		Platform.runLater(() -> NotificationBorderPaneController.getInstance().getUnsubscribeContainer()
				.add(HBoxBuilder.create()
								.smallText(UNSUBSCRIBED)
								.artist(podcast.getArtistName())
								.title(podcast.getTrackName())
								.build()));
	}
	//-.-on
	/**
	 * Starts a new media player with the given podcastepisode.
	 * 
	 * @see MediaControlPodcast
	 * @see MainBorderPaneView#buildBottom(PodcastEpisode)
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
}
