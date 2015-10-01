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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import szakdolgozat.podcast.data.podcast.PodcastContainer;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.listbuilder.SearchListBuilder;
import szakdolgozat.podcast.gui.samples.ButtonSample;
import szakdolgozat.podcast.gui.samples.HBoxSample;
import szakdolgozat.podcast.gui.samples.ListViewSample;
import szakdolgozat.podcast.gui.samples.TextFieldSample;
import szakdolgozat.podcast.jsonparser.JsonParser;
import szakdolgozat.podcast.xmlparser.XmlParser;

public class SearchBorderPane extends BorderPane {
	private static final String SEARCHTEXTFIELD_PROMPTTEXT = "SEARCH ME!";
	private static final String SEARCHTEXTFIELD_TOOLTIP = "Type something, you would like to podcast!";
	private static final int SEARCHTEXTFIELD_WIDTH = 800;
	private static final String SEARCHBUTTON_TOOLTIP = "Click me for search!";
	private static final String SEARCHBUTTON_TEXT = "Search!";
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
		searchTextField.setPrefWidth(SEARCHTEXTFIELD_WIDTH);
		searchButton = new ButtonSample(SEARCHBUTTON_TEXT, SEARCHBUTTON_TOOLTIP);
		searchItemsContainerHbox = new HBox(10, searchTextField, searchButton);
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
		searchPodcastContainer = jsonParser.getSearchResultContainer();
		showSearchResult();
	}

	private void showSearchResult() {
		searchResultList.clear();
		episodesList.clear();
		SearchListBuilder listBuilder = new SearchListBuilder(searchResultList, searchPodcastContainer);
		if (!searchResultList.isEmpty()) {
			searchResultListView.setDisable(false);
			episodesListView.setDisable(false);
		} else {
			Text text = new Text(new String("No result found!"));
			text.setFont(Font.font("Arial", FontWeight.BOLD, 16));
			searchResultList.add(new HBox(text));
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
					HBoxSample hBoxSample = new HBoxSample(imageView,
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
		// episodesListVBox.setMaxSize(800, 800);
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
