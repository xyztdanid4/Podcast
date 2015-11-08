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

	private PodcastListener() {
		timer = new Timer();
		tasks = new ArrayList<TimerTaskPodcast>();
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
		for (int i = 0; i < podcastsFromDBList.size(); i++) {
			final TimerTaskPodcast timerTaskPodcast = new TimerTaskPodcast(i);
			tasks.add(timerTaskPodcast);
			timer.schedule(timerTaskPodcast, 0, 10000);
			// timerTaskPodcast.cancel();
			// timer.schedule(new TimerTaskPodcast(i), 0, 10000);

		}
	}

	// ez meg mikor ujat veszünk fel
	public void startNewListener() {
		podcastsFromDBList = MorphiaConnector.getDataStore().createQuery(Podcast.class).asList();
		final TimerTaskPodcast timerTaskPodcast = new TimerTaskPodcast((podcastsFromDBList.size() - 1));
		tasks.add(timerTaskPodcast);
		timer.schedule(timerTaskPodcast, 0, 10000);
		// timer.cancel();
		// timer.schedule(new TimerTaskPodcast(podcastsFromDBList.size() - 1),
		// 0, 10000);
	}
}
