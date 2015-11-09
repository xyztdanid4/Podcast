package szakdolgozat.podcast.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.morphia.MorphiaConnector;

public class PodcastListener {
	private static PodcastListener instance = null;
	private static List<Podcast> podcastsFromDBList;
	private static Timer timer;
	private static List<TimerTaskPodcast> tasks;
	private static List<Podcast> podcasts;

	private PodcastListener() {
		timer = new Timer();
		tasks = new ArrayList<TimerTaskPodcast>();
		podcasts = new ArrayList<Podcast>();
	}

	public static List<Podcast> getPodcastsFromDBList() {
		return podcastsFromDBList;
	}

	public List<TimerTaskPodcast> getTasks() {
		return tasks;
	}

	public static PodcastListener getInstance() {
		if (instance == null) {
			instance = new PodcastListener();
		}
		return instance;
	}

	// ez akkor fut le mikor a program elindul
	public void startListeningToSubscribedPodcasts() {
		podcastsFromDBList = MorphiaConnector.getDataStore().createQuery(Podcast.class).asList();
		// for (int i = 0; i < podcastsFromDBList.size(); i++) {
		for (final Podcast podcast : podcastsFromDBList) {
			System.out.println("startListeningToSubscribedPodcasts: " + podcast);
			final TimerTaskPodcast timerTaskPodcast = new TimerTaskPodcast(podcast);
			tasks.add(timerTaskPodcast);
			podcasts.add(podcast);
			timer.schedule(timerTaskPodcast, 0, 10000);
		}

		// }
	}

	// ez meg mikor ujat veszünk fel
	public void startNewListener(final Podcast podcast) {
		podcastsFromDBList = MorphiaConnector.getDataStore().createQuery(Podcast.class).asList();
		final TimerTaskPodcast timerTaskPodcast = new TimerTaskPodcast(podcast);
		tasks.add(timerTaskPodcast);
		podcasts.add(podcast);
		timer.schedule(timerTaskPodcast, 0, 10000);
	}

	public void stopAll(final Podcast podcast) {
		// for (final TimerTaskPodcast podcast : tasks) {
		// podcast.getTimer().cancel(); //SZAR
		// podcast.cancel();
		tasks.get(podcasts.indexOf(podcast)).cancel();
		tasks.remove(podcasts.indexOf(podcast));
		podcasts.remove(podcast);
		// }
	}

}
