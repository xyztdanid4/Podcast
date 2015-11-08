package szakdolgozat.podcast.gui.borderpane;

import java.util.List;

import org.mongodb.morphia.query.Query;

import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.morphia.MorphiaConnector;
import szakdolgozat.podcast.threads.PodcastListener;

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

	public void stopListener(final int index) {
		System.out.println("index: " + index);
		PodcastListener.getInstance().getTasks().get(index).cancel();
		PodcastListener.getInstance().getTasks().remove(index);
	}
}
