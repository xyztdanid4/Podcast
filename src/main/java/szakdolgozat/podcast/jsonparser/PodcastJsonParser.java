package szakdolgozat.podcast.jsonparser;

import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import szakdolgozat.podcast.data.podcast.PodcastContainer;

public class PodcastJsonParser extends JsonParser {
	private PodcastContainer searchResultContainer;

	public PodcastJsonParser(final String searchText) {
		super(searchText);
		jsonToObject();
		removeNotWorkingFeeds();
	}

	@Override
	protected void jsonToObject() {
		final Gson gson = new Gson();
		this.searchResultContainer = gson.fromJson(this.result, PodcastContainer.class);
	}

	protected void removeNotWorkingFeeds() {
		for (int i = 0; i < this.searchResultContainer.getResults().size(); i++) {
			final String feedUrl = this.searchResultContainer.getResults().get(i).getFeedUrl();
			if (!isFeedUrlExists(feedUrl)) {
				this.searchResultContainer.getResults().remove(i);
			}
		}
	}

	private boolean isFeedUrlExists(final String URLName) {
		try {
			HttpURLConnection.setFollowRedirects(false);
			final HttpURLConnection connection = (HttpURLConnection) new URL(URLName).openConnection();
			connection.setRequestMethod("HEAD");
			return (connection.getResponseCode() == HttpURLConnection.HTTP_OK);
		} catch (final Exception e) {
			e.printStackTrace();
			// System.out.println("catch");
			return false;
		}
	}

	public PodcastContainer getSearchResult() {
		return this.searchResultContainer;
	}

}