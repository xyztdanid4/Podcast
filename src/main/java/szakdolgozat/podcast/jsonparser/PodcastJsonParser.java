package szakdolgozat.podcast.jsonparser;

import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import szakdolgozat.podcast.data.podcast.PodcastContainer;

public class PodcastJsonParser extends JsonParser {
	private PodcastContainer searchResultContainer;

	public PodcastJsonParser(String searchText) {
		super(searchText);
		jsonToObject();
		removeNotWorkingFeeds();
	}

	@Override
	protected void jsonToObject() {
		Gson gson = new Gson();
		searchResultContainer = gson.fromJson(result, PodcastContainer.class);
	}

	protected void removeNotWorkingFeeds() {
		for (int i = 0; i < searchResultContainer.getResults().size(); i++) {
			String feedUrl = searchResultContainer.getResults().get(i).getFeedUrl();
			if (!isFeedUrlExists(feedUrl)) {
				searchResultContainer.getResults().remove(i);
			}
		}
	}

	private boolean isFeedUrlExists(String URLName) {
		try {
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection connection = (HttpURLConnection) new URL(URLName).openConnection();
			connection.setRequestMethod("HEAD");
			return (connection.getResponseCode() == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public PodcastContainer getSearchResult() {
		return searchResultContainer;
	}

}