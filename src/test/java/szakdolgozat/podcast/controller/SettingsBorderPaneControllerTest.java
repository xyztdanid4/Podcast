package szakdolgozat.podcast.controller;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import szakdolgozat.podcast.data.basicinformation.InformationContainer;
import szakdolgozat.podcast.threads.PodcastListener;

/**
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 *
 */
public class SettingsBorderPaneControllerTest {
	SettingsBorderPaneController settingsBorderPaneController;

	@Before
	public void create() {
		this.settingsBorderPaneController = new SettingsBorderPaneController();
	}

	/**
	 * test for setEmailRequired method
	 * 
	 * @see {@link InformationContainer}
	 * @see {@link InformationContainer#getInstance()}
	 * @see {@link InformationContainer#isEmailRequired()}
	 */
	@Test
	public void setEmailRequiredTest() {
		final boolean b = InformationContainer.getInstance().isEmailRequired();
		this.settingsBorderPaneController.setEmailRequired(!b);
		assertEquals(b, b);
	}

	/**
	 * test for setting the frequency
	 * 
	 * @see {@link PodcastListener}
	 * @see {@link PodcastListener#getInstance()}
	 * @see {@link PodcastListener#getFrequency()}
	 * @see {@link SettingsBorderPaneController}
	 * @see {@link SettingsBorderPaneController#setFrequency(String)}
	 * 
	 */
	@Test
	public void setFrequencyTest() {
		final int frequency = PodcastListener.getInstance().getFrequency();
		this.settingsBorderPaneController.setFrequency("100");
		assertEquals(frequency != PodcastListener.getInstance().getFrequency(), true);
	}

	@After
	public void destroy() {

	}
}
