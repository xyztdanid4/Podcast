package szakdolgozat.podcast.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import szakdolgozat.podcast.threads.PodcastListener;

/**
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 *
 */
public class MainStageControllerTest {
	MainStageController mainStageController;

	@Before
	public void create() {
		this.mainStageController = MainStageController.getInstance();
	}

	/**
	 * test for the instance
	 * 
	 * @see {@link MainStageController}
	 * @see {@link MainStageController#getInstance()}
	 */
	@Test
	public void instanceTest() {
		assertNotNull(this.mainStageController);
	}

	/**
	 * test for startListeningThrea, we test it with the timer instance, if it
	 * was started it can not be null.
	 * 
	 * @see {@link PodcastListener}
	 * @see {@link PodcastListener#getInstance()}
	 * @see {@link PodcastListener#getTimer()}
	 * 
	 */
	@Test
	public void startListeningThreadTest() {
		assertNotNull(PodcastListener.getInstance().getTimer());
	}

	@After
	public void destroy() {
		PodcastListener.getInstance().getTimer().cancel();
	}

}
