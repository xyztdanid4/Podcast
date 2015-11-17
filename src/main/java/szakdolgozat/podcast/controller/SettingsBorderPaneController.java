package szakdolgozat.podcast.controller;

import szakdolgozat.podcast.basicinformation.InformationContainer;
import szakdolgozat.podcast.threads.PodcastListener;

/**
 * The SettingsBorderPaneController class
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 * 
 *
 */
public class SettingsBorderPaneController {
	public SettingsBorderPaneController() {

	}

	/**
	 * Set if the information containers email variable by the parameter.
	 * 
	 * @param bool
	 */
	public void setEmailRequired(final boolean bool) {
		InformationContainer.getInstance().setEmailRequired(bool);
	}

	/**
	 * Update and restart the listening thread by the parameter.
	 * 
	 * @param frequency
	 */
	public void setFrequency(final String frequency) {
		PodcastListener.getInstance().setFrequency(Integer.parseInt(frequency));
		PodcastListener.getInstance().getTimer().cancel();
		PodcastListener.getInstance().startListeningToSubscribedPodcasts();
	}
}
