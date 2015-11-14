package szakdolgozat.podcast.controller;

import szakdolgozat.podcast.basicinformation.InformationContainer;
import szakdolgozat.podcast.threads.PodcastListener;

public class SettingsBorderPaneController {
	public SettingsBorderPaneController() {

	}

	public void setEmailRequired(final boolean bool) {
		InformationContainer.getInstance().setEmailRequired(bool);
	}

	public void setFrequency(final String frequency) {
		PodcastListener.getInstance().setFrequency(Integer.parseInt(frequency));
		PodcastListener.getInstance().getTimer().cancel();
		PodcastListener.getInstance().startListeningToSubscribedPodcasts();
	}
}
