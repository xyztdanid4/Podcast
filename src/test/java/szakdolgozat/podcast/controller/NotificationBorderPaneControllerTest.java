package szakdolgozat.podcast.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 *
 */
public class NotificationBorderPaneControllerTest {

	@Before
	public void create() {

	}

	/**
	 * test for the instance
	 * 
	 * @see {@link NotificationBorderPaneController}
	 * @see {@link NotificationBorderPaneController#getInstance()}
	 */
	@Test
	public void instanceTest0() {
		assertNotNull(NotificationBorderPaneController.getInstance());
	}

	/**
	 * test for the instance of NewEpisodeContainer
	 * 
	 * @see {@link NotificationBorderPaneController}
	 * @see {@link NotificationBorderPaneController#getInstance()}
	 * @see {@link NotificationBorderPaneController#getNewEpisodeContainer()}
	 */
	@Test
	public void instanceTest1() {
		assertNotNull(NotificationBorderPaneController.getInstance().getNewEpisodeContainer());
	}

	/**
	 * test for the instance of NotificationContainer
	 * 
	 * @see {@link NotificationBorderPaneController}
	 * @see {@link NotificationBorderPaneController#getInstance()}
	 * @see {@link NotificationBorderPaneController#getNotificationContainer()}
	 */
	@Test
	public void instanceTest2() {
		assertNotNull(NotificationBorderPaneController.getInstance().getNotificationContainer());
	}

	/**
	 * test for the instance of SubscribeContainer
	 * 
	 * @see {@link NotificationBorderPaneController}
	 * @see {@link NotificationBorderPaneController#getInstance()}
	 * @see {@link NotificationBorderPaneController#getSubscribeContainer()}
	 */
	@Test
	public void instanceTest3() {
		assertNotNull(NotificationBorderPaneController.getInstance().getSubscribeContainer());
	}

	/**
	 * test for the instance UnsubscribeContainer
	 * 
	 * @see {@link NotificationBorderPaneController}
	 * @see {@link NotificationBorderPaneController#getInstance()}
	 * @see {@link NotificationBorderPaneController#getUnsubscribeContainer()}
	 */
	@Test
	public void instanceTest4() {
		assertNotNull(NotificationBorderPaneController.getInstance().getUnsubscribeContainer());
	}

	@After
	public void destroy() {

	}
}
