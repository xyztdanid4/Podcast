package szakdolgozat.podcast.controller;

import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.stage.MainStage;
import szakdolgozat.podcast.threads.PodcastListener;

/**
 * The MainStageController class
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 * 
 *        This class is represent the actions which is related with the
 *        {@link MainStage}. Maybe it has only one important function, is that
 *        {@link MainStageController#startListeningThread()} should start the
 *        listening thread which is related with observing fresh
 *        {@link Podcast#getPodcastEpisode()}.
 * 
 * @see {@link Podcast}
 * @ses {@link PodcastEpisode}
 * @see {@link MainStage}}
 *
 */
public class MainStageController {
	/**
	 * Variable for representing the instance of MainStageController. It is very
	 * important because we reach everything of the class through this.
	 */
	private static MainStageController instance = null;

	/**
	 * Instantiates a MainStageController. And start the listening thread.
	 */
	private MainStageController() {
		startListeningThread();
	}

	/**
	 * This method is responsible for start the thread which will observe the
	 * fresh episodes.
	 * 
	 * @see PodcastListener
	 * @see PodcastListener#startListeningToSubscribedPodcasts()
	 */
	private void startListeningThread() {
		PodcastListener.getInstance().startListeningToSubscribedPodcasts();
	}

	/**
	 * This method is responsible for creating the MainStageController object.
	 * The MainStageController object is singleton class, cause we need only one
	 * instance.
	 *
	 * @return the MainStageController object.
	 */
	public static MainStageController create() {
		if (instance == null) {
			instance = new MainStageController();
		}
		return instance;
	}
}
