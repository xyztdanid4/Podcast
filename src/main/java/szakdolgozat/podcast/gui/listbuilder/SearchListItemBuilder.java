package szakdolgozat.podcast.gui.listbuilder;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.PodcastContainer;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.samples.ButtonSample;
import szakdolgozat.podcast.gui.samples.HBoxSample;
import szakdolgozat.podcast.morphia.MorphiaConnector;
import szakdolgozat.podcast.xmlparser.XmlParser;

public class SearchListItemBuilder {
	private HBoxSample searchResultItem;
	private static final String SUBSCRIBED_TEXT = "Subscribe!";
	private static final String ALREADYSUBSCRIBED_TEXT = "Already subscribed!";
	private static final String SUBSCRIBED_TOOLTIP = "Click for subscribe!";
	private static final String ALREADYSUBSCRIBED_TOOLTIP = "Choose from others to subscribe!";

	public SearchListItemBuilder(PodcastContainer searchResultContainer, int i) {
		ImageView searchResultItemimageView = new ImageView();
		Image searchResultItemImage = new Image(searchResultContainer.getResults().get(i).getArtworkUrl60());
		searchResultItemimageView.setImage(searchResultItemImage);
		Text artistName = new Text(searchResultContainer.getResults().get(i).getArtistName());
		artistName.setFont(Font.font("Arial", FontWeight.BOLD, 13));
		ButtonSample subscribeButton;
		if (isPodcastSubscribed(searchResultContainer.getResults().get(i).getArtistName())) {
			subscribeButton = new ButtonSample(ALREADYSUBSCRIBED_TEXT, ALREADYSUBSCRIBED_TOOLTIP);
			subscribeButton.setDisable(true);
		} else {
			subscribeButton = new ButtonSample(SUBSCRIBED_TEXT, SUBSCRIBED_TOOLTIP);
			subscribeButton.setDisable(false);
			subscribeButton.setOnAction((ActionEvent event) -> {
				XmlParser xmlParser = new XmlParser(searchResultContainer.getResults().get(i).getFeedUrl());
				searchResultContainer.getResults().get(i)
						.setPodcastEpisode(new ArrayList<PodcastEpisode>(xmlParser.readFeed()));
				MorphiaConnector.getInstance().getDataStore().save(searchResultContainer.getResults().get(i));
			});
		}
		searchResultItem = new HBoxSample(searchResultItemimageView, artistName, subscribeButton);
	}

	public HBoxSample getSearchResultItem() {
		return searchResultItem;
	}

	private boolean isPodcastSubscribed(final String name) {
		return !(MorphiaConnector.getDataStore().createQuery(Podcast.class).filter("artistName = ", name).asList()
				.isEmpty());
	}

}
