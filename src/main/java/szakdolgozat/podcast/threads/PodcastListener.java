package szakdolgozat.podcast.threads;

import java.util.Timer;

import szakdolgozat.podcast.data.podcast.Podcast;

public class PodcastListener {
	private static PodcastListener instance = null;
	private static Timer timerSubscriber;
	private static int frequency = 0; /// elso futás miatt most épp teszt, de a
										/// default az legyen 1 óra mondjuk

	private static Timer timerDownloader;

	private PodcastListener() {

	}

	public void setFrequency(final int frequency) {
		PodcastListener.frequency = frequency;
	}

	public static PodcastListener getInstance() {
		if (instance == null) {
			instance = new PodcastListener();
		}
		return instance;
	}

	public Timer getTimer() {
		return timerSubscriber;
	}

	public void startListeningToSubscribedPodcasts() {
		final TimerTaskPodcast timerTaskPodcast = new TimerTaskPodcast();
		// TESZT
		// timer.schedule(timerTaskPodcast, 0, 10000);
		// ÉLES:
		timerSubscriber = new Timer();
		if (frequency == 0) {
			timerSubscriber.schedule(timerTaskPodcast, 0, 10000); // teszt miatt
																	// van igy
			// most
		} else {
			timerSubscriber.schedule(timerTaskPodcast, 0, 1000 * 60 * 60 * frequency);
		}
	}

	public void startDownload(final Podcast podcast) {
		final TimerTaskDownload timerTaskDownload = new TimerTaskDownload(podcast);
		timerDownloader = new Timer();
		timerDownloader.schedule(timerTaskDownload, 0);
	}

}
