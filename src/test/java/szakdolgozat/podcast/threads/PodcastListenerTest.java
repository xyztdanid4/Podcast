package szakdolgozat.podcast.threads;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import szakdolgozat.podcast.data.podcast.Podcast;

/**
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 *
 */
public class PodcastListenerTest {

	@Before
	public void create() {
	}

	@Test
	public void instanceTest() {
		assertNotNull(PodcastListener.getInstance());
	}

	@Test
	public void startListeningToSubscribedPodcastsTest() {
		PodcastListener.getInstance().startListeningToSubscribedPodcasts();
		assertNotNull(PodcastListener.getInstance().getTimer());
	}

	@Test
	public void startDownloadTest() {
		final Podcast podcast = new Podcast();
		PodcastListener.getInstance().startDownload(podcast);
		assertNotNull(PodcastListener.getInstance().getTimerDownloader());
	}

	@After
	public void destroy() {

	}
}
