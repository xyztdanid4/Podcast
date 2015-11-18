package szakdolgozat.podcast.jsonparser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * The Class UrlReader.
 * 
 * @author Daniel Toths
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public class UrlReader {

	/**
	 * Instantiates a new url reader.
	 */
	public UrlReader() {

	}

	/**
	 * Read url.
	 *
	 * @param urlString
	 *            the url string
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String readUrl(final String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			final URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			final StringBuffer buffer = new StringBuffer();
			int read;
			final char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1) {
				buffer.append(chars, 0, read);
			}

			return buffer.toString();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}
}
