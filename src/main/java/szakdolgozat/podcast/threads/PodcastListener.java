package szakdolgozat.podcast.threads;

import java.util.Timer;

public class PodcastListener {
	private static PodcastListener instance = null;
	private static Timer timer;
	private static int frequency = 0; /// elso futás miatt most épp teszt, de a
										/// default az legyen 1 óra mondjuk

	private PodcastListener() {
		// timer = new Timer();
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
		return timer;
	}

	public void startListeningToSubscribedPodcasts() {
		final TimerTaskPodcast timerTaskPodcast = new TimerTaskPodcast();
		// TESZT
		// timer.schedule(timerTaskPodcast, 0, 10000);
		// ÉLES:
		timer = new Timer();
		if (frequency == 0) {
			timer.schedule(timerTaskPodcast, 0, 10000); // teszt miatt van igy
														// most
		} else {
			timer.schedule(timerTaskPodcast, 0, 1000 * 60 * 60 * frequency);
		}
	}

}
