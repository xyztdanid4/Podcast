package szakdolgozat.podcast.jsonparser;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 *
 */
public class JsonParserTest {
	UrlReader urlReader;

	@Before
	public void create() {
		this.urlReader = new UrlReader();
	}

	@Test
	public void instanceTest() {
		assertNotNull(this.urlReader);
	}

	/**
	 * test, as result we have to get exception
	 * 
	 * @see {@link UrlReader}
	 * @see {@link UrlReader#readUrl(String)}
	 * @throws Exception
	 */
	@Test(expected = java.net.UnknownHostException.class)
	public void exceptionTest() throws Exception {
		this.urlReader.readUrl("http://test");
	}

	/**
	 * do not show exception
	 * 
	 * @see {@link UrlReader}
	 * @see {@link UrlReader#readUrl(String)}
	 * @throws Exception
	 */
	@Test
	public void validTest() throws Exception {
		this.urlReader.readUrl("https://itunes.apple.com/search?term=test");
	}

	@After
	public void destroy() {

	}
}
