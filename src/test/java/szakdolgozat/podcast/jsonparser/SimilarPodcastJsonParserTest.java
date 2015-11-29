package szakdolgozat.podcast.jsonparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import szakdolgozat.podcast.data.podcast.PodcastContainer;

/**
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 *
 */
public class SimilarPodcastJsonParserTest {
	UrlReader urlReader;
	PodcastContainer searchResultContainer;

	@Before
	public void create() {
		this.urlReader = new UrlReader();
		this.searchResultContainer = new PodcastContainer();
	}

	@Test
	public void jsonToObjectTest() throws Exception {
		final String result = this.urlReader.readUrl("https://itunes.apple.com/search?term=test");
		final Gson gson = new Gson();
		this.searchResultContainer = gson.fromJson(result, PodcastContainer.class);
		assertNotNull(gson);
		assertNotNull(result);
		assertNotNull(this.searchResultContainer);
		assertEquals(this.searchResultContainer.getResults().isEmpty(), false);
	}

	@After
	public void destroy() {

	}
}
