package szakdolgozat.podcast.jsonparser;

import com.google.gson.Gson;

import szakdolgozat.podcast.data.podcast.SimilarPodcastContainer;

/**
 * The Class SimilarPodcastJsonParser.
 * 
 * * @author Daniel Toth
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public class SimilarPodcastJsonParser extends JsonParser {

	/** The similar podcast container. */
	private SimilarPodcastContainer similarPodcastContainer;

	/**
	 * Instantiates a new similar podcast json parser.
	 *
	 * @param searchText
	 *            the search text
	 */
	public SimilarPodcastJsonParser(final String searchText) {
		super(searchText);
		jsonToObject();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see szakdolgozat.podcast.jsonparser.JsonParser#jsonToObject()
	 */
	@Override
	protected void jsonToObject() {
		final Gson gson = new Gson();
		this.similarPodcastContainer = gson.fromJson(this.result, SimilarPodcastContainer.class);
	}
	/*
	 * private boolean isFeedUrlExists(String URLName) { try {
	 * HttpURLConnection.setFollowRedirects(false); HttpURLConnection connection
	 * = (HttpURLConnection) new URL(URLName).openConnection();
	 * connection.setRequestMethod("HEAD"); return (connection.getResponseCode()
	 * == HttpURLConnection.HTTP_OK); } catch (Exception e) {
	 * e.printStackTrace(); return false; } }
	 */

	/**
	 * Gets the search result.
	 *
	 * @return the search result
	 */
	public SimilarPodcastContainer getSearchResult() {
		return this.similarPodcastContainer;
	}
}
