package szakdolgozat.podcast.jsonparser;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import szakdolgozat.podcast.data.podcast.SearchResultContainer;
import szakdolgozat.podcast.xmlparser.XmlParser;

import com.google.gson.Gson;

public class JsonParser {

	private SearchResultContainer searchResultContainer;
	private String result;

	public JsonParser(String searchText) {
		UrlReader urlReader = new UrlReader();
		searchText = searchText.replace(" ", "+");
		// String s =
		// "https://itunes.apple.com/search?term=podcast&genreId=1310&limit=200";
		// System.out.println(concatedSearchText);
		result = "";
		try {
			result = urlReader.readUrl(searchText);
			// result = urlReader.readUrl(s);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// System.out.println(result);

		jsonToObject();
		removeNotWorkingFeeds();
		// xmlToObject();

		// System.out.println(searchResultContainer);
	}

	private void removeNotWorkingFeeds() {
		for (int i = 0; i < searchResultContainer.getResults().size(); i++) {
			String feedUrl = searchResultContainer.getResults().get(i)
					.getFeedUrl();
			if (!isFeedUrlExists(feedUrl)) {
				searchResultContainer.getResults().remove(i);
			}
		}
	}

	public SearchResultContainer getSearchResultContainer() {
		return searchResultContainer;
	}

	private void jsonToObject() {
		Gson gson = new Gson();
		searchResultContainer = gson.fromJson(result,
				SearchResultContainer.class);
	}

	public void xmlToObject() {
		for (int i = 0; i < searchResultContainer.getResults().size(); i++) {
			XmlParser xmlParser = new XmlParser(searchResultContainer
					.getResults().get(i).getFeedUrl());
			try {
				searchResultContainer.getResults().get(i)
						.setPodcastEpisode(xmlParser.readFeed());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private boolean isFeedUrlExists(String URLName) {
		try {
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection connection = (HttpURLConnection) new URL(URLName)
					.openConnection();
			connection.setRequestMethod("HEAD");
			return (connection.getResponseCode() == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private Boolean testImage(String url) {
		try {
			BufferedImage image = ImageIO.read(new URL(url));
			if (image != null) {
				return true;
			} else {
				return false;
			}
		} catch (MalformedURLException e) {
			System.err.println("URL error with image");
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			System.err.println("IO error with image");
			e.printStackTrace();
			return false;
		}
	}

	private void removeNotWorkingImages() {
		for (int i = 0; i < searchResultContainer.getResults().size(); i++) {

		}
	}

}
