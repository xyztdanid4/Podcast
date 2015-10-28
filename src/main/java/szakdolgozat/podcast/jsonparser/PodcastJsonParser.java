package szakdolgozat.podcast.jsonparser;

import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import szakdolgozat.podcast.data.podcast.PodcastContainer;
import szakdolgozat.podcast.gui.dialog.OtherErrorDialog;

public class PodcastJsonParser extends JsonParser {

	private PodcastContainer searchResultContainer;
	private String result;

	public PodcastJsonParser(String searchText) {
		UrlReader urlReader = new UrlReader();
		searchText = searchText.replace(" ", "+");
		result = "";
		try {
			result = urlReader.readUrl(searchText);
		} catch (Exception e) {
			e.printStackTrace();
			OtherErrorDialog errorDialog = new OtherErrorDialog(new String("Error: cannot read Url for json parsing!"));
		}
		jsonToObject();
		removeNotWorkingFeeds();
	}

	private void removeNotWorkingFeeds() {
		for (int i = 0; i < searchResultContainer.getResults().size(); i++) {
			String feedUrl = searchResultContainer.getResults().get(i).getFeedUrl();
			if (!isFeedUrlExists(feedUrl)) {
				searchResultContainer.getResults().remove(i);
			}
		}
	}

	public PodcastContainer getSearchResult() {
		return searchResultContainer;
	}

	private void jsonToObject() {
		Gson gson = new Gson();
		searchResultContainer = gson.fromJson(result, PodcastContainer.class);
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
}