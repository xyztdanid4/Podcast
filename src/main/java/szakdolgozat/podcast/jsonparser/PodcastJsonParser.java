package szakdolgozat.podcast.jsonparser;

import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import szakdolgozat.podcast.data.podcast.PodcastContainer;

/**
 * The Class PodcastJsonParser.
 * 
 * * @author Daniel Toth
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public class PodcastJsonParser extends JsonParser {

	/** The search result container. */
	private PodcastContainer searchResultContainer;

	/**
	 * Instantiates a new podcast json parser.
	 *
	 * @param searchText
	 *            the search text
	 */
	public PodcastJsonParser(final String searchText) {
		super(searchText);
		jsonToObject();
		removeNotWorkingFeeds();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see szakdolgozat.podcast.jsonparser.JsonParser#jsonToObject()
	 */
	@Override
	protected void jsonToObject() {
		final Gson gson = new Gson();
		this.searchResultContainer = gson.fromJson(this.result, PodcastContainer.class);
	}

	/**
	 * Removes the not working feeds.
	 */
	protected void removeNotWorkingFeeds() {
		for (int i = 0; i < this.searchResultContainer.getResults().size(); i++) {
			final String feedUrl = this.searchResultContainer.getResults().get(i).getFeedUrl();
			if (!isFeedUrlExists(feedUrl)) {
				this.searchResultContainer.getResults().remove(i);
			}
		}
	}

	/**
	 * Checks if is feed url exists.
	 *
	 * @param URLName
	 *            the URL name
	 * @return true, if is feed url exists
	 */
	private boolean isFeedUrlExists(final String URLName) {
		try {
			HttpURLConnection.setFollowRedirects(false);
			final HttpURLConnection connection = (HttpURLConnection) new URL(URLName).openConnection();
			connection.setRequestMethod("HEAD");
			return (connection.getResponseCode() == HttpURLConnection.HTTP_OK);
		} catch (final Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Gets the search result.
	 *
	 * @return the search result
	 */
	public PodcastContainer getSearchResult() {
		return this.searchResultContainer;
	}

}