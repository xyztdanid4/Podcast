package szakdolgozat.podcast.jsonparser;

import com.google.gson.Gson;

import szakdolgozat.podcast.data.podcast.SimilarPodcastContainer;

public class SimilarPodcastJsonParser extends JsonParser {
	private SimilarPodcastContainer similarPodcastContainer;

	public SimilarPodcastJsonParser(final String searchText) {
		super(searchText);
		jsonToObject();
	}

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

	public SimilarPodcastContainer getSearchResult() {
		return this.similarPodcastContainer;
	}
}
