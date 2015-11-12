package szakdolgozat.podcast.gui.borderpane;

import java.util.List;

import org.mongodb.morphia.query.Query;

import javafx.application.Platform;
import szakdolgozat.podcast.builder.HBoxBuilder;
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.mediaplayer.MediaControlPodcast;
import szakdolgozat.podcast.morphia.MorphiaConnector;

public class PodcastBoderPaneController {
	private List<Podcast> podcastsFromDBList;

	public PodcastBoderPaneController() {

	}

	public void readfromDB() {
		this.podcastsFromDBList = MorphiaConnector.getDataStore().createQuery(Podcast.class).asList();
	}

	public List<Podcast> getPodcastsFromDBList() {
		return this.podcastsFromDBList;
	}

	public void removefromDB(final String name) {
		final Query<Podcast> deletePodcast = MorphiaConnector.getDataStore().createQuery(Podcast.class)
				.filter("artistName =", name);
		MorphiaConnector.getDataStore().delete(deletePodcast);
	}

	//-.-off
	public void notice(final Podcast podcast) {
		Platform.runLater(() -> NotificationBorderPaneController.getInstance().getNotificationContainer()
				.add(HBoxBuilder.create()
								.smallText("UNSUBSCRIBED: ")
								.artist(podcast.getArtistName())
								.title(podcast.getTrackName())
								.build()));
		Platform.runLater(() -> NotificationBorderPaneController.getInstance().getUnsubscribeContainer()
				.add(HBoxBuilder.create()
								.smallText("UNSUBSCRIBED: ")
								.artist(podcast.getArtistName())
								.title(podcast.getTrackName())
								.build()));
	}
	//-.-on

	public void startNewMediaPlayer(final PodcastEpisode podcastEpisode) {
		try {
			MediaControlPodcast.getInstance().stop();
			MainBorderPane.getInstance().buildBottom(podcastEpisode);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
