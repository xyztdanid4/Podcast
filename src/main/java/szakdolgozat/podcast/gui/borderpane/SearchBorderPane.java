package szakdolgozat.podcast.gui.borderpane;

import java.util.ArrayList;

import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.PodcastContainer;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.decorator.SearchBPDecorator;
import szakdolgozat.podcast.gui.samples.ButtonSample;
import szakdolgozat.podcast.gui.samples.ListViewSample;
import szakdolgozat.podcast.gui.samples.TextFieldSample;
import szakdolgozat.podcast.jsonparser.JsonParser;
import szakdolgozat.podcast.morphia.MorphiaConnector;
import szakdolgozat.podcast.xmlparser.XmlParser;

public class SearchBorderPane extends BorderPane {
	private static final String SEARCHTEXTFIELD_PROMPTTEXT = "SEARCH ME!";
	private static final String SEARCHTEXTFIELD_TOOLTIP = "Type something, you would like to podcast!";
	private static final String SEARCHBUTTON_TOOLTIP = "Click me for search!";
	private static final String SEARCHBUTTON_TEXT = "Search!";
	private static final String EPISODESTEXT = "Episodes";
	private static final String PODCASTTEXT = "Podcasts";
	private static final String SUBSCRIBED_TEXT = "Subscribe!";
	private static final String ALREADYSUBSCRIBED_TEXT = "Already subscribed!";
	private static final String SUBSCRIBED_TOOLTIP = "Click for subscribe!";
	private static final String ALREADYSUBSCRIBED_TOOLTIP = "Choose from others to subscribe!";
	private ButtonSample searchButton;
	private HBox searchItemsContainerHbox;
	private TextFieldSample searchTextField;
	private PodcastContainer searchPodcastContainer;
	private ObservableList<HBox> searchResultList;
	private ListViewSample searchResultListView;
	private ObservableList<HBox> episodesList;
	private ListViewSample episodesListView;
	private VBox podcastListVBox;
	private VBox episodesListVBox;

	public SearchBorderPane() {
		searchTextField = new TextFieldSample(SEARCHTEXTFIELD_PROMPTTEXT, SEARCHTEXTFIELD_TOOLTIP);
		SearchBPDecorator.decorateTextField(searchTextField, SearchBPDecorator.SEARCHTEXTFIELDWIDTH,
				SearchBPDecorator.SEARCHTEXTFIELDHEIGHT);
		searchButton = new ButtonSample(SEARCHBUTTON_TEXT, SEARCHBUTTON_TOOLTIP);
		SearchBPDecorator.decorateButton(searchButton);
		searchItemsContainerHbox = new HBox(SearchBPDecorator.HBOXPADDING, searchTextField, searchButton);
		setTop(searchItemsContainerHbox);
		setButtonDisability();
		setSearchButtonFunctionality();
		setSearchTextFieldKeyEvent();
		setPadding();
		showPodcastList();
		showEpisodesList();
		SearchBPDecorator.decorate(this);
	}

	private void setPadding() {
		setPadding(new Insets(SearchBPDecorator.PADDING, SearchBPDecorator.PADDING, SearchBPDecorator.PADDING,
				SearchBPDecorator.PADDING));
	}

	private void setSearchTextFieldKeyEvent() {
		searchTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					searchButton.fire();
				}
			}
		});
	}

	private void setButtonDisability() {
		searchButton.disableProperty().bind(Bindings.isEmpty(searchTextField.textProperty()));
	}

	private void setSearchButtonFunctionality() {
		searchButton.setOnAction((ActionEvent event) -> {
			startSearchPodcast();
		});
	}

	private void startSearchPodcast() {
		JsonParser jsonParser = new JsonParser(new String("https://itunes.apple.com/search?term="
				+ searchTextField.getText() + "&entity=podcast&media=podcast&limit=5"));
		searchPodcastContainer = jsonParser.getSearchResult();
		showSearchResult();
	}

	private void showSearchResult() {
		searchResultList.clear();
		episodesList.clear();
		for (Podcast podcast : searchPodcastContainer.getResults()) {
			Rectangle imageRectangle = new Rectangle();
			SearchBPDecorator.decorateRectangle(imageRectangle, SearchBPDecorator.SMALLRECTANGLEHEIGHT,
					SearchBPDecorator.SMALLRECTANGLEWIDTH, podcast.getArtworkUrl60());
			Text artistName = new Text(podcast.getArtistName());
			SearchBPDecorator.decorateText(artistName, SearchBPDecorator.SMALLTEXTSIZE);
			Button subscribeButton;
			if (isPodcastSubscribed(podcast.getArtistName())) {
				subscribeButton = new ButtonSample(ALREADYSUBSCRIBED_TEXT, ALREADYSUBSCRIBED_TOOLTIP);
				SearchBPDecorator.decorateButton(subscribeButton);
				subscribeButton.setDisable(true);
			} else {
				subscribeButton = new ButtonSample(SUBSCRIBED_TEXT, SUBSCRIBED_TOOLTIP);
				SearchBPDecorator.decorateButton(subscribeButton);
				subscribeButton.setDisable(false);
				subscribeButton.setOnAction((ActionEvent event) -> {
					XmlParser xmlParser = new XmlParser(podcast.getFeedUrl());
					podcast.setPodcastEpisode(new ArrayList<PodcastEpisode>(xmlParser.readFeed()));
					MorphiaConnector.getInstance().getDataStore().save(podcast);
					subscribeButton.setDisable(true);
					subscribeButton.setText(ALREADYSUBSCRIBED_TEXT);
					subscribeButton.setTooltip(new Tooltip(ALREADYSUBSCRIBED_TOOLTIP));
				});
			}
			HBox resultItemHBox = new HBox(SearchBPDecorator.PADDING, imageRectangle, artistName, subscribeButton);
			SearchBPDecorator.decorateHBox(resultItemHBox);
			searchResultList.add(resultItemHBox);
		}
		if (!searchResultList.isEmpty()) {
			searchResultListView.setDisable(false);
			episodesListView.setDisable(false);
		} else {
			Text text = new Text(new String("No result found!"));
			SearchBPDecorator.decorateText(text, SearchBPDecorator.BIGTEXTSIZE);
			HBox itemHbox = new HBox(SearchBPDecorator.PADDING, text);
			SearchBPDecorator.decorateHBox(itemHbox);
			searchResultList.add(itemHbox);
			searchResultListView.setDisable(true);
			episodesListView.setDisable(true);
		}
		searchResultListView.setItems(searchResultList);
	}

	private void setListViewInvalidationListener() {
		searchResultListView.getSelectionModel().selectedIndexProperty().addListener((Observable o) -> {
			if (!searchResultListView.getSelectionModel().isEmpty()) {
				episodesList.clear();
				XmlParser xmlParser = new XmlParser(searchPodcastContainer.getResults()
						.get(searchResultListView.getSelectionModel().getSelectedIndex()).getFeedUrl());
				searchPodcastContainer.getResults().get(searchResultListView.getSelectionModel().getSelectedIndex())
						.setPodcastEpisode(new ArrayList<PodcastEpisode>(xmlParser.readFeed()));
				for (int i = 0; i < searchPodcastContainer.getResults()
						.get(searchResultListView.getSelectionModel().getSelectedIndex()).getPodcastEpisode()
						.size(); i++) {
					ImageView imageView = new ImageView();
					Image image = null;
					try {
						image = new Image(searchPodcastContainer.getResults()
								.get(searchResultListView.getSelectionModel().getSelectedIndex()).getPodcastEpisode()
								.get(i).getImage());
					} catch (Exception e) {
						e.printStackTrace();
					}
					imageView.setImage(image);
					imageView.setFitHeight(30);
					imageView.setFitWidth(30);
					HBox hBoxSample = new HBox(imageView,
							new Text(searchPodcastContainer.getResults()
									.get(searchResultListView.getSelectionModel().getSelectedIndex())
									.getPodcastEpisode().get(i).getTitle()));
					episodesList.add(hBoxSample);
				}
				episodesListView.setItems(episodesList);
			}
		});
	}

	private void showEpisodesList() {
		episodesListVBox = new VBox(SearchBPDecorator.PADDING);
		Text episodeText = new Text(EPISODESTEXT);
		SearchBPDecorator.decorateText(episodeText, SearchBPDecorator.BIGTEXTSIZE);
		episodesList = FXCollections.observableArrayList();
		episodesListView = new ListViewSample();
		SearchBPDecorator.decorateListView(episodesListView, SearchBPDecorator.EPISODESLISTWIDTH,
				SearchBPDecorator.EPISODESLISTHEIGHT);
		setMargin(episodesListVBox, new Insets(SearchBPDecorator.PADDING));
		episodesListView.setItems(episodesList);
		episodesListVBox.getChildren().addAll(episodeText, episodesListView);
		// setAlignment(episodesListVBox, Pos.CENTER_LEFT);
		setCenter(episodesListVBox);
	}

	private void showPodcastList() {
		podcastListVBox = new VBox(SearchBPDecorator.PADDING);
		Text podcastText = new Text(PODCASTTEXT);
		SearchBPDecorator.decorateText(podcastText, SearchBPDecorator.BIGTEXTSIZE);
		searchResultList = FXCollections.observableArrayList();
		searchResultListView = new ListViewSample();
		SearchBPDecorator.decorateListView(searchResultListView, SearchBPDecorator.PODCASTLISTWIDTH,
				SearchBPDecorator.PODCASTLISTHEIGHT);
		setMargin(podcastListVBox, new Insets(SearchBPDecorator.PADDING));
		searchResultListView.setItems(searchResultList);
		podcastListVBox.getChildren().addAll(podcastText, searchResultListView);
		// setAlignment(podcastListVBox, Pos.CENTER_LEFT);
		setListViewInvalidationListener();
		setLeft(podcastListVBox);
	}

	private boolean isPodcastSubscribed(final String name) {
		return !(MorphiaConnector.getDataStore().createQuery(Podcast.class).filter("artistName = ", name).asList()
				.isEmpty());
	}
}
