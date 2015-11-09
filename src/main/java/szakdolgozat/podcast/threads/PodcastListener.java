package szakdolgozat.podcast.threads;

import java.util.Timer;

public class PodcastListener {
	private static PodcastListener instance = null;
	private static Timer timer;

	private PodcastListener() {
		timer = new Timer();
	}

	public static PodcastListener getInstance() {
		if (instance == null) {
			instance = new PodcastListener();
		}
		return instance;
	}

	public void startListeningToSubscribedPodcasts() {
		final TimerTaskPodcast timerTaskPodcast = new TimerTaskPodcast();
		timer.schedule(timerTaskPodcast, 0, 10000);
	}

}
