package szakdolgozat.podcast.gui.borderpane;

import java.util.List;

import org.mongodb.morphia.query.Query;

import javafx.application.Platform;
import szakdolgozat.podcast.builder.HBoxBuilder;
import szakdolgozat.podcast.data.podcast.Podcast;
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

	/*
	 * public void stopListener(final Podcast podcast) { // System.out.println(
	 * "index: " + index); System.out.println("STOP");
	 * System.out.println(podcast); if
	 * (PodcastListener.getInstance().getTasks().get(PodcastListener.getInstance
	 * ().getTasks().indexOf(podcast)) .getPodcast().equals(podcast)) {
	 * System.out.println("ANYÁD"); } //
	 * System.out.println(PodcastListener.getInstance().getTasks() //
	 * .get(PodcastListener.getInstance().getTasks().indexOf(podcast)).
	 * getPodcast()); //
	 * PodcastListener.getInstance().getTasks().get(PodcastListener.getInstance(
	 * ).getTasks().indexOf(podcast)) // .cancel(); //
	 * PodcastListener.getInstance().getTasks().get().getTimer().cancel(); //
	 * PodcastListener.getInstance().getTasks().remove(); }
	 */
}
