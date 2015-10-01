package szakdolgozat.podcast.gui.borderpane;

import java.util.ArrayList;

import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.data.podcast.SearchResultContainer;
import szakdolgozat.podcast.gui.listbuilder.SearchListBuilder;
import szakdolgozat.podcast.gui.samples.BorderPaneSample;
import szakdolgozat.podcast.gui.samples.ButtonSample;
import szakdolgozat.podcast.gui.samples.HBoxSample;
import szakdolgozat.podcast.gui.samples.ListViewSample;
import szakdolgozat.podcast.gui.samples.TextFieldSample;
import szakdolgozat.podcast.jsonparser.JsonParser;
import szakdolgozat.podcast.xmlparser.XmlParser;

public class SearchBorderPane extends BorderPaneSample {
	private TextFieldSample searchTextFieldSample;
	private final String SEARCHTEXTFIELD_PROMPTTEXT = "SEARCH ME!";
	private final String SEARCHTEXTFIELD_TOOLTIP = "Type something, you would like to podcast!";
	private final ButtonSample searchButtonSample;
	private final String SEARCHBUTTON_TOOLTIP = "Click me for search!";
	private final String SEARCHBUTTON_TEXT = "Search!";
	private final HBoxSample searchItemsContainerHbox;
	private final int SEARCHTEXTFIELD_WIDTH = 500;
	private SearchResultContainer searchResultContainer;
	private ObservableList<HBoxSample> searchResultList;
	private ListViewSample searchResultListView;
	private ObservableList<HBoxSample> episodesList;
	private ListViewSample episodesListView;
	private VBox podcastListVBox;
	private VBox episodesListVBox;

	public SearchBorderPane() {
		searchTextFieldSample = new TextFieldSample(SEARCHTEXTFIELD_PROMPTTEXT, SEARCHTEXTFIELD_TOOLTIP);
		searchTextFieldSample.setPrefWidth(SEARCHTEXTFIELD_WIDTH);
		searchButtonSample = new ButtonSample(SEARCHBUTTON_TEXT, SEARCHBUTTON_TOOLTIP);
		searchItemsContainerHbox = new HBoxSample(searchTextFieldSample, searchButtonSample);
		setTop(searchItemsContainerHbox);
		setButtonDisability();
		setSearchButtonFunctionality();
		setSearchTextFieldKeyEvent();
		setMarginForElements();
		showPodcastList();
		showEpisodesList();
	}

	private void setMarginForElements() {
		setPadding(new Insets(20, 20, 20, 20));
	}

	private void setSearchTextFieldKeyEvent() {
		searchTextFieldSample.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					searchButtonSample.fire();
				}
			}
		});
	}

	private void setButtonDisability() {
		searchButtonSample.disableProperty().bind(Bindings.isEmpty(searchTextFieldSample.textProperty()));
	}

	private void setSearchButtonFunctionality() {
		searchButtonSample.setOnAction((ActionEvent event) -> {
			startSearchPodcast();
		});
	}

	private void startSearchPodcast() {
		String searchText = "https://itunes.apple.com/search?term=" + searchTextFieldSample.getText()
				+ "&entity=podcast&media=podcast&limit=10";
		JsonParser jsonParser = new JsonParser(searchText);
		searchResultContainer = jsonParser.getSearchResultContainer();
		showSearchResult();
	}

	private void showSearchResult() {
		clearPodcastList();
		clearEpisodesList();
		SearchListBuilder listBuilder = new SearchListBuilder(searchResultList, searchResultContainer);
		if (!searchResultList.isEmpty()) {
			searchResultListView.setDisable(false);
		} else {
			// HBoxSample emptyHBoxSample = new HBoxSample(new Text("No result
			// found!"));
			searchResultList.add(new HBoxSample(new Text("No result found!")));
			searchResultListView.setDisable(true);
		}
		searchResultListView.setItems(searchResultList);
		// setAlignment(searchResultListView, Pos.CENTER_LEFT);
		// setListViewInvalidationListener();
		// setLeft(searchResultListView);
	}

	private void clearPodcastList() {
		searchResultList.clear();
	}

	private void clearEpisodesList() {
		episodesList.clear();
	}

	private void setListViewInvalidationListener() {
		searchResultListView.getSelectionModel().selectedIndexProperty().addListener((Observable o) -> {
			clearEpisodesList();
			XmlParser xmlParser = new XmlParser(searchResultContainer.getResults()
					.get(searchResultListView.getSelectionModel().getSelectedIndex()).getFeedUrl());

			searchResultContainer.getResults().get(searchResultListView.getSelectionModel().getSelectedIndex())
					.setPodcastEpisode(new ArrayList<PodcastEpisode>(xmlParser.readFeed()));

			for (int i = 0; i < searchResultContainer.getResults()
					.get(searchResultListView.getSelectionModel().getSelectedIndex()).getPodcastEpisode().size(); i++) {
				ImageView imageView = new ImageView();

				Image image = null;
				try {
					image = new Image(searchResultContainer.getResults()
							.get(searchResultListView.getSelectionModel().getSelectedIndex()).getPodcastEpisode().get(i)
							.getImage());
				} catch (Exception e) {
					e.printStackTrace();
				}
				imageView.setImage(image);
				imageView.setFitHeight(30);
				imageView.setFitWidth(30);
				HBoxSample hBoxSample = new HBoxSample(imageView,
						new Text(searchResultContainer.getResults()
								.get(searchResultListView.getSelectionModel().getSelectedIndex()).getPodcastEpisode()
								.get(i).getTitle()));
				episodesList.add(hBoxSample);
			}

			episodesListView.setItems(episodesList);
		});
	}

	private void showEpisodesList() {
		episodesListVBox = new VBox(10);
		Text episodeText = new Text("Episodes");
		episodeText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		episodesList = FXCollections.observableArrayList();
		episodesListView = new ListViewSample();
		setMargin(episodesListVBox, new Insets(20));
		episodesListView.setItems(episodesList);
		episodesListVBox.getChildren().add(episodeText);
		episodesListVBox.getChildren().add(episodesListView);
		setAlignment(episodesListVBox, Pos.CENTER_LEFT);
		episodesListVBox.setPrefSize(400, 600);
		episodesListVBox.setMaxSize(800, 800);
		setCenter(episodesListVBox);
	}

	private void showPodcastList() {
		podcastListVBox = new VBox(10);
		Text podcastText = new Text("Podcasts");
		podcastText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		searchResultList = FXCollections.observableArrayList();
		searchResultListView = new ListViewSample();
		setMargin(podcastListVBox, new Insets(20));
		searchResultListView.setItems(searchResultList);
		podcastListVBox.getChildren().add(podcastText);
		podcastListVBox.getChildren().add(searchResultListView);
		setAlignment(podcastListVBox, Pos.CENTER_LEFT);
		podcastListVBox.setPrefSize(400, 400);
		podcastListVBox.setMaxSize(800, 800);
		setListViewInvalidationListener();
		setLeft(podcastListVBox);
	}
}
