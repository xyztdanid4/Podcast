package szakdolgozat.podcast.jsonparser;

import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import szakdolgozat.podcast.data.podcast.SimilarPodcastContainer;

public class SimilarPodcastJsonParser extends JsonParser {
	private SimilarPodcastContainer similarPodcastContainer;

	public SimilarPodcastJsonParser(String searchText) {
		super(searchText);
		jsonToObject();
	}

	@Override
	protected void jsonToObject() {
		Gson gson = new Gson();
		similarPodcastContainer = gson.fromJson(result, SimilarPodcastContainer.class);
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

	public SimilarPodcastContainer getSearchResult() {
		return similarPodcastContainer;
	}
}
