package szakdolgozat.podcast.gui.borderpane;

import java.util.List;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.samples.ButtonSample;
import szakdolgozat.podcast.gui.samples.HBoxSample;
import szakdolgozat.podcast.gui.samples.ListViewSample;
import szakdolgozat.podcast.morphia.MorphiaConnector;

public class PodcastBorderPane extends BorderPane {
	private static final String NOSUBSCRIPTIONS = "You have no subscriptions yet!";
	private static final String GETINFO = "Choose from subscribed podcast to get information!";
	private static final String SUBSCRIBEDPODCAST = "Subscribed Podcasts";
	private static final String UNSUBSCRIBE = "Unsubscribe";
	private static final String CLICKFORUNSUBSCRIBE = "Click for unsubscribe!";
	private static final String NOPODCASTSELECTED = "Choose from subscribed podcasts!";
	private static final String SUBSCRIBEDEPISODES = "Subscribed Episodes";
	private List<Podcast> podcastsFromDBList;
	private VBox podcastListVBox;
	private VBox episodesListVBox;
	private ObservableList<HBox> podcastsContainer;
	private ListViewSample podcastListView;
	private ObservableList<HBox> episodesContainer;
	private ListView episodeListView;
	private HBox podcastInformationContainer;
	private ListViewSample podcastInformationListView;
	private ObservableList<HBox> podcastInformationHbox;
	private List<PodcastEpisode> selectedPodcastEpisodes;

	public PodcastBorderPane() {
		readfromDB();
		setMarginForElements();
		showPodcastEmptyInformation();
		showSubscribedPodcasts();
		showEmptyEpisodesList();
		setPodcastListInvalidationListener();
	}

	private void setMarginForElements() {
		setPadding(new Insets(20, 20, 20, 20));
	}

	private void readfromDB() {
		podcastsFromDBList = MorphiaConnector.getDataStore().createQuery(Podcast.class).asList();
	}

	private void showPodcastEmptyInformation() {
		Text text = new Text(GETINFO);
		text.setFont(Font.font("Arial", FontWeight.BOLD, 13));
		podcastInformationContainer = new HBox(10, text);
		podcastInformationContainer.setPrefSize(800, 100);
		podcastInformationContainer.setMaxSize(800, 100);
		podcastInformationContainer.setStyle("-fx-background-color: white;");
		// setMargin(podcastInformationContainer, new Insets(20));
		setTop(podcastInformationContainer);
	}

	private void showSubscribedPodcasts() {
		readfromDB();
		Text podcastText = new Text(SUBSCRIBEDPODCAST);
		podcastText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		podcastsContainer = FXCollections.observableArrayList();
		podcastListView = new ListViewSample();
		podcastListView.setPrefSize(300, 300);
		for (int i = 0; i < podcastsFromDBList.size(); i++) {
			ImageView imageView = new ImageView(new Image(podcastsFromDBList.get(i).getArtworkUrl60()));
			Text name = new Text(new String(podcastsFromDBList.get(i).getArtistName()));
			name.setFont(Font.font("Arial", FontWeight.BOLD, 13));
			ButtonSample subscribeButton = new ButtonSample(UNSUBSCRIBE, CLICKFORUNSUBSCRIBE);
			podcastsContainer.add(new HBoxSample(imageView, name));
		}
		if (!isEmptySubscriptions()) {
			podcastListView.setDisable(false);
		} else {
			Text nosubscriptionText = new Text(NOSUBSCRIPTIONS);
			nosubscriptionText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
			podcastsContainer.add(new HBox(nosubscriptionText));
			podcastListView.setDisable(true);
		}
		podcastListView.setItems(podcastsContainer);
		podcastListVBox = new VBox(10, podcastText, podcastListView);
		setMargin(podcastListVBox, new Insets(20));
		setAlignment(podcastListVBox, Pos.CENTER_LEFT);
		setLeft(podcastListVBox);
	}

	boolean isEmptySubscriptions() {
		return podcastsContainer.isEmpty();
	}

	private void setPodcastListInvalidationListener() {
		podcastListView.getSelectionModel().selectedIndexProperty().addListener((Observable o) -> {
			showPodcastInformation();
			showpodcastEpisodes();
		});

	}

	private void showPodcastInformation() {
		readfromDB();
		Text artistLabel = new Text(new String("Artist name: "
				+ podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getArtistName()));
		Text collectionNameLabel = new Text(new String("Title: "
				+ podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getCollectionName()));
		Text countryLabel = new Text(new String("Country: "
				+ podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getCountry()));
		Text feedUrlLabel = new Text(new String("Feed url: "
				+ podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getFeedUrl()));
		Text genreLabel = new Text(new String("Genre: " + podcastsFromDBList
				.get(podcastListView.getSelectionModel().getSelectedIndex()).getPrimaryGenreName()));
		Text lastReleaseDateLabel = new Text(new String("Last Updated: "
				+ podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getReleaseDate()));
		Text trackCountLabel = new Text(new String("Number of episodes: "
				+ podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getTrackCount()));
		artistLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		collectionNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		countryLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		feedUrlLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		genreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		lastReleaseDateLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		trackCountLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		ImageView imageView = new ImageView(new Image(
				podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getArtworkUrl100()));
		podcastInformationContainer = new HBox(10, imageView,
				new VBox(5, artistLabel, collectionNameLabel, countryLabel),
				new VBox(5, feedUrlLabel, genreLabel, lastReleaseDateLabel, trackCountLabel));
		podcastInformationContainer.setPrefSize(800, 100);
		podcastInformationContainer.setMaxSize(800, 100);
		podcastInformationContainer.setStyle("-fx-background-color: white;");
		setTop(podcastInformationContainer);
	}

	private void showEmptyEpisodesList() {
		Text noPodcastSelectedText = new Text(NOPODCASTSELECTED);
		noPodcastSelectedText.setFont(Font.font("Arial", FontWeight.BOLD, 13));
		Text episodeListText = new Text(SUBSCRIBEDEPISODES);
		episodeListText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		episodeListView = new ListView(FXCollections.observableArrayList(new HBox(10, noPodcastSelectedText)));
		episodeListView.setPrefSize(400, 300);
		episodeListView.setMaxSize(400, 300);
		VBox episodeVBox = new VBox(10, episodeListText, episodeListView);
		setMargin(episodeVBox, new Insets(20));
		setCenter(episodeVBox);
	}

	private void showpodcastEpisodes() {
		readfromDB();
		Text episodeListText = new Text(SUBSCRIBEDEPISODES);
		episodeListText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		selectedPodcastEpisodes = podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex())
				.getPodcastEpisode();
		episodesContainer = FXCollections.observableArrayList();
		for (PodcastEpisode podcastEpisode : selectedPodcastEpisodes) {
			Text pubDateText = new Text(podcastEpisode.getPubdate());
			Text titleText = new Text(podcastEpisode.getTitle());
			pubDateText.setFont(Font.font("Arial", FontWeight.BOLD, 12));
			titleText.setFont(Font.font("Arial", FontWeight.BOLD, 12));
			ImageView imageView = new ImageView(new Image(podcastEpisode.getImage()));
			imageView.setFitHeight(30);
			imageView.setFitWidth(30);
			episodesContainer.add(new HBox(10, imageView, titleText, pubDateText));
		}
		episodeListView = new ListView<HBox>(episodesContainer);
		episodeListView.setPrefSize(400, 300);
		episodeListView.setMaxSize(400, 300);
		VBox episodeVBox = new VBox(10, episodeListText, episodeListView);
		setMargin(episodeVBox, new Insets(20));
		setCenter(episodeVBox);
	}

	private void task(final int i) {
		String imageUrl = selectedPodcastEpisodes.get(i).getImage();
		String pubDate = selectedPodcastEpisodes.get(i).getPubdate();
		String title = selectedPodcastEpisodes.get(i).getTitle();
		Text pubDateText = new Text(pubDate);
		Text titleText = new Text(title);
		pubDateText.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		titleText.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		ImageView imageView = new ImageView();
		imageView.setFitHeight(30);
		imageView.setFitWidth(30);
		Image episodeImage = new Image(imageUrl);
		imageView.setImage(episodeImage);
		episodesContainer.add(new HBoxSample(imageView, titleText, pubDateText));
	}
}
