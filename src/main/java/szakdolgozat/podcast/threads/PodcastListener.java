package szakdolgozat.podcast.threads;

import java.util.Timer;

import szakdolgozat.podcast.data.podcast.Podcast;

/**
 * The listener interface for receiving podcast events. The class that is
 * interested in processing a podcast event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addPodcastListener<code> method. When the podcast event
 * occurs, that object's appropriate method is invoked.
 *
 * @see PodcastEvent
 */
public class PodcastListener {

	/** The instance. */
	private static PodcastListener instance = null;

	/** The timer subscriber. */
	private static Timer timerSubscriber;

	/** The frequency. */
	private static int frequency = 0; /// elso futás miatt most épp teszt, de a
										/// default az legyen 1 óra mondjuk

	/** The timer downloader. */
	private static Timer timerDownloader;

	/**
	 * Instantiates a new podcast listener.
	 */
	private PodcastListener() {
		timerSubscriber = new Timer();
	}

	public Timer getTimerDownloader() {
		return timerDownloader;
	}

	/**
	 * Sets the frequency.
	 *
	 * @param frequency
	 *            the new frequency
	 */
	public void setFrequency(final int frequency) {
		PodcastListener.frequency = frequency;
	}

	/**
	 * Gets the single instance of PodcastListener.
	 *
	 * @return single instance of PodcastListener
	 */
	public static PodcastListener getInstance() {
		if (instance == null) {
			instance = new PodcastListener();
		}
		return instance;
	}

	/**
	 * Gets the timer.
	 *
	 * @return the timer
	 */
	public Timer getTimer() {
		return timerSubscriber;
	}

	public int getFrequency() {
		return frequency;
	}

	/**
	 * Start listening to subscribed podcasts.
	 */
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

	/**
	 * Start download.
	 *
	 * @param podcast
	 *            the podcast
	 */
	public void startDownload(final Podcast podcast) {
		final TimerTaskDownload timerTaskDownload = new TimerTaskDownload(podcast);
		timerDownloader = new Timer();
		timerDownloader.schedule(timerTaskDownload, 0);
	}

}
