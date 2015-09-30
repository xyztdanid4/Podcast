package szakdolgozat.podcast.gui.borderpane;

import java.util.ArrayList;
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
	private List<Podcast> podcastsFromDBList;
	private VBox podcastListVBox;
	private VBox episodesListVBox;
	private ObservableList<HBoxSample> podcastsContainer;
	private ListViewSample podcastListView;
	private ObservableList<HBoxSample> episodesContainer;
	private ListView episodeListView;
	private static final String NOSUBSCRIPTIONS = "You have no subscriptions yet!";
	private static final String SUBSCRIBEDPODCAST = "Subscribed Podcasts";
	private HBox podcastInformationContainer;
	private ListViewSample podcastInformationListView;
	private ObservableList<HBox> podcastInformationHbox;
	private List<PodcastEpisode> selectedPodcastEpisodes;

	public PodcastBorderPane() {
		readfromDB();
		showPodcastEmptyInformation();
		showSubscribedPodcasts();
		setPodcastListInvalidationListener();
	}

	private void setMarginForElements() {
		setPadding(new Insets(20, 20, 20, 20));
	}

	private void readfromDB() {
		podcastsFromDBList = MorphiaConnector.getDataStore().createQuery(Podcast.class).asList();
	}

	private void showPodcastEmptyInformation() {
		podcastInformationContainer = new HBox(10);
		Text text = new Text(new String("Choose from subscribed podcast to get information!"));
		text.setFont(Font.font("Arial", FontWeight.BOLD, 13));
		podcastInformationContainer.getChildren().add(text);
		podcastInformationContainer.setPrefSize(800, 100);
		podcastInformationContainer.setMaxSize(800, 100);
		podcastInformationContainer.setStyle("-fx-background-color: white;");
		setMargin(podcastInformationContainer, new Insets(20));
		setTop(podcastInformationContainer);
	}

	private void showSubscribedPodcasts() {
		readfromDB();
		podcastListVBox = new VBox(10);
		Text podcastText = new Text("Subscribed Podcasts");
		podcastText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		podcastsContainer = FXCollections.observableArrayList();
		podcastListView = new ListViewSample();
		podcastListView.setPrefSize(300, 300);
		for (int i = 0; i < podcastsFromDBList.size(); i++) {
			ImageView imageView = new ImageView();
			Image image = new Image(podcastsFromDBList.get(i).getArtworkUrl60());
			imageView.setImage(image);
			Text name = new Text(new String(podcastsFromDBList.get(i).getArtistName()));
			name.setFont(Font.font("Arial", FontWeight.BOLD, 13));
			ButtonSample subscribeButton = new ButtonSample("Unsubscribe", "Click for unsubscribe!");
			podcastsContainer.add(new HBoxSample(imageView, name));
		}
		if (!isEmptySubscriptions()) {
			podcastListView.setDisable(false);
		} else {
			Text nosubscriptionText = new Text(new String(NOSUBSCRIPTIONS));
			podcastsContainer.add(new HBoxSample(nosubscriptionText));
			podcastListView.setDisable(true);
		}
		podcastListView.setItems(podcastsContainer);
		podcastListVBox.getChildren().add(podcastText);
		podcastListVBox.getChildren().add(podcastListView);
		setMargin(podcastListVBox, new Insets(20));
		setAlignment(podcastListVBox, Pos.CENTER_LEFT);
		// podcastListVBox.setPrefSize(300, 300);
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
		String artistname = podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex())
				.getArtistName();
		String imageUrl = podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex())
				.getArtworkUrl100();
		String collectionName = podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex())
				.getCollectionName();
		String country = podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getCountry();
		String feedUrl = podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getFeedUrl();
		String genreName = podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex())
				.getPrimaryGenreName();
		String lastReleaseDate = podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex())
				.getReleaseDate();
		String trackCount = podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex())
				.getTrackCount();
		podcastInformationContainer = new HBox(10);
		podcastInformationContainer.setPrefSize(800, 100);
		podcastInformationContainer.setMaxSize(800, 100);
		podcastInformationContainer.setStyle("-fx-background-color: white;");
		setMargin(podcastInformationContainer, new Insets(20));
		Text artistLabel = new Text(new String("Artist name: " + artistname));
		Text collectionNameLabel = new Text(new String("Title: " + collectionName));
		Text countryLabel = new Text(new String("Country: " + country));
		Text feedUrlLabel = new Text(new String("Feed url: " + feedUrl));
		Text genreLabel = new Text(new String("Genre: " + genreName));
		Text lastReleaseDateLabel = new Text(new String("Last Updated: " + lastReleaseDate));
		Text trackCountLabel = new Text(new String("Number of episodes: " + trackCount));
		artistLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		collectionNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		countryLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		feedUrlLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		genreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		lastReleaseDateLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		trackCountLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		ImageView imageView = new ImageView();
		Image image = new Image(imageUrl);
		imageView.setImage(image);
		podcastInformationContainer.getChildren().add(imageView);
		VBox helperVbox1 = new VBox(5);
		helperVbox1.getChildren().add(artistLabel);
		helperVbox1.getChildren().add(collectionNameLabel);
		helperVbox1.getChildren().add(countryLabel);
		podcastInformationContainer.getChildren().add(helperVbox1);
		VBox helperVbox2 = new VBox(5);
		helperVbox2.getChildren().add(feedUrlLabel);
		helperVbox2.getChildren().add(genreLabel);
		helperVbox2.getChildren().add(lastReleaseDateLabel);
		helperVbox2.getChildren().add(trackCountLabel);
		podcastInformationContainer.getChildren().add(helperVbox2);
		setTop(podcastInformationContainer);
	}

	private void showpodcastEpisodes() {
		readfromDB();
		System.out.println(
				podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getPodcastEpisode());
		selectedPodcastEpisodes = new ArrayList<PodcastEpisode>();
		selectedPodcastEpisodes = podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex())
				.getPodcastEpisode();
		// podcastsFromDBList.get(podcastInformationListView.getSelectionModel().getSelectedIndex());
		Text episodeText = new Text(new String("Podcast episodes"));
		episodesListVBox = new VBox(10);
		episodeListView = new ListView();
		episodesContainer = FXCollections.observableArrayList();
		for (int i = 0; i < selectedPodcastEpisodes.size(); i++) {
			// for (PodcastEpisode podcastEpisode : selectedPodcastEpisodes) {
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
		episodeListView.setItems(episodesContainer);
		episodesListVBox.getChildren().add(episodeText);
		episodesListVBox.getChildren().add(episodeListView);
		setCenter(episodesListVBox);
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
