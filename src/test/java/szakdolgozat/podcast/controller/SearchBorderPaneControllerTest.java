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
import szakdolgozat.podcast.gui.mediaplayer.MediaControlPodcast;
import szakdolgozat.podcast.morphia.MorphiaConnector;
import szakdolgozat.podcast.morphia.MorphiaLoginConnector;
import szakdolgozat.podcast.threads.PodcastListener;
import szakdolgozat.podcast.user.User;

/**
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 *
 */

@RunWith(JfxRunner.class)
public class SearchBorderPaneControllerTest {
	Podcast podcast;

	@Before
	public void create() {
		this.podcast = new Podcast();
		this.podcast.setArtistName("test");
	}

	/**
	 * test for subscribe, basically the same as ispodcastsubsribed method. *
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
	public void subscribeTest() {
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
	 * @see {@link NotificationBorderPaneController}
	 * @see {@link NotificationBorderPaneController#getInstance()}
	 * @see {@link NotificationBorderPaneController#getNotificationContainer()}
	 * @see {@link NotificationBorderPaneController#getSubscribeContainer()()
	 * 
	 */
	@TestInJfxThread
	@Test
	public void noticeTest() {
		final int oldNotificationContainerCount = NotificationBorderPaneController.getInstance()
				.getNotificationContainer().size();
		final int oldSubscribeContainer = NotificationBorderPaneController.getInstance().getSubscribeContainer().size();
		NotificationBorderPaneController.getInstance().getNotificationContainer()
				.add(HBoxBuilder.create().smallText("SUBSCRIBED").artist(this.podcast.getArtistName()).build());
		NotificationBorderPaneController.getInstance().getSubscribeContainer()
				.add(HBoxBuilder.create().smallText("SUBSCRIBED").artist(this.podcast.getArtistName()).build());
		final int newNotificationContainerCount = NotificationBorderPaneController.getInstance()
				.getNotificationContainer().size();
		final int newSubscribeContainer = NotificationBorderPaneController.getInstance().getSubscribeContainer().size();
		assertEquals(oldSubscribeContainer < newSubscribeContainer, true);
		assertEquals(oldNotificationContainerCount < newNotificationContainerCount, true);
	}

	/**
	 * test for starting the media player
	 */
	@Test
	public void startNewMediaPlayerTest() {
		assertNotNull(MediaControlPodcast.getInstance());
	}

	@Test
	public void startDownloadTest() {
		assertNotNull(PodcastListener.getInstance());
	}

	/**
	 * test for ispodcastsubscribed method
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
	public void isPodcastSubscribedTest() {
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

	@After
	public void destroy() {
		final Query<User> deleteUser1 = MorphiaLoginConnector.getInstance().getDataStore().createQuery(User.class)
				.filter("name", "test");
		MorphiaLoginConnector.getInstance().getDataStore().delete(deleteUser1);
	}
}
