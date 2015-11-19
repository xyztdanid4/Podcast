package szakdolgozat.podcast.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mongodb.morphia.query.Query;

import de.saxsys.javafx.test.JfxRunner;
import de.saxsys.javafx.test.TestInJfxThread;
import szakdolgozat.podcast.data.basicinformation.InformationContainer;
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.gui.builder.HBoxBuilder;
import szakdolgozat.podcast.morphia.MorphiaConnector;
import szakdolgozat.podcast.morphia.MorphiaLoginConnector;
import szakdolgozat.podcast.user.User;

/**
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 *
 */

@RunWith(JfxRunner.class)
public class PodcastBorderPaneControllerTest {
	Podcast podcast;

	@Before
	public void create() {
		this.podcast = new Podcast();
		this.podcast.setArtistName("test");
	}

	/**
	 * test for reading from DB
	 * 
	 * @see {@link PodcastBoderPaneController}
	 * @see {@link PodcastBoderPaneController#readfromDB()}
	 * @see {@link MorphiaLoginConnector}
	 * @see {@link MorphiaLoginConnector#getInstance()}
	 * @see {@link MorphiaLoginConnector#save(User)}
	 * @see {@link User}
	 * @see {@link InformationContainer}
	 * @see {@link InformationContainer#getInstance()}
	 * @see {@link InformationContainer#setOwner(String)}
	 * @see {@link PlayListController}
	 * @see {@link PlayListController#getInstance()}
	 * @see {@link PlayListController#readfromDB()}
	 */
	@Test
	public void readfromDBTest() {
		MorphiaLoginConnector.getInstance().save(new User("test", "test", "test@test.test"));
		InformationContainer.getInstance().setOwner("test");
		assertNotNull(PlayListController.getInstance().readfromDB());
	}

	/**
	 * test for removing from DB
	 * 
	 * @see {@link MorphiaLoginConnector}
	 * @see {@link MorphiaLoginConnector#getInstance()}
	 * @see {@link MorphiaLoginConnector#save(User)}
	 * @see {@link InformationContainer}
	 * @see {@link InformationContainer#getInstance()}
	 * @see {@link InformationContainer#setOwner(String)}
	 * @see {@link Podcast}
	 * @see {@link MorphiaConnector}
	 * @see {@link MorphiaConnector#getInstance()}
	 * @see {@link MorphiaConnector#getDataStore()}
	 */
	@Test
	public void removefromDBTest() {
		MorphiaLoginConnector.getInstance().save(new User("test", "test", "test@test.test"));
		InformationContainer.getInstance().setOwner("test");
		MorphiaConnector.getInstance().getDataStore().save(this.podcast);
		final Query<Podcast> deletePodcast = MorphiaConnector.getInstance().getDataStore().createQuery(Podcast.class)
				.filter("artistName =", "test");
		assertEquals(MorphiaConnector.getInstance().getDataStore().createQuery(Podcast.class).asList().isEmpty(),
				false);
		MorphiaConnector.getInstance().getDataStore().delete(deletePodcast);
		assertEquals(MorphiaConnector.getInstance().getDataStore().createQuery(Podcast.class).asList().isEmpty(), true);
	}

	/**
	 * test the notifier method
	 * 
	 * @see {@link NotificationBorderPaneController}
	 * @see {@link NotificationBorderPaneController#getInstance()}
	 * @see {@link NotificationBorderPaneController#getNotificationContainer()}
	 * @see {@link NotificationBorderPaneController#getUnsubscribeContainer()
	 */
	@TestInJfxThread
	@Test
	public void noticeTest() {
		final int oldNotificationContainerCount = NotificationBorderPaneController.getInstance()
				.getNotificationContainer().size();
		final int oldUnsubscribeContainer = NotificationBorderPaneController.getInstance().getUnsubscribeContainer()
				.size();
		NotificationBorderPaneController.getInstance().getNotificationContainer()
				.add(HBoxBuilder.create().smallText("UNSUBSCRIBED").artist(this.podcast.getArtistName()).build());
		NotificationBorderPaneController.getInstance().getUnsubscribeContainer()
				.add(HBoxBuilder.create().smallText("UNSUBSCRIBED").artist(this.podcast.getArtistName()).build());
		final int newNotificationContainerCount = NotificationBorderPaneController.getInstance()
				.getNotificationContainer().size();
		final int newUnsubscribeContainer = NotificationBorderPaneController.getInstance().getUnsubscribeContainer()
				.size();
		assertEquals(oldUnsubscribeContainer < newUnsubscribeContainer, true);
		assertEquals(oldNotificationContainerCount < newNotificationContainerCount, true);
	}

	/**
	 * test for starting the media player
	 */
	@Test
	public void startNewMediaPlayerTest() {
		// to be tested in mediapodcast control class
	}

	/**
	 * cleaning up
	 */
	@After
	public void destroy() {
		final Query<User> deleteUser1 = MorphiaLoginConnector.getInstance().getDataStore().createQuery(User.class)
				.filter("name", "test");
		MorphiaLoginConnector.getInstance().getDataStore().delete(deleteUser1);
	}
}
