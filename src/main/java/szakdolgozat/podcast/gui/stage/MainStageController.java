package szakdolgozat.podcast.gui.stage;

import szakdolgozat.podcast.threads.PodcastListener;

public class MainStageController {
	private static MainStageController instance = null;

	private MainStageController() {
		PodcastListener.getInstance().startListeningToSubscribedPodcasts();
	}

	public static MainStageController getInstance() {
		if (instance == null) {
			instance = new MainStageController();
		}
		return instance;
	}

	public static MainStageController create() {
		if (instance == null) {
			instance = new MainStageController();
		}
		return instance;
	}
}
