package szakdolgozat.podcast.gui.listbuilder;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.data.podcast.SearchResultContainer;
import szakdolgozat.podcast.gui.samples.ButtonSample;
import szakdolgozat.podcast.gui.samples.HBoxSample;
import szakdolgozat.podcast.morphia.MorphiaConnector;
import szakdolgozat.podcast.xmlparser.XmlParser;

public class SearchListItemBuilder {
	private HBoxSample searchResultItem;

	public SearchListItemBuilder(SearchResultContainer searchResultContainer,
			int i) {
		ImageView searchResultItemimageView = new ImageView();
		Image searchResultItemImage = new Image(searchResultContainer
				.getResults().get(i).getArtworkUrl60());
		searchResultItemimageView.setImage(searchResultItemImage);
		Text artistName = new Text(searchResultContainer.getResults().get(i)
				.getArtistName());
		artistName.setFont(Font.font("Arial", FontWeight.BOLD, 13));
		// Text trackName = new Text(searchResultContainer.getResults().get(i)
		// .getTrackName());
		// trackName.setFont(Font.font("Arial", FontPosture.ITALIC, 13));
		// searchResultItem = new HBoxSample(searchResultItemimageView,
		// artistName, trackName);
		ButtonSample subscribeButton = new ButtonSample("Subscribe!",
				"Click for subscribe");

		subscribeButton
				.setOnAction((ActionEvent event) -> {
					XmlParser xmlParser = new XmlParser(searchResultContainer
							.getResults().get(i).getFeedUrl());
					searchResultContainer
							.getResults()
							.get(i)
							.setPodcastEpisode(
									new ArrayList<PodcastEpisode>(xmlParser
											.readFeed()));
					MorphiaConnector.getInstance().getDataStore()
							.save(searchResultContainer.getResults().get(i));
					subscribeButton.setText("Unsubscribe!");
				});

		searchResultItem = new HBoxSample(searchResultItemimageView,
				artistName, subscribeButton);
	}

	public HBoxSample getSearchResultItem() {
		return searchResultItem;
	}

}
