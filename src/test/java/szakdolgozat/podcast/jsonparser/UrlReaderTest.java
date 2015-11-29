package szakdolgozat.podcast.jsonparser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 *
 */
public class UrlReaderTest {
	UrlReader urlReader;

	@Before
	public void create() {
		this.urlReader = new UrlReader();
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

	@After
	public void destroy() {

	}
}
